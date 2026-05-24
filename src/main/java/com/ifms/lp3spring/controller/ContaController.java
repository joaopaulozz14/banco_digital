package com.ifms.lp3spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifms.lp3spring.service.ContaService;

@Controller
public class ContaController {

    @Autowired
    private ContaService contaService;
    
    @PostMapping("/depositar")
    public String depositar(
    		  @RequestParam Long idConta,
    	        @RequestParam double valor,
    	        @RequestParam Long idCliente) {

        try {

            contaService.depositar(idConta, valor);

        } catch(Exception e) {

            e.printStackTrace();
        }

        return "redirect:/detalhescliente/" + idCliente;
        
        // Fluxo: Mapear requisicao POST na URL: /depositar -> Capturar Valores do Formulario ->
        // 
    }
    
    @PostMapping("/sacar")
    public String sacar(
            @RequestParam Long idConta,
            @RequestParam double valor,
            @RequestParam Long idCliente) {

        try {

            contaService.sacar(idConta, valor);

        } catch(Exception e) {

            e.printStackTrace();
        }

        return "redirect:/detalhescliente/" + idCliente;
    }
}