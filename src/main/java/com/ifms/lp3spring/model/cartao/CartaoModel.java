package com.ifms.lp3spring.model.cartao;

import java.time.LocalDate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ifms.lp3spring.model.conta.ContaModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cartao")
public abstract class CartaoModel { // Alterado para abstract para nao permitir criacao generica

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartao;

    @NotNull(message = "A data de validade é obrigatória")
    @Future(message = "A validade do cartão deve ser uma data futura") // Garante que o cartão não nasça vencido
    private LocalDate validade;

    @NotBlank(message = "O CVV é obrigatório")
    @Pattern(regexp = "^\\d{3,4}$", message = "O CVV deve conter 3 ou 4 dígitos numéricos") // Validação de formato para segurança
    @Column(length = 4)
    private String cvv;
    
    @NotBlank(message = "O número do cartão é obrigatório")
    String numero;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_conta")
    @Fetch(FetchMode.JOIN)
    private ContaModel conta;

    protected CartaoModel(){}

    public CartaoModel(LocalDate validade, String cvv, String numero, ContaModel conta) {
        this.validade = validade;
        this.cvv = cvv;
        this.numero = numero;
        this.conta = conta;
    }

    // GETTERS E SETTERS
    public Long getIdCartao() { return idCartao; }
    public void setIdCartao(Long idCartao) { this.idCartao = idCartao; }

    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero;}

    public ContaModel getConta() { return conta; }
    public void setConta(ContaModel conta) { this.conta = conta; }
}
