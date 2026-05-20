package com.ifms.lp3spring.model.conta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta")

public abstract class ContaModel {
    @Id
    @GeneratedValue
    private Long idConta;


    private double saldo_atual;

    private double fatura;

    public ContaModel(){}
    public ContaModel(Long idConta, double saldo_atual, double fatura) {
        this.idConta = idConta;
        this.saldo_atual = saldo_atual;
        this.fatura = fatura;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public double getSaldo_atual() {
        return saldo_atual;
    }

    public void setSaldo_atual(double saldo_atual) {
        this.saldo_atual = saldo_atual;
    }

    public double getFatura() {
        return fatura;
    }

    public void setFatura(double fatura) {
        this.fatura = fatura;
    }

    
}
