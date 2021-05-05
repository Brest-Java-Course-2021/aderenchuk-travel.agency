package com.aderenchuk.brest.service.rest;

import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.service.TourDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TourDtoServiceRest implements TourDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public TourDtoServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }


    @Override
    public List<TourDto> findAllQuantityClients() {
        LOGGER.debug("find quantity clients ({})");
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return responseEntity.getBody();
    }

    @Override
    public List<TourDto> findAllQuantityClientsAndDateFilter(LocalDate dateFrom, LocalDate dateTo) {
        LOGGER.debug("find with date filter ({}, {})", dateFrom, dateTo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("dateFrom", dateFrom)
                .queryParam("dateTo", dateTo);
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, List.class);
        return responseEntity.getBody();
    }
}
