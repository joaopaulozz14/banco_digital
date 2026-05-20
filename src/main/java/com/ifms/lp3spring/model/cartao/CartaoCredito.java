package com.ifms.lp3spring.model.cartao;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "idCartao")
@Table(name = "cartao_credito")
public class CartaoCredito extends CartaoModel{
    
    @NotNull
    private double limite;

    public CartaoCredito() {
    }

    public CartaoCredito(Long idCartao, Date validade, String cvv, @NotNull double limite) {
        super(idCartao, validade, cvv);
        this.limite = limite;
    }


    public double getLimite() {
        return limite;
    }



    public void setLimite(double limite) {
        this.limite = limite;
    }

    

}
