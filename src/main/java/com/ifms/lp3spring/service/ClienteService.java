package com.ifms.lp3spring.service;

import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.repository.ClienteRepository;
import com.ifms.lp3spring.strategy.CalculoLimiteFactory;
import com.ifms.lp3spring.strategy.CalculoLimiteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // INSERIR (Utilizando o Strategy para calcular um limite fictício baseado na renda)
    public ClienteModel inserir(ClienteModel cliente) throws Exception {
        try {
            // Aplicação do padrão Strategy antes de salvar
            CalculoLimiteStrategy strategy = CalculoLimiteFactory.obterEstrategia(cliente.getRenda());
            double limiteCalculado = strategy.calcular(cliente.getRenda());
            
            System.out.println("Limite de crédito sugerido pelo Strategy: R$ " + limiteCalculado);
            // como salvar esse limite em uma conta corrente associada?

            return clienteRepository.save(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error");
        }
    }

    // LISTAR
    public List<ClienteModel> listarTodos() throws Exception {
        try {
            return clienteRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Erro ao listar");
        }
    }

    // REMOVER
    public void remover(Long id) throws Exception {
        try {
            // Verifica se o cliente existe antes de tentar deletar
            if (clienteRepository.existsById(id)) {
                clienteRepository.deleteById(id);
            } else {
                throw new Exception("Cliente inexistente. Não pôde ser removido.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
            throw new Exception("Falha na exclusão do cliente.");
        }
    }
    
    // BUSCAR PELO ID
	public ClienteModel buscarId(long id) throws Exception {
		try {
			return clienteRepository.findById(id).orElse(null);
		}catch(Exception e) {
			throw new Exception("Error");
		}
	}

    //Getters e Setters
	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
    
    
}
