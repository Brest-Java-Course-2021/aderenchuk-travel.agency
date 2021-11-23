//package com.aderenchuk.brest.service.rest;
//
//import com.aderenchuk.brest.model.dto.TourDto;
//import com.aderenchuk.brest.service.TourDtoService;
//import com.aderenchuk.brest.service.TourService;
//import com.aderenchuk.brest.service.rest.config.TestConfig;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.client.ExpectedCount;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static com.aderenchuk.brest.service.rest.config.TestConfig.TOUR_DTOS_URL;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class TourDtoServiceRestIT {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TourDtoServiceRestIT.class);
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    private MockRestServiceServer mockServer;
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Autowired
//    TourDtoServiceRest tourDtoServiceRest;
//
//    @BeforeEach
//    public void before() {
//        mockServer = MockRestServiceServer.createServer(restTemplate);
//    }
//
//    @Test
//    public void shouldFindAllWithDateFilter() throws Exception {
//        LOGGER.debug("should find with date filter()");
//        //given
//        LocalDate dateFrom = LocalDate.now().minusDays(1);
//        LocalDate dateTo = LocalDate.now().plusDays(1);
//        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOUR_DTOS_URL + "?dateFrom=" + dateFrom + "&dateTo=" + dateTo)))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
//                );
//        //when
//        List<TourDto> tourDtoList = tourDtoServiceRest.findAllQuantityClientsAndDateFilter(dateFrom, dateTo);
//        //then
//        assertTrue(tourDtoList.size() == 2);
//    }
//
//    @Test
//    public void shouldFindAllWithQuantityClients() throws Exception{
//        LOGGER.debug("should find all with quantity clients()");
//        //given
//        mockServer.expect(ExpectedCount.once(), requestTo(new URI(TOUR_DTOS_URL)))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
//                );
//        //when
//        List<TourDto> tourDtoList = tourDtoServiceRest.findAllQuantityClients();
//        //then
//        assertTrue(tourDtoList.size() > 0);
//    }
//
//    private TourDto create(int index){
//
//        TourDto tourDto = new TourDto();
//        tourDto.setTourId(index);
//        tourDto.setDirection("direction"+index);
//        tourDto.setDateTour(LocalDate.now());
//        tourDto.setQuantityClients(1+index);
//        return tourDto;
//    }
//}
