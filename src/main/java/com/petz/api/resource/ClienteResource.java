package com.petz.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petz.api.model.Cliente;
import com.petz.api.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping("add")
	public Cliente add(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	@GetMapping("list")
	public List<Cliente> list(){
		return repository.findAll();
	}

}
