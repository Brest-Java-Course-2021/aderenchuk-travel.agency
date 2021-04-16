package com.aderenchuk.brest.service.web_app;

import com.aderenchuk.brest.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:app-context-test.xml"})
@Transactional
class DepartmentControllerIT {

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
                MockMvcRequestBuilders.get("/tours")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("tours"));
//                .andExpect(model().attribute("tours", hasItem(
//                        allOf(
//                                hasProperty("tourId", is(101)),
//                                hasProperty("direction", is("BREST-MINSK")),
//                                hasProperty("dateTour", is(convertToLocalDate("2020-06-05")))
//                        )
//                )))
//                .andExpect(model().attribute("tours", hasItem(
//                        allOf(
//                                hasProperty("tourId", is(102)),
//                                hasProperty("direction", is("MINSK-DUBAI")),
//                                hasProperty("dateTour", is(convertToLocalDate("2020-06-20")))
//                        )
//                )))
//                .andExpect(model().attribute("tours", hasItem(
//                        allOf(
//                                hasProperty("tourId", is(103)),
//                                hasProperty("direction", is("MOSCOW-BERLIN")),
//                                hasProperty("dateTour", is(convertToLocalDate("2020-05-15")))
//                        )
//                )));
    }

//    @Test
//    public void shouldOpenEditToursPageId() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get( "/tours/{id}", 101)
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("tour"))
//                .andExpect(model().attribute("isNew", is(false)))
//                .andExpect(model().attribute("tour", hasProperty("tourId", is(101))))
//                .andExpect(model().attribute("tour", hasProperty("direction", is("MINSK-BREST"))))
//                .andExpect(model().attribute("tour", hasProperty("dateTour", is(convertToLocalDate("2020-03-11"))
//                )));
//    }
//
//    @Test
//    public void shouldReturnToToursPageIfTourNotFoundById() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/tours"+"/13")
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("tours"));
//    }
//
//    @Test
//    public void shouldUpdateTourAfterEdit() throws Exception {
//
//        Tour tour = create(1);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/tours" + "/101")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("tourId", String.valueOf(tour.getTourId()))
//                .param("direction", tour.getDirection())
//                .param("dateTour", String.valueOf(tour.getDateTour()))
//                .sessionAttr("tour", tour)
//        ).andExpect(status().isFound())
//                .andExpect(view().name("redirect:" + "/tours"))
//                .andExpect(redirectedUrl("/tours"));
//    }
//
//    @Test
//    public void shouldOpenNewTourPage() throws Exception {
//
//        mockMvc.perform(
//         MockMvcRequestBuilders.get("/tours" + "/add")
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
//                .andExpect(view().name("tour"))
//                .andExpect(model().attribute("isNew", is(true)))
//                .andExpect(model().attribute("tour", isA(Tour.class)));
//    }
//
//    @Test
//    public void shouldAddNewTour() throws Exception {
//        Tour tour = create(1);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/tours" + "/add")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("tourId", String.valueOf(tour.getTourId()))
//                .param("direction", tour.getDirection())
//                .param("dateTour", ("2020-05-12"))
//        ).andExpect(status().isFound())
//                .andExpect(redirectedUrl("/tours"));
//    }
//
//    @Test
//    public  void shouldDeleteTour() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/tours" + "/1/delete")
//        ).andExpect(status().isFound())
//                .andExpect(view().name("redirect:" + "/tours"))
//                .andExpect(redirectedUrl("tours"));
//    }
//
//    private Tour create(int index) {
//        Tour tour = new Tour();
//        tour.setTourId(index);
//        tour.setDirection("MINSK-" + index);
//        tour.setDateTour(LocalDate.now());
//        return tour;
//    }
//
//    private LocalDate convertToLocalDate(String dateAdded) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDateForId = LocalDate.parse(dateAdded, dtf);
//        return localDateForId;
//    }


}