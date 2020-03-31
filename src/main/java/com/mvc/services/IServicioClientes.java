package com.mvc.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mvc.entities.Cliente;

public interface IServicioClientes  {
	public List <Cliente> findAll();
	public Page <Cliente> findAll(Pageable p);
	public Cliente findById(Integer id) ;
	public Cliente saveCliente (Cliente c) ;
	public void deleteCliente(int c) ;
	public void deleteCliente(Cliente c) ;
}
