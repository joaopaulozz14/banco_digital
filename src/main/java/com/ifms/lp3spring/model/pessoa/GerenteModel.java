package com.ifms.lp3spring.model.pessoa;

import java.util.Date;
/* import java.util.List; */

/* import jakarta.persistence.CascadeType; */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
/* import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany; */
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")
@Table(name = "professor")
public class GerenteModel extends PessoaModel {


    @NotNull
    private int salario;

    public GerenteModel() {
    }

    
    public GerenteModel(@NotNull int salario) {
        this.salario = salario;
    }


    public GerenteModel(String nome, Long cpf, String telefone, Date dataNascimento, @NotNull int salario) {
        super(nome, cpf, telefone, dataNascimento);
        this.salario = salario;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    

}
