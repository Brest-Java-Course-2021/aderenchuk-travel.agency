package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.impl.ClientServiceImpl;
import com.aderenchuk.brest.service.rest_app.exception.ErrorResponse;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    /**
     * Go to clients list page
     *
     * @return view name
     */
    @GetMapping(value = "clients")
    public final Iterable<Client> clients() {
        LOGGER.debug("clients()");
        return clientService.findAll();
    }

    /**
     * find client by id
     * @param id
     * @return client
     */
    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        LOGGER.debug("find client by id({})", id);
        Optional<Client> optionalClient = clientService.findById(id);
        return optionalClient.isPresent()
                ? new ResponseEntity<>(optionalClient.get(), HttpStatus.OK)
                : new ResponseEntity(
                new ErrorResponse(List.of("Can't find Client with such id")),
                HttpStatus.NOT_FOUND);
    }

    /**
     * create new client
     * @param client
     * @return id client
     */
    @PostMapping(path = "/clients", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createClient(@RequestBody Client client) {
        LOGGER.debug("createClient({})", client);
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update client
     * @param clientId
     * @param clientDetails
     * @return update client
     */
    @PutMapping(value = "/clients", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Boolean> updateClient(@PathVariable Integer clientId, @RequestBody Client clientDetails) throws NotFoundException {
        LOGGER.debug("updateClient({})", clientDetails);
        Client client = clientService.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Tour not found for this id :: " + clientId));

        client.setClientId(clientDetails.getClientId());
        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setTourId(clientDetails.getTourId());
        final boolean updatedTour = clientService.update(client);
        return ResponseEntity.ok(updatedTour);
    }

    /**
     * delete client
     * @param id
     * @return result of delete
     */
    @DeleteMapping(value = "/clients/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteClient(@PathVariable Integer id) throws NotFoundException {
        LOGGER.debug("deleteClient({})", id);
        Client client = clientService.findById(id)
                .orElseThrow(() -> new NotFoundException("Tour not found for this id :: " + id));

        clientService.delete(id);
        List<Client> list = new ArrayList<>();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
