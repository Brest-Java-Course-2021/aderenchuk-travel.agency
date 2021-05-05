package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.service.ClientService;
import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.rest_app.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static com.aderenchuk.brest.service.rest_app.constants.RestConstants.CLIENT_NOT_FOUND;
import static com.aderenchuk.brest.service.rest_app.constants.RestConstants.CLIENT_NOT_FOUND_BY_ID;

@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Go to clients list page
     *
     * @return view name
     */
    @GetMapping(value = "clients")
    public final Collection<Client> clients() {
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
                new ErrorResponse(CLIENT_NOT_FOUND,
                        Arrays.asList(CLIENT_NOT_FOUND_BY_ID + id)),
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
        Integer id = clientService.create(client);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Update client
     * @param client
     * @return id client
     */
    @PutMapping(value = "/clients", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateClient(@RequestBody Client client) {
        LOGGER.debug("updateClient({})", client);
        int result = clientService.update(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * delete client
     * @param id
     * @return result of delete
     */
    @DeleteMapping(value = "/clients/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteClient(@PathVariable Integer id) {
        LOGGER.debug("deleteClient({})", id);
        int result = clientService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
