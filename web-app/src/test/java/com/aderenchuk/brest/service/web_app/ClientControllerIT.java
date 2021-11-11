package com.aderenchuk.brest.service.web_app;

import com.aderenchuk.brest.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
                MockMvcRequestBuilders.get(CLIENT_URL)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("clients"))
                .andExpect(model().attribute("clients", hasItem(
                        allOf(
                                hasProperty("firstName", is("Ihor")),
                                hasProperty("lastName", is("Dmitriev")),
                                hasProperty("tourId", is(101))
                        )
                )))
                .andExpect(model().attribute("clients", hasItem(
                        allOf(
                                hasProperty("firstName", is("Alex")),
                                hasProperty("lastName", is("Volkanovski")),
                                hasProperty("tourId", is(103))
                        )
                )))
        ;
    }

    @Test
    public void shouldOpenEditTourPageById() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get( "/client/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("client"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("client", hasProperty("firstName", is("Griha"))))
                .andExpect(model().attribute("client", hasProperty("lastName", is("Bogin"))))
                .andExpect(model().attribute("client", hasProperty("tourId", is(106)
                )));
    }

    @Test
    public void shouldReturnToClientPageIfClientNotFoundById() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/client/999")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("clients"));
    }

//    @Test
//    public void shouldUpdateClientAfterEdit() throws Exception {
//
//        Client client = create(1,"Egor", "Britnev", 105);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/client/1")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("firstName", client.getFirstName())
//                .param("lastName", client.getLastName())
//                .param("tourId", String.valueOf(client.getTourId()))
//                .sessionAttr("client", client)
//        ).andExpect(status().isFound())
//                .andExpect(view().name("redirect:" + CLIENT_URL))
//                .andExpect(redirectedUrl(CLIENT_URL));
//    }

    @Test
    public void shouldOpenNewClientPage() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/client")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("client"))
                .andExpect(model().attribute("isNew", is(true)))
                .andExpect(model().attribute("client", isA(Client.class)));
    }

//    @Test
//    public void shouldAddNewClient() throws Exception {
//
//        Client client = create(2, "Sam", "Rodd", 103);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/client/2")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("firstName", client.getFirstName())
//                .param("lastName", client.getLastName())
//                .param("tourId", String.valueOf(client.getTourId()))
//        ).andExpect(status().isFound())
//                .andExpect(view().name("redirect:" + CLIENT_URL))
//                .andExpect(redirectedUrl(CLIENT_URL));
//    }

    @Test
    public void shouldDeleteClient() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.get("/client/1/delete")
        ).andExpect(status().isFound())
                .andExpect(view().name("redirect:" + CLIENT_URL))
                .andExpect(redirectedUrl(CLIENT_URL));
    }

    private Client create(int clientId, String firstName, String lastName, int tourId) {
        Client client = new Client();
        client.getClientId();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setTourId(tourId);
        return client;
    }
}
