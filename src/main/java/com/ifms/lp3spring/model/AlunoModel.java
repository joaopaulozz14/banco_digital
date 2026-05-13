package com.ifms.lp3spring.model;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@PrimaryKeyJoinColumn(name="idPessoa")
@Table(name = "aluno")
public class AlunoModel extends PessoaModel {
    
    @NotNull(message = "RA não pode ser nulo")
    @Min(value = 4, message = "RA deve ter pelo menos 4 caracteres")
    private int ra;

    @PastOrPresent(message = "Data de ingresso não pode ser futura")
    @NotNull(message = "Data de ingresso não pode ser nulo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataIngresso;

    @NotBlank(message = "Curso não pode estar vazio")
    private String curso;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "E-mail inválido")
    private String emailInstitucional;


    @ManyToMany
    @JoinTable(
    name = "aluno_disciplina",
    joinColumns = @JoinColumn(name = "aluno_id"),
    inverseJoinColumns = @JoinColumn(name = "disciplina_id")
)

private List<DisciplinaModel> disciplinas;
    

    public AlunoModel() {
    }

    public AlunoModel(String nome, Long cpf, Date dataNascimento, int ra, LocalDate dataIngresso, String curso, String emailInstitucional) {
        super(nome, cpf, dataNascimento);
        this.ra = ra;
        this.dataIngresso = dataIngresso;
        this.curso = curso;
        this.emailInstitucional = emailInstitucional;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    

}

