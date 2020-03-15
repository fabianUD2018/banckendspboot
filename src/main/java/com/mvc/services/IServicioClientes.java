package com.mvc.services;

import java.util.List;

import com.mvc.entities.Cliente;

public interface IServicioClientes  {
	public List <Cliente> findAll();

	public Cliente findById(Integer id) ;
	public Cliente saveCliente (Cliente c) ;
	public void deleteCliente(int c) ;
	public void deleteCliente(Cliente c) ;
}
