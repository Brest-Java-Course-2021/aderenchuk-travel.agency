package com.aderenchuk.brest.service.rest;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceRest implements ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public ClientServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Client> findAll() {
        LOGGER.debug("find all clients");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Client>) responseEntity.getBody();
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        LOGGER.debug("findById ({})", clientId);
        ResponseEntity<Client> responseEntity =
                restTemplate.getForEntity(url + "/" + clientId, Client.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Integer create(Client client) {
        LOGGER.debug("create({})", client);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, client, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer update(Client client) {
        LOGGER.debug("update({})", client);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Client> entity = new HttpEntity<>(client, httpHeaders);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return responseEntity.getBody();
    }

    @Override
    public Integer delete(Integer clientId) {
        LOGGER.debug("delete client with id ({})", clientId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Integer> entity = new HttpEntity<>(clientId, httpHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(url + "/" + clientId, HttpMethod.DELETE, entity, Integer.class);
        return (Integer) responseEntity.getBody();
    }
}
