package com.mvc.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.mvc.entities.Cliente;

public interface IClientesDAO extends JpaRepository<Cliente, Integer> {

}
