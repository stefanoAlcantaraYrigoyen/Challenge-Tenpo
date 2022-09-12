package com.challange.tenpo.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExternalPorcentage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "porcentage")
	private Double porcentage;

	public ExternalPorcentage(Double porcentage) {
		this.porcentage = porcentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPorcentage() {
		return porcentage;
	}

	public void setPorcentage(Double porcentage) {
		this.porcentage = porcentage;
	}
	
	
}
