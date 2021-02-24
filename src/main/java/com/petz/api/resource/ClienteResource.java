package com.petz.api.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petz.api.model.Cliente;
import com.petz.api.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente add(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	@GetMapping("list")
	public List<Cliente> list(){
		return repository.findAll();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Cliente> get(@PathVariable Long id) {

		Optional<Cliente> optionalCliente = repository.findById(id);
		
		if (!optionalCliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliente = optionalCliente.get();		
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente existing = repository.getOne(id);
		
		if (existing == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(cliente, existing, "id");
		
		existing = repository.save(existing);
		
		return ResponseEntity.ok(existing);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		Cliente cliente = repository.getOne(id);
		
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		repository.delete(cliente);
		
		return ResponseEntity.noContent().build();
	}
}
