package com.ifms.lp3spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifms.lp3spring.model.pessoa.GerenteModel;
import com.ifms.lp3spring.service.GerenteService;

import jakarta.validation.Valid;

@Controller

public class GerenteController {
	@Autowired
	private GerenteService gerenteService;

	@GetMapping("/salvargerente")
	public ModelAndView getSalvar() {

		return new ModelAndView("gerente/salvargerente", "gerente", new GerenteModel());
	}

	@PostMapping("/salvargerente")
	public String postSalvar(@Valid @ModelAttribute("gerente") GerenteModel gerente, BindingResult result) {

		if (result.hasErrors()) {

			return "gerente/salvargerente";
		}

		try {

			gerenteService.inserir(gerente);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/buscargerente";
	}

	@GetMapping("/buscargerente")
	public ModelAndView buscarGerente() throws Exception {

		List<GerenteModel> gerentes = gerenteService.listarTodos();

		return new ModelAndView("gerente/buscargerente", "gerentes", gerentes);
	}

	@GetMapping("/removergerente/{id}")
	public String deletar(@ModelAttribute("id") Long id) {
		try {
			gerenteService.remover(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/buscargerente";
	}

	@GetMapping("/editargerente/{id}")
	public ModelAndView buscarPorId(@ModelAttribute("id") Long id) {
		GerenteModel gerente;
		try {
			gerente = gerenteService.buscarId(id);
			return new ModelAndView("/gerente/editargerente", "gerente", gerente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/gerente/editargerente");

	}

	@PostMapping("/editargerente")
	public String postEditar(@Valid @ModelAttribute("gerente") GerenteModel gerente, BindingResult result) {
		if (result.hasErrors()) {
			return "gerente/editargerente";
		}

		try {
			gerenteService.inserir(gerente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/buscargerente";
	}

}
