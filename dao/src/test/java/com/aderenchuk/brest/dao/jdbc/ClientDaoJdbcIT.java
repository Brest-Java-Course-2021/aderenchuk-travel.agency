package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.ClientDao;
import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.testdb.SpringJdbcConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.aderenchuk.brest.constants.ClientConstants.FIRST_NAME_SIZE;
import static com.aderenchuk.brest.constants.ClientConstants.LAST_NAME_SIZE;
import static com.aderenchuk.brest.constants.TourConstants.DIRECTION_MAX_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@Transactional
public class ClientDaoJdbcIT {

    @Autowired
    private ClientDao clientDao;

    @Test
    public void findAll() {
        List<Client> clients = clientDao.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);
    }

    @Test
    public void findById() {
        List<Client> clients = clientDao.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);


        Integer clientId = clients.get(0).getClientId();
        Client expClient = clientDao.findById(clientId).get();
        assertEquals(clientId, expClient.getClientId());
        assertEquals(clients.get(0).getFirstName(), expClient.getFirstName());
        assertEquals(clients.get(0).getLastName(), expClient.getLastName());
        assertEquals(clients.get(0), expClient);
    }

    @Test
    public void findByIdExceptional() {
        Optional<Client> optionalClient = clientDao.findById(999);
        assertTrue(optionalClient.isEmpty());
    }

    @Test
    public void createClient() {
        List<Client> clients = clientDao.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);

        clientDao.create(new Client("Ihor", "Dmitriev", 101));

        List<Client> realClient = clientDao.findAll();
        assertEquals(clients.size() + 1, realClient.size());
    }

    @Test
    public void updateClient() {
        List<Client> clients = clientDao.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);

        Client client = clients.get(0);
        client.setLastName(RandomStringUtils.randomAlphabetic(LAST_NAME_SIZE));
        client.setFirstName(RandomStringUtils.randomAlphabetic(FIRST_NAME_SIZE));
        client.setTourId(101);
        clientDao.update(client);

        Optional<Client> realClient = clientDao.findById(client.getClientId());
        assertEquals(realClient.get().getLastName(), client.getLastName());
        assertEquals(realClient.get().getFirstName(), client.getFirstName());
        assertEquals(realClient.get().getTourId(), client.getTourId());
    }

    @Test
    public void deleteClient() {
        Client client = new Client();
        client.setFirstName(RandomStringUtils.randomAlphabetic(FIRST_NAME_SIZE));
        client.setLastName(RandomStringUtils.randomAlphabetic(LAST_NAME_SIZE));
        client.setTourId(101);
        Integer id = clientDao.create(client);
        assertNotNull(id);

        List<Client> clients = clientDao.findAll();
        assertNotNull(clients);

        int result = clientDao.delete(id);

        assertTrue(1 == result);

        List<Client> realClients = clientDao.findAll();
        assertNotNull(realClients);

        assertTrue(clients.size()-1 == realClients.size());
    }


}
