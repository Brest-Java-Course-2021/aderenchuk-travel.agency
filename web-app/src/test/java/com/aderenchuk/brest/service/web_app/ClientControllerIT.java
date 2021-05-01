package com.aderenchuk.brest.service.web_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ClientControllerIT {

    private final String CLIENT_URL = "/clients";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldReturnClientPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("clients"))
                .andExpect(model().attribute("clients", hasItem(
                        allOf(
                                hasProperty("firstname", is("Ihor")),
                                hasProperty("lastname", is("Dmitriev")),
                                hasProperty("tourId", is(101))
                        )
                )))
                .andExpect(model().attribute("clients", hasItem(
                        allOf(
                                hasProperty("firstname", is("Alex")),
                                hasProperty("lastname", is("Volkanovski")),
                                hasProperty("tourId", is(103))
                        )
                )))
        ;
    }

    @Test
    public void shouldOpenEditTourPageById() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get( CLIENT_URL+"/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("client"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("client", hasProperty("firstname", is("Ihor"))))
                .andExpect(model().attribute("client", hasProperty("lastname", is("Dmitriev"))))
                .andExpect(model().attribute("client", hasProperty("tourId", is(101)
                )));
    }


}
