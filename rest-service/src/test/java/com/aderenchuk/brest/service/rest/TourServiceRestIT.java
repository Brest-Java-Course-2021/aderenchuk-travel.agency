package com.aderenchuk.brest.service.rest;


import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import com.aderenchuk.brest.service.rest.config.TestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.net.URISyntaxException;
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
public class TourServiceRestIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceRestIT.class);

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TourServiceRest tourServiceRest;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void shouldFindAllTours() throws URISyntaxException, JsonProcessingException {
        LOGGER.debug("Should find all tours");
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOURS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))                );

        // when
        List<Tour> tours = tourServiceRest.findAll();

        // then
        mockServer.verify();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
    }

    @Test
    void shouldCreateTour() throws Exception {

        LOGGER.debug("should create Tour");
        // given
        Integer id = 1;
        Tour tour = create(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOURS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(id))
                );
        //when
        Integer tourId = tourServiceRest.create(tour);

        // then
        mockServer.verify();
        assertNotNull(tourId);
    }

    @Test
    void shouldFindTourById() throws Exception {

        LOGGER.debug("should find tour by id ");
        // given
        Integer id = 1;
        Tour tour = create(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOURS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(tour))
                );

        // when
        Optional<Tour> optionalTours = tourServiceRest.findById(id);

        // then
        mockServer.verify();
        assertTrue(optionalTours.isPresent());
        assertEquals(optionalTours.get().getTourId(), id);
        assertEquals(optionalTours.get().getDirection(), tour.getDirection());
        assertEquals(optionalTours.get().getDateTour(), tour.getDateTour());
    }

    @Test
    void shouldUpdateTour() throws Exception {

        // given
        Integer id = 1;
        Tour tour = create(id);

        mockServer.expect(ExpectedCount.once(), requestTo(TOURS_URL))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(id))
                );

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOURS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(tour))
                );

        // when
        int result = tourServiceRest.update(tour);
        Optional<Tour> updatedTourOptional = tourServiceRest.findById(id);

        // then
        mockServer.verify();
        assertTrue(1 == result);

        assertTrue(updatedTourOptional.isPresent());
        assertEquals(updatedTourOptional.get().getTourId(), id);
        assertEquals(updatedTourOptional.get().getDirection(), tour.getDirection());
    }





    @Test
    void shouldDeleteTour() throws Exception {

        //given
        Integer id = 1;

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOURS_URL + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        //when
        int result = tourServiceRest.delete(id);

        // then
        mockServer.verify();
        assertEquals(result,1);
    }


    private Tour create(int index){

        Tour tour = new Tour();
        tour.setTourId(index);
        tour.setDirection("direction:"+index);
        return tour;
    }

}