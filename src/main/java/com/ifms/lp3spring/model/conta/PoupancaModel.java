package com.ifms.lp3spring.model.conta;

import jakarta.persistence.Entity;

import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "idConta")
@Table(name = "conta_poupanca")
public class PoupancaModel extends ContaModel {

    @NotNull
    private double rendimento;

   

    public PoupancaModel(Long idConta, double saldo_atual, double fatura, @NotNull double rendimento) {
        super(idConta, saldo_atual, fatura);
        this.rendimento = rendimento;
    }
    public PoupancaModel() {

    }

}
