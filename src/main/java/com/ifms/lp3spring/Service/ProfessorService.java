package com.ifms.lp3spring.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.Repository.ProfessorRepository;
import com.ifms.lp3spring.model.ProfessorModel;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public String inserir (ProfessorModel professor) {
        try {
            professorRepository.save(professor);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Salvo com Sucesso";
    }
    
    public List<ProfessorModel> buscarTodos() {
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
    
    public ProfessorModel buscarPorId(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    public ProfessorRepository getProfessorRepository() {
        return professorRepository;
    }

    public void setProfessorRepository(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


}
