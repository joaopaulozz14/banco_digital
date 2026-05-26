package com.ifms.lp3spring.service;

import com.ifms.lp3spring.model.conta.ContaModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.repository.ClienteRepository;


import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
    private ContaService contaService;

	// INSERIR - cria 'cliente' e delega a criação da conta e do cartão para o serviço de conta, garantindo que tudo seja salvo em uma única transação.
	@Transactional // Garante que se algo falhar, nada é salvo no banco
    public ClienteModel inserir(ClienteModel cliente) throws Exception {
        try {
            // Delega a criação da conta e do cartão para o serviço responsável
            contaService.criarContaInicial(cliente);

            // Salva o cliente. Se o cascade estiver ativo nas entidades, salvará conta e cartão juntos.
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception("Erro ao inserir cliente: " + e.getMessage());
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
		} catch (Exception e) {
			throw new Exception("Error");
		}
	}

	// Editar
	public ClienteModel editar(ClienteModel cliente) throws Exception {

		ClienteModel clienteBanco = clienteRepository.findById(cliente.getIdPessoa())
				.orElseThrow(() -> new Exception("Cliente não encontrado"));

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
		// lista de contas -> converte lista em fluxo de dados -> extrai o valor de
		// saldo de todas as contas
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

	// Getters e Setters
	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

}