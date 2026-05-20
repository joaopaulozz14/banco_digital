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
    private double chequeEspecial; // Alterado de String para double para fazer sentido financeiro

    protected CorrenteModel() {}

    // Construtor corrigido sem o parâmetro idConta
    public CorrenteModel(double saldoAtual, double fatura, double chequeEspecial) {
        super(saldoAtual, fatura);
        this.chequeEspecial = chequeEspecial;
    }

    public double getChequeEspecial() { return chequeEspecial; }
    public void setChequeEspecial(double chequeEspecial) { this.chequeEspecial = chequeEspecial; }    
}
