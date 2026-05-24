package com.ifms.lp3spring.model.cartao;

import java.time.LocalDate;
import com.ifms.lp3spring.model.conta.ContaModel;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@PrimaryKeyJoinColumn(name = "idCartao")
@Table(name = "cartao_credito")
public class CreditoModel extends CartaoModel {
    
    @PositiveOrZero(message = "O limite do cartão deve ser maior ou igual a zero") // Melhor que @NotNull para tipos primitivos
    private double limite;

    protected CreditoModel() {
    }

    public CreditoModel(LocalDate validade, String cvv, String numero, ContaModel conta, double limite) {
        super(validade, cvv, numero, conta);
        this.limite = limite;
    }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }
}
