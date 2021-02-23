package com.petz.api.resource;

import java.util.List;

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
import com.petz.api.model.Pet;
import com.petz.api.repository.ClienteRepository;
import com.petz.api.repository.PetRepository;

@RestController
@RequestMapping("/pets")
public class PetResource {

	@Autowired
	private PetRepository repository;
	
	@Autowired
	private ClienteRepository ClienteRepository;
	
	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public Pet add(@RequestBody Pet pet) {
		Cliente cliente = ClienteRepository.getOne(pet.getCliente().getId());
		pet.setCliente(cliente);
		return repository.save(pet);
	}
	
	@GetMapping("list")
	public List<Pet> list(){
		return repository.findAll();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Pet> view(@PathVariable Long id) {
		
		Pet pet = repository.getOne(id);
		
		if (pet == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pet);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Pet> update(@PathVariable Long id, @Valid @RequestBody Pet contato) {
		Pet existing = repository.getOne(id);
		
		if (existing == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(contato, existing, "id");
		
		existing = repository.save(existing);
		
		return ResponseEntity.ok(existing);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		Pet pet = repository.getOne(id);
		
		if (pet == null) {
			return ResponseEntity.notFound().build();
		}
		
		repository.delete(pet);
		
		return ResponseEntity.noContent().build();
	}
}
