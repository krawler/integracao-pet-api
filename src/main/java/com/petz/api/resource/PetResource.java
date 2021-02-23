package com.petz.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Pet add(@RequestBody Pet pet) {
		Cliente cliente = ClienteRepository.getOne(pet.getCliente().getId());
		pet.setCliente(cliente);
		return repository.save(pet);
	}
	
	@GetMapping("list")
	public List<Pet> list(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pet> get(@PathVariable Long id) {
		Pet pet = repository.getOne(id);
		
		if (pet == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pet);
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
