package com.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Cliente findById(Integer id) {
		return clienteDAO.findById(id).orElse(null);
	}
	
	public void saveCliente (Cliente c) {
		clienteDAO.save(c);
	}
	
	public void deleteCliente(Cliente c) {
		clienteDAO.delete(c);
	}

	public void deleteCliente(int id) {
		clienteDAO.deleteById(id);
	}
}
