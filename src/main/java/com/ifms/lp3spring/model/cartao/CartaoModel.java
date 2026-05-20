package com.ifms.lp3spring.model.cartao;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cartao")
public class CartaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCartao;

    private Date validade;

    private String cvv;

    public CartaoModel(){}

    public CartaoModel(Long idCartao, Date validade, String cvv) {
        this.idCartao = idCartao;
        this.validade = validade;
        this.cvv = cvv;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    
}
