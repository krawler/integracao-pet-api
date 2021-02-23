package com.petz.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Cliente {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@OneToMany(targetEntity=Pet.class,
			   mappedBy="cliente",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Pet> pets;	
}
