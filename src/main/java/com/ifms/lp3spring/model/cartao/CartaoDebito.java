package com.ifms.lp3spring.model.cartao;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "idCartao")
@Table(name = "cartao_debito")
public class CartaoDebito extends CartaoModel{
       
}
