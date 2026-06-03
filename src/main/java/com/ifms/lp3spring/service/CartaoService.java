package com.ifms.lp3spring.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.model.cartao.CreditoModel;
import com.ifms.lp3spring.model.conta.CorrenteModel;
import com.ifms.lp3spring.repository.CartaoRepository;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartao;

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

    public void criarCartaoDebito(CorrenteModel conta) {
        Random random = new Random();
        String numero = "5000" + (1000 + random.nextInt(9000));
        String cvv = String.valueOf(100 + random.nextInt(900));

        CreditoModel cartao = new CreditoModel(
                LocalDate.now().plusYears(5),
                cvv,
                numero,
                conta,
                0); // Limite zero para cartão de débito
        conta.adicionarCartao(cartao);
    }
}
