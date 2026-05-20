package com.ifms.lp3spring.model.conta;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@PrimaryKeyJoinColumn(name = "idConta")
@Table(name = "conta_poupanca")
public class PoupancaModel extends ContaModel {

    @PositiveOrZero(message = "O rendimento deve ser maior ou igual a zero")
    private double rendimento;

    protected PoupancaModel() {}

    // Construtor corrigido sem o parâmetro idConta
    public PoupancaModel(double saldoAtual, double fatura, double rendimento) {
        super(saldoAtual, fatura);
        this.rendimento = rendimento;
    }

    public double getRendimento() { return rendimento; }
    public void setRendimento(double rendimento) { this.rendimento = rendimento; }
}
