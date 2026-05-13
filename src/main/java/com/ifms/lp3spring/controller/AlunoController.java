package com.ifms.lp3spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifms.lp3spring.Service.AlunoService;
import com.ifms.lp3spring.model.AlunoModel;

import jakarta.validation.Valid;

@Controller
public class AlunoController {
    
 @Autowired
    private AlunoService alunoService;

    @GetMapping("/salvaraluno")
    public ModelAndView getSalvar() {
        return new ModelAndView("aluno/salvaraluno", "aluno", new AlunoModel());
    }
    
    @PostMapping("/salvaraluno")
    public String postSalvar(@Valid @ModelAttribute("aluno") AlunoModel aluno, BindingResult result) {
        if (result.hasErrors()) {
            return "aluno/salvaraluno";
        }
        
        alunoService.inserir(aluno);
        return "redirect:/salvaraluno";
    }  

    public AlunoService getAlunoService() {
        return alunoService;
    }

    public void setAlunoService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
}
