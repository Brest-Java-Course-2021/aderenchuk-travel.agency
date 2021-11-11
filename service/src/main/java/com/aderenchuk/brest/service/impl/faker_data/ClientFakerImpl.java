package com.aderenchuk.brest.service.impl.faker_data;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientFakerService;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class ClientFakerImpl implements ClientFakerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFakerImpl.class);


    private Faker faker;

    public ClientFakerImpl(Faker faker) {
        this.faker = faker;
    }

    @Override
    public List<Client> findAll() {
        LOGGER.trace("findAll()");
        List<Client> fakerClients = IntStream.rangeClosed(1, 100).mapToObj(i -> new Client(
                i,
                faker.name().firstName(),
                faker.name().lastName(),
                i
        )).collect(Collectors.toList());
        return fakerClients;
    }
}
