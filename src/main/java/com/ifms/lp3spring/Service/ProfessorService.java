package com.ifms.lp3spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.model.pessoa.GerenteModel;
import com.ifms.lp3spring.repository.ProfessorRepository;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public String inserir (GerenteModel professor) {
        try {
            professorRepository.save(professor);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Salvo com Sucesso";
    }
    
    public List<GerenteModel> buscarTodos() {
        return professorRepository.findAll();
    }

    public String remover(Long id) {
        try {
            professorRepository.deleteById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Removido com Sucesso";
    }
    
    public GerenteModel buscarPorId(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    public ProfessorRepository getProfessorRepository() {
        return professorRepository;
    }

    public void setProfessorRepository(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


}
