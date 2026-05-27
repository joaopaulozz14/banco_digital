package com.ifms.lp3spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ifms.lp3spring.model.conta.ContaModel;
import com.ifms.lp3spring.model.conta.CorrenteModel;
import com.ifms.lp3spring.model.conta.PoupancaModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import com.ifms.lp3spring.repository.ClienteRepository;
import com.ifms.lp3spring.repository.ContaRepository;
import com.ifms.lp3spring.strategy.CalculoLimiteFactory;
import com.ifms.lp3spring.strategy.CalculoLimiteStrategy;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteRepository clienteRepository;

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


    public void criarContaPoupanca(Long idCliente) throws Exception {

        ClienteModel cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new Exception("Cliente não encontrado"));

        // Verifica se já possui conta poupança
        boolean possuiPoupanca =
                cliente.getContas()
                        .stream()
                        .anyMatch(conta ->
                                conta instanceof
                                        PoupancaModel);

        if (possuiPoupanca) {
            throw new Exception("Cliente já possui conta poupança");
        }

        // Criação da conta
        PoupancaModel poupanca = new PoupancaModel(0.0, 0.0,0.05);

        // Associa ao cliente
        cliente.adicionarConta(poupanca);

        // Salva
        clienteRepository.save(cliente);
    }
}

