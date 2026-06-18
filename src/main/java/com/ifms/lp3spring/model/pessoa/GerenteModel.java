package com.ifms.lp3spring.model.pessoa;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")
@Table(name = "gerente")
public class GerenteModel extends PessoaModel {

    @Positive(message = "O salário deve ser maior que zero")
    @Column(name = "salarioGerente")
    private double salario;

    @OneToMany(mappedBy = "gerente")
    private List<ClienteModel> clientes;

    public GerenteModel() {
    }

    public GerenteModel(String nome, String cpf, String telefone, Date dataNascimento, int salario) {
        super(nome, cpf, telefone, dataNascimento);
        this.salario = salario;
    }

    public List<ClienteModel> getClientes() {
		return clientes;
	}
    
    public void setClientes(List<ClienteModel> clientes) {
    			this.clientes = clientes;
    }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
