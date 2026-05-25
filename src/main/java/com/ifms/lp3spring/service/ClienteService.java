package com.ifms.lp3spring.service;

import com.ifms.lp3spring.model.cartao.CreditoModel;
import com.ifms.lp3spring.model.conta.ContaModel;
import com.ifms.lp3spring.model.conta.CorrenteModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.repository.ClienteRepository;
import com.ifms.lp3spring.strategy.CalculoLimiteFactory;
import com.ifms.lp3spring.strategy.CalculoLimiteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // INSERIR (Utilizando o Strategy para calcular um limite fictício baseado na renda)
    public ClienteModel inserir(ClienteModel cliente) throws Exception {
        try {
            // Aplicação do padrão Strategy antes de salvar (calcular limite)
            CalculoLimiteStrategy strategy = CalculoLimiteFactory.obterEstrategia(cliente.getRenda());
            double limiteCalculado = strategy.calcular(cliente.getRenda());
            
            // Criar contaCorrente associada padrão e associar ao cliente
            CorrenteModel conta = new CorrenteModel(0.0, 0.0, limiteCalculado);
            cliente.adicionarConta(conta);
            
            // Criar cartaoCredito padrão associada a conta;
      		String numero = "4000" + (1000 + new Random().nextInt(9000));
            String cvv = String.valueOf(100 + new Random().nextInt(900));
      		
            CreditoModel cartao = new CreditoModel(
                    LocalDate.now().plusYears(5),
                    cvv,
                    numero,
                    conta,
                    limiteCalculado
            		);
    		conta.adicionarCartao(cartao);

            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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

        if (!clienteRepository.existsById(id)) {
            throw new Exception("Cliente inexistente");
        }

        clienteRepository.deleteById(id);
    }

    
    // BUSCAR PELO ID
	public ClienteModel buscarId(Long id) throws Exception {
		try {
			return clienteRepository.findById(id).orElse(null);
		}catch(Exception e) {
			throw new Exception("Error");
		}
	}
	
	// Editar
	public ClienteModel editar(ClienteModel cliente) throws Exception {

	    ClienteModel clienteBanco =
	            clienteRepository.findById(cliente.getIdPessoa())
	                    .orElseThrow(() ->
	                            new Exception("Cliente não encontrado"));

	    clienteBanco.setNome(cliente.getNome());
	    clienteBanco.setCpf(cliente.getCpf());
	    clienteBanco.setTelefone(cliente.getTelefone());
	    clienteBanco.setDataNascimento(cliente.getDataNascimento());
	    clienteBanco.setRenda(cliente.getRenda());

	    return clienteRepository.save(clienteBanco);
	}
	
	
	// Calcular Saldo do cliente 
	public double calcularSaldoTotal(
	        ClienteModel cliente) {

	    return cliente.getContas()
	            .stream()
	            .mapToDouble(ContaModel::getSaldoAtual)
	            .sum(); 
	    // lista de contas -> converte lista em fluxo de dados -> extrai o valor de saldo de todas as contas
	    // -> soma -> RESULTADO: Saldo de todas as contas
	}
	
	
	// Calcular Limite do cliente;
	public double calcularLimiteTotal(
	        ClienteModel cliente) {

	    return cliente.getContas()
	            .stream()
	            .mapToDouble(ContaModel::getLimite)
	            .sum();
	    // mesma logica, mas soma o limite ao inves do saldo.
	}

    //Getters e Setters
	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
    
    
}