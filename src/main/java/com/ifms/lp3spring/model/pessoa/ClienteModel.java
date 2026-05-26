package com.ifms.lp3spring.model.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ifms.lp3spring.model.conta.ContaModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")
@Table(name = "cliente")
public class ClienteModel extends PessoaModel {

    @PositiveOrZero(message = "A renda deve ser maior ou igual a 0")
    @Column(name = "rendaCliente")
    private double renda;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContaModel> contas = new ArrayList<>();

    public ClienteModel() {}

    public ClienteModel(String nome, String cpf, String telefone, Date dataNascimento, double renda) {
        super(nome, cpf, telefone, dataNascimento);
        this.renda = renda;
    }
    
    public void adicionarConta(ContaModel conta) {
    	 this.contas.add(conta);
    	 conta.setCliente(this);
    }

    public void removerConta(ContaModel conta) {
        this.contas.remove(conta);
        conta.setCliente(null);
    }

    public double getRenda() { return renda; }
    public void setRenda(double renda) { this.renda = renda; }
    public List<ContaModel> getContas() { return contas; }
    // Removido o setContas(List) para proteger o gerenciamento do Hibernate
}
