package com.mvc.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.entities.Cliente;
import com.mvc.services.*;
@RestController
//@Controller
@RequestMapping("/api")
//@CrossOrigin(origins ="http://localhost:4200")
@CrossOrigin (origins ="*")
public class Controlador {
	@Autowired
	private IServicioClientes service;
	
	
	//@RequestMapping (method =RequestMethod.GET , path = "/clientes")
	@GetMapping("/clientes")
	public  List<Cliente> getClientes () {
		return service.findAll();
	}
	
	/*@RequestMapping("/clientes/{id}")
	public Cliente findById(@PathVariable Integer id) {
		return service.findById(id);
	}*/
	
	@RequestMapping("/clientes/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Cliente c = null;
		Map<String, Object> errores = new HashMap<>();
		try {
			c= service.findById(id);
		}
		catch (DataAccessException e ) {
			errores.put("mensaje", "error al realizar consulta en la base sde datos ");
			return new ResponseEntity<Map<String, Object>>(errores, HttpStatus.NOT_FOUND);
		}
		
		
		
		if (c ==null) {
			
			errores.put("mensaje", "error no se encuentra el cliente con id  " +id );

			return new ResponseEntity<Map<String, Object>>(errores, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(service.findById(id), HttpStatus.OK);
		
	}
	
	@PostMapping("/postclient")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveCliente (@Valid @RequestBody Cliente c, BindingResult br) {
		
		Map<String, Object> response = new HashMap<>();
		if (br.hasErrors()) {
			/*List <String> errores = new ArrayList<String>();
			for (FieldError err : br.getFieldErrors()) {
				errores.add("EL CAMPO "+ err.getField() +" "+ err.getDefaultMessage());
			}*/
			List<String> errores = br.getFieldErrors()
			.stream().map(err ->{
				return "EL CAMPO "+ err.getField() +" "+ err.getDefaultMessage();
			}).collect(Collectors.toList());
			
			response.put("errors", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			service.saveCliente(c);
			}
		catch (DataAccessException e ) {
			response.put("mensaje", "error al realizar consulta en la base sde datos ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_GATEWAY);
		}
		
		 return  new ResponseEntity<Cliente>(c, HttpStatus.CREATED);
		 
	}
	
	@DeleteMapping("/deleteClient")	
	@ResponseStatus(HttpStatus.OK)
	public void deleteCliente(@RequestParam(name="borrar", required=true) int id) {
		service.deleteCliente(id);
	}
	
	@PutMapping ("/putClient/{id}")
	public Cliente putCliente (@RequestBody Cliente c, @PathVariable Integer id) {
		Cliente temp = service.findById(id);
		temp.setApellido(c.getApellido());
		temp.setNombre(c.getNombre());
		temp.setEmail(c.getEmail());
		service.saveCliente(temp);
		
		return temp;
	}
	
	@GetMapping("/clientes/page/{n}")
	public  Page<Cliente> getClientes (@PathVariable int n ) {
		return service.findAll(PageRequest.of(n, 5));
	}

}


