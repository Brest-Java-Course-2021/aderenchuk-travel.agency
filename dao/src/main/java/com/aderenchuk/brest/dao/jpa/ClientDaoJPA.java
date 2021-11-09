package com.aderenchuk.brest.dao.jpa;

import com.aderenchuk.brest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDaoJPA extends JpaRepository<Client, Integer> {
}
