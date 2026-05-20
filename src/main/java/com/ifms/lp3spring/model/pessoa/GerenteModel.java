package com.ifms.lp3spring.model.pessoa;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")
@Table(name = "gerente") // Corrigido de "professor" para "gerente"
public class GerenteModel extends PessoaModel {

    @Positive(message = "O salário deve ser maior que zero") // Melhor que @NotNull para valores numéricos
    @Column(name = "salarioGerente")
    private int salario;

    protected GerenteModel() {
    }

    public GerenteModel(String nome, String cpf, String telefone, Date dataNascimento, int salario) {
        super(nome, cpf, telefone, dataNascimento);
        this.salario = salario;
    }

    public int getSalario() { return salario; }
    public void setSalario(int salario) { this.salario = salario; }
}
