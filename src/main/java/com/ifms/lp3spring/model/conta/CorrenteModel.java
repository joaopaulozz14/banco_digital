package com.ifms.lp3spring.model.conta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@PrimaryKeyJoinColumn(name = "idConta")
@Table(name = "conta_corrente")
public class CorrenteModel extends ContaModel {
    
    @PositiveOrZero(message = "O cheque especial deve ser maior ou igual a zero")
    @Column(name = "chequeEspecial")
    private double chequeEspecial;

    protected CorrenteModel() {}

    public CorrenteModel(double saldoAtual, double fatura, double chequeEspecial) {
        super(saldoAtual, fatura);
        this.chequeEspecial = chequeEspecial;
    }
    
    // Polimorfismo para armazenar limite na conta
    @Override
    public double getLimite() {
        return chequeEspecial;
    }

    public double getChequeEspecial() { return chequeEspecial; }
    public void setChequeEspecial(double chequeEspecial) { this.chequeEspecial = chequeEspecial; }    
}
