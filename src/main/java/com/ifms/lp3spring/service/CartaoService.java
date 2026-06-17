package com.ifms.lp3spring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.model.cartao.CreditoModel;
import com.ifms.lp3spring.model.cartao.DebitoModel;
import com.ifms.lp3spring.model.conta.ContaModel;
import com.ifms.lp3spring.model.conta.CorrenteModel;
import com.ifms.lp3spring.model.conta.PoupancaModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.repository.CartaoRepository;
import com.ifms.lp3spring.repository.ClienteRepository;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository cartao;

	@Autowired
	ClienteRepository clienteRepository;

	// Gerar um cartão de crédito inicial para a conta corrente, com número e CVV aleatórios, e validade de 5 anos
	public void criarCartaoInicial(CorrenteModel conta, double limite) {
		Random random = new Random();
		String numero = "4000" + (1000 + random.nextInt(9000));
		String cvv = String.valueOf(100 + random.nextInt(900));

		CreditoModel cartao = new CreditoModel(
				LocalDate.now().plusYears(5),
				cvv,
				numero,
				conta,
				limite);
		conta.adicionarCartao(cartao);
	}

	public Long criarCartaoDebito(Long idCliente)
			throws Exception {

		ClienteModel cliente =
				clienteRepository.findById(idCliente)
				.orElseThrow(() ->
				new Exception("Cliente não encontrado"));

		// Busca conta poupança
		PoupancaModel poupanca =
				cliente.getContas()
				.stream()
				.filter(conta ->
				conta instanceof PoupancaModel)
				.map(conta ->
				(PoupancaModel) conta)
				.findFirst()
				.orElseThrow(() ->
				new Exception("Cliente não possui conta poupança"));

		// Verifica se já possui cartão débito
		boolean possuiDebito =
				poupanca.getCartoes()
				.stream()
				.anyMatch(cartao ->
				cartao instanceof DebitoModel);

		if (possuiDebito) {
			throw new Exception(
					"Conta já possui cartão débito");
		}

		Random random = new Random();

		String numero =
				"5000" +
						(1000 + random.nextInt(9000));

		String cvv =
				String.valueOf(
						100 + random.nextInt(900));

		DebitoModel cartao =
				new DebitoModel(
						LocalDate.now().plusYears(5),
						cvv,
						numero,
						poupanca);

		poupanca.adicionarCartao(cartao);

		clienteRepository.save(cliente);
		return idCliente;
	}
}

