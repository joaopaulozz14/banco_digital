package com.ifms.lp3spring.model.cartao;

import java.time.LocalDate;
import com.ifms.lp3spring.model.conta.ContaModel;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "idCartao")
@Table(name = "cartao_debito")
public class DebitoModel extends CartaoModel {

    protected DebitoModel() {
        super();
    }

    public DebitoModel(LocalDate validade, String cvv, ContaModel conta) {
        super(validade, cvv, conta);
    }
}
