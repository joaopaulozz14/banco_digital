package com.ifms.lp3spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.service.ClienteService;

import jakarta.validation.Valid;

@Controller
public class ClienteController {
	@Autowired
	ClienteService clienteService;

	// Listagem de clientes
	@GetMapping("/buscarcliente")
	public ModelAndView listar() throws Exception {
	    List<ClienteModel> clientes =
	            clienteService.listarTodos();

	    return new ModelAndView(
	            "cliente/buscarcliente",
	            "clientes",
	            clientes
	    );
	}
	
	// Mostra o formulario para salvar cliente
	@GetMapping("/salvarcliente")
	public ModelAndView getSalvar() {
		return new ModelAndView("cliente/salvarcliente", "cliente", new ClienteModel());
	}
	
	// Processa acao de salvar
	@PostMapping("/salvarcliente")
	public String postSalvar(
	        @Valid @ModelAttribute("cliente") ClienteModel cliente,
	        BindingResult result) {

	    if (result.hasErrors()) {
	        return "cliente/salvarcliente";
	    }

	    try {

	        clienteService.inserir(cliente);

	        return "redirect:/buscarcliente";

	    } catch (Exception e) {

	        result.reject("erro", e.getMessage());

	        return "cliente/salvarcliente";
	    }
	}
	
	//Remover cliente pelo id;
    @GetMapping("/removercliente/{id}")
    public String deletar(@ModelAttribute("id") Long id) {
        try {
			clienteService.remover(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/buscarcliente";
    }
    
    //Mostrar form de edicao
    @GetMapping("/editarcliente/{id}")
    public ModelAndView buscarPorId(@ModelAttribute("id") Long id) throws Exception {
        ClienteModel cliente = clienteService.buscarId(id);

        if (cliente==null) {
            cliente = new ClienteModel();
        }
        return new ModelAndView("/cliente/editarcliente", "cliente", cliente);
    }
    
    //Processar edicao
    @PostMapping("/editarcliente")
    public String postEditar(@Valid @ModelAttribute("cliente") ClienteModel cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente/editarcliente";
        }
        
        try {
			clienteService.editar(cliente);
		} catch (Exception e) {
	        result.reject("erro", e.getMessage());
	        return "cliente/editarcliente";
		}
        return "redirect:/buscarcliente";
    }
    
    //Mostrar Relacionamento
    @GetMapping("/detalhescliente/{id}")
    public ModelAndView detalhesCliente(@ModelAttribute("id") Long id) throws Exception {

        ClienteModel cliente = clienteService.buscarId(id);

        ModelAndView mv =
                new ModelAndView(
                        "cliente/detalhescliente"
                );

        mv.addObject("cliente", cliente);
        mv.addObject(
                "saldoTotal",
                clienteService.calcularSaldoTotal(cliente)
        );

        mv.addObject(
                "limiteTotal",
                clienteService.calcularLimiteTotal(cliente)
        );

        return mv;
        
        // Fluxo: mapeamento em detalhescliente/id -> ModelAndView (Recebe parametros: 
        // 'arquivo html', 'dados enviados') -> Usa mv.addObject para chamar services especificas
    }


}