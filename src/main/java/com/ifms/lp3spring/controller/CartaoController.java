package com.ifms.lp3spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.service.CartaoService;
import com.ifms.lp3spring.service.ClienteService;

@Controller
public class CartaoController {

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private CartaoService cartaoService;
    
    @GetMapping("/criarcartao/{idCliente}")
    public ModelAndView abrirCriacaoCartao(
            @PathVariable Long idCliente) {

        ModelAndView mv =
                new ModelAndView("cartao/criarcartao");

        mv.addObject("idCliente", idCliente);

        return mv;
    }

    @PostMapping("/criarcartao/{idCliente}")
    public String criarCartaoDebito(
            @PathVariable Long idCliente)
            throws Exception {

        cartaoService.criarCartaoDebito(idCliente);

        return "redirect:/detalhescliente/" + idCliente;
    }
}