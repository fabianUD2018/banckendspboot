package com.mvc.daos;

import org.springframework.data.repository.CrudRepository;

import com.mvc.entities.Cliente;

public interface IClientesDAO extends CrudRepository<Cliente, Integer> {

}
