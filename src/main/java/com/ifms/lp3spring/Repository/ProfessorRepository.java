package com.ifms.lp3spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifms.lp3spring.model.pessoa.GerenteModel;

@Repository
public interface ProfessorRepository extends JpaRepository<GerenteModel, Long> {
    
}
