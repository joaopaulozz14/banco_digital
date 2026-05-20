package com.ifms.lp3spring.model.conta;

import java.util.ArrayList;
import java.util.List;
import com.ifms.lp3spring.model.cartao.CartaoModel;
import com.ifms.lp3spring.model.pessoa.ClienteModel;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta")
public abstract class ContaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    private double saldoAtual; 

    private double fatura;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartaoModel> cartoes = new ArrayList<>();
    
    protected ContaModel(){} // Alterado para protegido por boa prática
    
    // Removido idConta do construtor público
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
