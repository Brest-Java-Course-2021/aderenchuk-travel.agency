package com.aderenchuk.brest.service.rest_app.faker_data;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientFakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aderenchuk.brest.service.rest_app.exception.ControllerAdvisor.LOGGER;

@RestController
@RequestMapping("/fakerClients")
public class ClientFakerController {

    private ClientFakerService clientFakerService;

    public ClientFakerController(ClientFakerService clientFakerService) {
        this.clientFakerService = clientFakerService;
    }

    @GetMapping()
    public final Iterable<Client> fakeClients() {
        LOGGER.debug("fakeClient()");

        return clientFakerService.findAll();
    }
}
