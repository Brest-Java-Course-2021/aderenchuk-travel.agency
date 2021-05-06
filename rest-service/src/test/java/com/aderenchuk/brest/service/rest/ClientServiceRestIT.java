package com.aderenchuk.brest.service.rest;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.rest.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.aderenchuk.brest.service.rest.config.TestConfig.TOURS_URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class ClientServiceRestIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceRestIT.class);

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    ClientServiceRest clientServiceRest;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        clientServiceRest = new ClientServiceRest(TOURS_URL, restTemplate);
    }

    @Test
    void shouldFindAllClients()throws Exception {

        LOGGER.debug("should find all clients");
        //given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOURS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );
        //when
        List<Client> clientList = clientServiceRest.findAll();

        // then
        mockServer.verify();
        assertNotNull(clientList);
        assertTrue(clientList.size() > 0);
    }

    @Test
    void shouldFindClientById() throws Exception {

        LOGGER.debug("should find client by id");
        // given
        Integer id = 1;
        Client client = create(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOURS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(client))
                );

        // when
        Optional<Client> optionalClient = clientServiceRest.findById(id);

        // then
        mockServer.verify();
        assertTrue(optionalClient.isPresent());
        assertEquals(optionalClient.get().getClientId(), id);
        assertEquals(optionalClient.get().getFirstName(), client.getFirstName());
        assertEquals(optionalClient.get().getLastName(), client.getLastName());
        assertEquals(optionalClient.get().getTourId(), client.getTourId());
    }

    @Test
    void shouldCreateClient() throws Exception{

        LOGGER.debug("should create client");
        Integer id = 1;
        Client client = create(id);
        //given
        mockServer.expect(ExpectedCount.once(),requestTo(new URI(TOURS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(id)));


        //when
        Integer passengerId = clientServiceRest.create(client);

        //then
        mockServer.verify();
        assertNotNull(passengerId);
    }

    @Test
    void shouldUpdateClient() throws Exception{

        LOGGER.debug("should update client()");
        //given
        Integer id = 1;
        Client client = create(id);
        mockServer.expect(ExpectedCount.once(),requestTo(TOURS_URL))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(1)));

        //when
        int result = clientServiceRest.update(client);

        //then
        mockServer.verify();
        assertEquals(result, 1);
    }

    @Test
    void shouldDeleteClient() throws Exception {

        LOGGER.debug("should delete client");
        //given
        Integer id = 1;
        Client client = create(id);
        mockServer.expect(ExpectedCount.once(), requestTo(TOURS_URL + "/" + id ))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1")));

        //when
        int result = clientServiceRest.delete(id);

        //then
        assertTrue(result == 1);

    }

    private Client create(int index){

        Client client = new Client();
        client.setClientId(index);
        client.setFirstName("firstname"+index);
        client.setLastName("lastname"+index);
        client.setTourId(100+index);

        return client;
    }

}