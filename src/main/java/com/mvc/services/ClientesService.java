package com.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.daos.IClientesDAO;
import com.mvc.entities.Cliente;

@Service
public class ClientesService implements IServicioClientes {
	
	@Autowired 
	IClientesDAO clienteDAO;
	
	@Transactional (readOnly = true)
	public List <Cliente> findAll(){
		return (List<Cliente>) clienteDAO.findAll();
	}
	
	public Page <Cliente> findAll(Pageable p){
		return  clienteDAO.findAll(p);
	}
	
	public Cliente findById(Integer id) {
		return clienteDAO.findById(id).orElse(null);
	}
	
	public Cliente saveCliente (Cliente c) {
		
		return clienteDAO.save(c);
		
	}
	
	public void deleteCliente(Cliente c) {
		clienteDAO.delete(c);
	}

	public void deleteCliente(int id) {
		clienteDAO.deleteById(id);
	}
}
