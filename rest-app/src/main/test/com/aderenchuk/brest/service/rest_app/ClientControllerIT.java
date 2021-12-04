package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.service.ClientService;
import com.aderenchuk.brest.model.Client;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.aderenchuk.brest.model.constants.ClientConstants.FIRST_NAME_SIZE;
import static com.aderenchuk.brest.model.constants.ClientConstants.LAST_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
public class ClientControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private final String CLIENTS_ENDPOINT = "/clients";

    @Autowired
    private ClientService clientService;

//    private MockMvc mockMvc;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    MockMvcClientService clientService = new MockMvcClientService();
//
//    @Autowired
//    private ClientController clientController;
//
//    @Autowired
//    private CustomExceptionHandler customExceptionHandler;
//
//    @BeforeEach
//    public void before() {
//        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
//                .setMessageConverters(new MappingJackson2HttpMessageConverter())
//                .setControllerAdvice(customExceptionHandler)
//                .alwaysDo(MockMvcResultHandlers.print())
//                .build();
//    }

    @Test
    public void shouldFindAllClients() throws Exception {
        List<Client> clients = clientService.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);
    }

    @Test
    public void findById() {
        List<Client> clients = clientService.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);

        Integer id = clients.get(0).getClientId();
        Client expClient = clientService.findById(id).get();
        assertEquals(id, expClient.getClientId());
        assertEquals(clients.get(0).getFirstName(), expClient.getFirstName());
        assertEquals(clients.get(0).getLastName(), expClient.getLastName());
        assertEquals(clients.get(0), expClient);
    }

    @Test
    public void findByIdExceptional() {
        Optional<Client> optionalClient = clientService.findById(999);
        assertTrue(optionalClient.isEmpty());
    }

    @Test
    public void createClient() {
        List<Client> clients = clientService.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);

        clientService.create(new Client("Ihor", "Dmitriev", 101));

        List<Client> realClient = clientService.findAll();
        assertEquals(clients.size() + 1, realClient.size());
    }

    @Test
    public void updateClient() {
        List<Client> clients = clientService.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);

        Client client = clients.get(0);
        client.setLastName(RandomStringUtils.randomAlphabetic(LAST_NAME_SIZE));
        client.setFirstName(RandomStringUtils.randomAlphabetic(FIRST_NAME_SIZE));
        client.setTourId(101);
        clientService.update(client);

        Optional<Client> realClient = clientService.findById(client.getClientId());
        assertEquals(realClient.get().getLastName(), client.getLastName());
        assertEquals(realClient.get().getFirstName(), client.getFirstName());
        assertEquals(realClient.get().getTourId(), client.getTourId());
    }

    @Test
    public void deleteClient() {
        Client client = new Client();
        client.setFirstName(RandomStringUtils.randomAlphabetic(FIRST_NAME_SIZE));
        client.setLastName(RandomStringUtils.randomAlphabetic(LAST_NAME_SIZE));
        client.setTourId(101);
        Integer id = clientService.create(client);
        assertNotNull(id);

        List<Client> clients = clientService.findAll();
        assertNotNull(clients);

        int result = clientService.delete(id);

        assertTrue(1 == result);

        List<Client> realClients = clientService.findAll();
        assertNotNull(realClients);

        assertTrue(clients.size()-1 == realClients.size());
    }



//    private class MockMvcClientService {
//
//        public List<Client> findAll() throws Exception {
//            LOGGER.debug("findAll()");
//            MockHttpServletResponse response = mockMvc.perform(get(CLIENTS_ENDPOINT)
//                    .accept(MediaType.APPLICATION_JSON)
//            ).andExpect(status().isOk())
//                    .andReturn().getResponse();
//            assertNotNull(response);
//
//            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Client>>() {});
//        }
//
//        private Optional<Client> findById(Integer clientId) throws Exception {
//
//            LOGGER.debug("findById({})", clientId);
//            MockHttpServletResponse response = mockMvc.perform(get(CLIENTS_ENDPOINT + "/" + clientId)
//                    .accept(MediaType.APPLICATION_JSON)
//            ).andExpect(status().isOk())
//                    .andReturn().getResponse();
//            return Optional.of(objectMapper.readValue(response.getContentAsString(), Client.class));
//        }
//
//        private Integer create(Client client) throws Exception {
//
//            LOGGER.debug("create({})", client);
//            MockHttpServletResponse response =
//                    mockMvc.perform(post(CLIENTS_ENDPOINT)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(client))
//                            .accept(MediaType.APPLICATION_JSON)
//                    ).andExpect(status().isOk())
//                            .andReturn().getResponse();
//            return objectMapper.readValue(response.getContentAsString(), Integer.class);
//        }
//
//        private int update(Client client) throws Exception {
//
//            LOGGER.debug("create({})", client);
//            MockHttpServletResponse response =
//                    mockMvc.perform(put(CLIENTS_ENDPOINT)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(client))
//                            .accept(MediaType.APPLICATION_JSON)
//                    ).andExpect(status().isOk())
//                            .andReturn().getResponse();
//            return objectMapper.readValue(response.getContentAsString(), Integer.class);
//        }
//
//        private int delete(Integer clientId) throws Exception {
//
//            LOGGER.debug("delete(id:{})", clientId);
//            MockHttpServletResponse response = mockMvc.perform(
//                    MockMvcRequestBuilders.delete(new StringBuilder(CLIENTS_ENDPOINT).append("/")
//                            .append(clientId).toString())
//                            .accept(MediaType.APPLICATION_JSON)
//            ).andExpect(status().isOk())
//                    .andReturn().getResponse();
//
//            return objectMapper.readValue(response.getContentAsString(), Integer.class);
//        }
//
//        public List<Client> findByClientId(int clientId) throws Exception {
//
//            LOGGER.debug("findByDepartmentId({})", clientId);
//            MockHttpServletResponse response = mockMvc.perform(get(CLIENTS_ENDPOINT )
//                    .param("departmentId", String.valueOf(clientId))
//                    .accept(MediaType.APPLICATION_JSON)
//            ).andExpect(status().isOk())
//                    .andReturn().getResponse();
//            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Client>>() {});
//        }
//    }
}
