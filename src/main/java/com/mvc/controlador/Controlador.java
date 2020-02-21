package com.mvc.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.entities.Cliente;
import com.mvc.services.*;
@RestController
//@Controller
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200")
public class Controlador {
	@Autowired
	private IServicioClientes service;
	
	
	@RequestMapping ("/clientes")
	//@GetMapping()
	public  List<Cliente> getClientes () {
		return service.findAll();
	}
	
	@RequestMapping("/clientes/{id}")
	public Cliente findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping("/postclient")
	public void saveCliente (@RequestBody Cliente c) {
		 service.saveCliente(c);
	}
	
	@DeleteMapping("/deleteClient")
	public void deleteCliente(@RequestParam(name="borrar", required=true) int id) {
		service.deleteCliente(id);
	}
	
	@PutMapping ("/putClient/{id}")
	public Cliente putCliente (@RequestBody Cliente c, @PathVariable Integer id) {
		System.out.println("fl 1");
		Cliente temp = service.findById(id);
		temp.setApellido(c.getApellido());
		temp.setNombre(c.getNombre());
		temp.setEmail(c.getEmail());
		return temp;
	}

}


