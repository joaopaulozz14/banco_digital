package com.ifms.lp3spring.model.pessoa;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")
@Table(name = "cliente")
public class ClienteModel extends PessoaModel {


    @NotNull
    private double renda;

    public ClienteModel(String nome, Long cpf, String telefone, Date dataNascimento, double renda) {
        super(nome, cpf, telefone, dataNascimento);

        this.renda = renda;
    }

    public ClienteModel() {
    }


    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

}
