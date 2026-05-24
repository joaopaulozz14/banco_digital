package com.ifms.lp3spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifms.lp3spring.model.conta.ContaModel;

@Repository
public interface ContaRepository
        extends JpaRepository<ContaModel, Long> {
}
