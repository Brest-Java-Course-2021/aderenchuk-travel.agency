package com.aderenchuk.brest.service.web_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.testdb.SpringJdbcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class TourControllerIT {

    private final String TOURS_URL = "/tours";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }




    @Test
    public void shouldReturnDepartmentsPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(TOURS_URL)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("tours"))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("tourId", is(101)),
                                hasProperty("direction", is("BREST-MOSCOW")),
                                hasProperty("dateTour", is(convertToLocalDate("2021-08-01")))
                        )
                )))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("tourId", is(102)),
                                hasProperty("direction", is("MINSK-ROME")),
                                hasProperty("dateTour", is(convertToLocalDate("2021-08-01")))
                        )
                )))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("tourId", is(103)),
                                hasProperty("direction", is("MOSCOW-BARCELONA")),
                                hasProperty("dateTour", is(convertToLocalDate("2021-06-15")))
                        )
                )));
    }



    @Test
    public void shouldOpenEditTourPageById() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get( TOURS_URL+"/101")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("tour"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("tour", hasProperty("tourId", is(101))))
                .andExpect(model().attribute("tour", hasProperty("direction", is("BREST-MOSCOW"))))
                .andExpect(model().attribute("tour", hasProperty("dateTour", is(convertToLocalDate("2021-08-01"))
                )));
    }


    @Test
    public void shouldReturnToToursPageIfTourNotFoundById() throws Exception {

         mockMvc.perform(
                 MockMvcRequestBuilders.get(TOURS_URL+"/13")
         ).andDo(MockMvcResultHandlers.print())
                 .andExpect(status().isFound())
                 .andExpect(MockMvcResultMatchers.redirectedUrl("tours"));
    }

     @Test
     public void shouldUpdateTourAfterEdit() throws Exception {

         Tour tour = create(101, "BREST-MOSCOW");

         mockMvc.perform(
                 MockMvcRequestBuilders.post(TOURS_URL + "/101")
                 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                 .param("tourId", String.valueOf(tour.getTourId()))
                 .param("direction", tour.getDirection())
                 .param("dateTour", String.valueOf(tour.getDateTour()))
                 .sessionAttr("tour", tour)
         ).andExpect(status().isFound())
                 .andExpect(view().name("redirect:" + TOURS_URL))
                 .andExpect(redirectedUrl(TOURS_URL));
     }


    @Test
    public void shouldOpenNewTourPage() throws Exception {

        mockMvc.perform(
         MockMvcRequestBuilders.get(TOURS_URL+"/add")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("tour"))
                .andExpect(model().attribute("isNew", is(true)))
                .andExpect(model().attribute("tour", isA(Tour.class)));
    }


     @Test
     public void shouldAddNewTour() throws Exception {
         Tour tour = create(108, "BREST-KIEV");

         mockMvc.perform(
                 MockMvcRequestBuilders.post(TOURS_URL + "/add")
                 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                 .param("tourId", String.valueOf(tour.getTourId()))
                 .param("direction", tour.getDirection())
                 .param("dateTour", ("2020-05-12"))
         ).andExpect(status().isFound())
                 .andExpect(redirectedUrl(TOURS_URL));
     }

     @Test
     public  void shouldDeleteTour() throws Exception {

         mockMvc.perform(
                 MockMvcRequestBuilders.get(TOURS_URL + "/1/delete")
         ).andExpect(status().isFound())
                 .andExpect(view().name("redirect:" + "/tours"))
                 .andExpect(redirectedUrl("/tours"));
     }




    private Tour create(int id, String direction) {
        Tour tour = new Tour();
        tour.setTourId(id);
        tour.setDirection(direction);
        tour.setDateTour(convertToLocalDate("2021-08-01"));
        return tour;
    }

    private LocalDate convertToLocalDate(String dateAdded) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateForId = LocalDate.parse(dateAdded, dtf);
        return localDateForId;
    }


}