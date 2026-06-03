package com.ifms.lp3spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ifms.lp3spring.model.conta.ContaModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.service.CartaoService;
import com.ifms.lp3spring.service.ClienteService;

@Controller
public class CartaoController {
    @Autowired
    CartaoService cartaoService;

    @Autowired
    ClienteService clienteService;
    
    // FORMULARIO PARA CONFIRMAR A CRIACAO DE CARTAO DE DEBITO
    @GetMapping("/criarcartao/{id}")
    public ModelAndView criarCartao(@PathVariable Long id) throws Exception {
        
        ClienteModel cliente = clienteService.buscarId(id);

        ModelAndView mv = new ModelAndView("criarcartao");

        mv.addObject("idCliente", id);

        return mv;
    }

    // PROCESSAR A CRIACAO DE CARTAO DE DEBITO

}
