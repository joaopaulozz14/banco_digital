package com.ifms.lp3spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.model.conta.ContaModel;
import com.ifms.lp3spring.model.conta.CorrenteModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.repository.ContaRepository;
import com.ifms.lp3spring.strategy.CalculoLimiteFactory;
import com.ifms.lp3spring.strategy.CalculoLimiteStrategy;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CartaoService cartaoService;

    public void depositar(Long idConta, double valor) throws Exception {

        ContaModel conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new Exception("Conta não encontrada"));

        conta.depositar(valor);

        contaRepository.save(conta);
    }

    public void sacar(Long idConta, double valor) throws Exception {

        ContaModel conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new Exception("Conta não encontrada"));

        conta.sacar(valor);

        contaRepository.save(conta);
    }

    // Cria Conta Padrão para o cliente, aplicando o padrão Strategy para calcular o limite com base na renda
    public void criarContaInicial(ClienteModel cliente) {
        // Aplicação do padrão Strategy
        CalculoLimiteStrategy strategy = CalculoLimiteFactory.obterEstrategia(cliente.getRenda());
        double limiteCalculado = strategy.calcular(cliente.getRenda());

        // Criar contaCorrente associada padrão e associar ao cliente
        CorrenteModel conta = new CorrenteModel(0.0, 0.0, limiteCalculado);
        cliente.adicionarConta(conta);

        // Cria o cartão vinculado a esta conta
        cartaoService.criarCartaoInicial(conta, limiteCalculado);
    }

}