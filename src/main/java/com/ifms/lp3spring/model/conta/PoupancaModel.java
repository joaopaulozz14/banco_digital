package com.ifms.lp3spring.model.conta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "idConta")
@Table(name = "poupanca")
public class PoupancaModel extends ContaModel {

    @Id
    @GeneratedValue
    private Long idPoupanca;

    @NotNull
    private double rendimento;

   

    public PoupancaModel(Long idConta, double saldo_atual, double fatura, Long idPoupanca, @NotNull double rendimento) {
        super(idConta, saldo_atual, fatura);
        this.idPoupanca = idPoupanca;
        this.rendimento = rendimento;
    }
    public PoupancaModel() {

    }

}
