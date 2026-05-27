package com.ifms.lp3spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.service.ClienteService;
import com.ifms.lp3spring.service.ContaService;

@Controller
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/depositar")
    public String depositar(
            @RequestParam Long idConta,
            @RequestParam double valor,
            @RequestParam Long idCliente) {

        try {

            contaService.depositar(idConta, valor);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/detalhescliente/" + idCliente;

        // Fluxo: Mapear requisicao POST na URL: /depositar -> Capturar Valores do
        // Formulario ->
        //
    }

    @PostMapping("/sacar")
    public String sacar(
            @RequestParam Long idConta,
            @RequestParam double valor,
            @RequestParam Long idCliente) {

        try {

            contaService.sacar(idConta, valor);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/detalhescliente/" + idCliente;
    }

    // FORMULÁRIO PARA CRIAR CONTA POUPANÇA
    @GetMapping("/criarpoupanca/{id}")
    public ModelAndView abrirCriacaoConta(@PathVariable Long id) throws Exception {

        ClienteModel cliente = clienteService.buscarId(id);

        ModelAndView mv = new ModelAndView("conta/criarconta");

        mv.addObject("cliente", cliente);

        return mv;
    }

    // PROCESSA CRIAÇÃO
    @PostMapping("/criarpoupanca")
    public String criarConta(@RequestParam Long idCliente) throws Exception {

        contaService.criarContaPoupanca(idCliente);

        return "redirect:/detalhescliente/" + idCliente;
    }
}
