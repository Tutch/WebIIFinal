/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Yuri
 */
@Entity
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ficha_sequence_generator")
    @SequenceGenerator(name = "ficha_sequence_generator", sequenceName = "ficha_id_seq", allocationSize = 1)
    private Long codigo;
    private String descricao;
    @ManyToOne
    @JoinColumn(name="codigo_aluno")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name="codigo_professor")
    private Professor instrutor;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Professor instrutor) {
        this.instrutor = instrutor;
    }

    

    public Ficha(String descricao, Aluno aluno, Professor professor) {
        this.descricao = descricao;
        this.aluno = aluno;
        this.instrutor = professor;
    }
    
    public Ficha(){
        
    }
    
    @Override
    public String toString(){
        return this.descricao;
    }
}
