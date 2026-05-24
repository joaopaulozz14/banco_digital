package com.ifms.lp3spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifms.lp3spring.model.conta.ContaModel;
import com.ifms.lp3spring.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public void depositar(
            Long idConta,
            double valor) throws Exception {

        ContaModel conta =
                contaRepository.findById(idConta)
                .orElseThrow(() ->
                        new Exception("Conta não encontrada"));

        conta.depositar(valor);

        contaRepository.save(conta);
    }
    
    public void sacar(
            Long idConta,
            double valor) throws Exception {

        ContaModel conta =
                contaRepository.findById(idConta)
                .orElseThrow(() ->
                        new Exception("Conta não encontrada"));

        conta.sacar(valor);

        contaRepository.save(conta);
    }
}