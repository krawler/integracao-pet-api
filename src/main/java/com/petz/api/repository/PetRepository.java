package com.petz.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petz.api.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	
}
