package com.ifms.lp3spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.service.ClienteService;

import jakarta.validation.Valid;

@Controller
public class ClienteController {
	@Autowired
	ClienteService clienteService;

	// Cadastrar Clientes
	@PostMapping("/clientes")
	public String salvar(@Valid ClienteModel cliente) throws Exception {
		clienteService.inserir(cliente);
        return "redirect:/salvaraluno";

	}
	
	//Dar Commit amanhça

	// Listar Clientes
	@GetMapping("/cliente")
	public List<ClienteModel> index() throws Exception {
		try {
			List<ClienteModel> cliente = clienteService.listarTodos();
			return cliente;
		} catch (Exception e) {
			throw new Exception("error");
		}
	}
	
	// Deletar Clientes
	@DeleteMapping("/deleteprofessor/{id}")
	public String deletar(Long id) throws Exception{
		try {
			clienteService.remover(id);
			return "redirect:/salvaraluno";
		}catch(Exception e) {
			throw new Exception("Error");
		}
	}
	
	// Buscar por ID
	@GetMapping("/cliente/{id}")
	public ClienteModel buscarId(Long id) throws Exception{
		try {
			ClienteModel cliente = clienteService.buscarId(id);
			return cliente;
		}catch(Exception e) {
			throw new Exception("Erro");
		}
	}

	
	

}
