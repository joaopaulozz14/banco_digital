package com.ifms.lp3spring.model.conta;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ifms.lp3spring.model.cartao.CartaoModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta")
public abstract class ContaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    @NotNull
    private double saldoAtual; 

    @NotNull
    private double fatura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    @Fetch(FetchMode.JOIN)
    private ClienteModel cliente;

    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartaoModel> cartoes = new ArrayList<>();
    
    protected ContaModel(){}
    
    public ContaModel(double saldoAtual, double fatura) {
        this.saldoAtual = saldoAtual;
        this.fatura = fatura;
    }

    // Métodos Utilitários para sincronização dos Cartões
    public void adicionarCartao(CartaoModel cartao) {
        this.cartoes.add(cartao);
        cartao.setConta(this);
    }

    public void removerCartao(CartaoModel cartao) {
        this.cartoes.remove(cartao);
        cartao.setConta(null);
    }

    // Retorna o tipo de conta;
    public String getTipoConta() {
        return this.getClass().getSimpleName();
    }
    
    // Recebe o limite calculado a partir da renda de ContaCorrente
    public abstract double getLimite();
    
    // Depositar valor da conta;
    public void depositar(double valor) {

        if(valor <= 0) {

            throw new IllegalArgumentException(
                    "Valor inválido"
            );
        }

        this.saldoAtual += valor;
    }
    
    // Sacar valor da conta;
    public void sacar(double valor) {

        if(valor <= 0) {

            throw new IllegalArgumentException(
                    "Valor inválido"
            );
        }

        double saldoDisponivel =
                saldoAtual + getLimite();

        if(valor > saldoDisponivel) {

            throw new IllegalArgumentException(
                    "Saldo insuficiente"
            );
        }

        this.saldoAtual -= valor;
    }
    
    // GETTERS E SETTERS
    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }

    public double getSaldoAtual() { return saldoAtual; }
    public void setSaldoAtual(double saldoAtual) { this.saldoAtual = saldoAtual; }

    public double getFatura() { return fatura; }
    public void setFatura(double fatura) { this.fatura = fatura; }

    public ClienteModel getCliente() { return cliente; }
    public void setCliente(ClienteModel cliente) { this.cliente = cliente; }

    public List<CartaoModel> getCartoes() { return cartoes; }
    // Removido setCartoes para proteger o gerenciamento da coleção pelo Hibernate
}
