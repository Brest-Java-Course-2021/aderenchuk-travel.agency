package com.aderenchuk.brest.service.rest;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceRest implements TourService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public TourServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Tour> findAll() {
        LOGGER.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Tour>) responseEntity.getBody();
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        LOGGER.debug("find by id ({})", tourId);
        ResponseEntity<Tour> responseEntity =
                restTemplate.getForEntity(url + "/" + tourId, Tour.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Integer create(Tour tour) {
        LOGGER.debug("create ({})", tour);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, tour, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer update(Tour tour) {
        LOGGER.debug("update ({})", tour);
//        restTemplate.put(url, tour);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Tour> entity = new HttpEntity<>(tour, httpHeaders);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer delete(Integer tourId) {
        LOGGER.debug("delete ({})", tourId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Tour> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + tourId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }
}
