package com.ifms.lp3spring.model.conta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@PrimaryKeyJoinColumn(name = "idConta")
@Table(name = "conta_corrente")
public class ContaCorrente extends ContaModel{
    @NotNull
    @Column(name = "chequeEspecial")
    private String cheque_especial;

    
    public ContaCorrente() {
    }

    public ContaCorrente(Long idConta, double saldo_atual, double fatura, String cheque_especial) {
        super(idConta, saldo_atual, fatura);
        this.cheque_especial = cheque_especial;
    }


    public String getCheque_especial() {
        return cheque_especial;
    }

    public void setCheque_especial(String cheque_especial) {
        this.cheque_especial = cheque_especial;
    }    

    
}
