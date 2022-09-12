package com.challange.tenpo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challange.tenpo.entitys.ExternalPorcentage;

public interface ExternalPorcentageRepository extends JpaRepository<ExternalPorcentage, Long>{

	ExternalPorcentage findTopByOrderByIdDesc();
}
