package com.ifms.lp3spring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.Repository.AlunoRepository;
import com.ifms.lp3spring.model.AlunoModel;


@Service
public class AlunoService {
     @Autowired
    private AlunoRepository alunoRepository;

    public String inserir (AlunoModel aluno) {
        try {
            alunoRepository.save(aluno);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Salvo com Sucesso";
    }
    
    public AlunoRepository getAlunoRepository() {
        return alunoRepository;
    }

    public void setAlunoRepository(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

}
