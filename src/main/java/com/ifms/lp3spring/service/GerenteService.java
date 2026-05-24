package com.ifms.lp3spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.model.pessoa.GerenteModel;
import com.ifms.lp3spring.repository.GerenteRepository;

@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;
    
    public GerenteModel inserir(GerenteModel gerente) throws Exception {
        try {
            return gerenteRepository.save(gerente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public List<GerenteModel> listarTodos() throws Exception{
    	 try {
             return gerenteRepository.findAll();
         } catch (Exception e) {
             System.out.println(e.getMessage());
             throw new Exception("Erro ao listar");
         }
    }
    
    public GerenteModel buscarId(Long id) throws Exception {
		try {
			return gerenteRepository.findById(id).orElse(null);
		}catch(Exception e) {
			throw new Exception("Error");
		}
    }
    
    public void remover(Long id) throws Exception {
        if (!gerenteRepository.existsById(id)) {
            throw new Exception("Cliente inexistente");
        }

        gerenteRepository.deleteById(id);
    }
}
