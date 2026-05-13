package com.ifms.lp3spring.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@PrimaryKeyJoinColumn(name="idPessoa")
@Table(name = "professor")
public class ProfessorModel extends PessoaModel {
    private int siape;
    @PastOrPresent
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataPosse;
    @OneToMany(mappedBy="professor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DisciplinaModel> disciplinas;

    public ProfessorModel() {
    }

    public ProfessorModel(String nome, Long cpf, Date dataNascimento, int siape, Date dataPosse) {
        super(nome, cpf, dataNascimento);
        this.siape = siape;
        this.dataPosse = dataPosse;
    }

    public int getSiape() {
        return siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
    }

    public Date getDataPosse() {
        return dataPosse;
    }

    public void setDataPosse(Date dataPosse) {
        this.dataPosse = dataPosse;
    }

    public List<DisciplinaModel> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinaModel> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
