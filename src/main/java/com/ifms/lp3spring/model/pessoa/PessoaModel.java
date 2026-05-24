package com.ifms.lp3spring.model.pessoa;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public abstract class PessoaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Alterado para IDENTITY para evitar conflitos no JOINED
    private Long idPessoa;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 10, max = 100, message = "O nome deve ter entre 10 e 100 caracteres") // Padronizado para @Size
    @Column(length = 100)
    private String nome;

    @NotBlank(message = "CPF é obrigatório") // Alterado para String para não perder o zero à esquerda
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
    @Column(unique = true, length = 11)
    private String cpf;

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$", message = "Formato de telefone inválido")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    @Column(length = 15) // Boa prática definir o tamanho no banco também
    private String telefone;

    @Past(message = "A data de nascimento deve ser uma data passada")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    // Construtor padrão protegido (boa prática JPA)
    protected PessoaModel() {
    }

    public PessoaModel(String nome, String cpf, String telefone, Date dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters...
    public Long getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Long idPessoa) { this.idPessoa = idPessoa; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
}
