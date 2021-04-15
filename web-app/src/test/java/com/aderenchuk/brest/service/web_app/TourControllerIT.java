package com.aderenchuk.brest.service.web_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("tours"))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("tourId", is(101)),
                                hasProperty("direction", is("BREST-MINSK")),
                                hasProperty("dateTour", is("2020-05-12"))
                        )
                )))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("tourId", is(102)),
                                hasProperty("direction", is("MINSK-DUBAI")),
                                hasProperty("dateTour", is("2020-06-13"))
                        )
                )))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("tourId", is(103)),
                                hasProperty("direction", is("MOSCOW-BERLIN")),
                                hasProperty("dateTour", is("2020-05-18"))
                        )
                )))
        ;
    }
}