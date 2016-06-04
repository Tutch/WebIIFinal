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
    private Long codigo_aluno;
    private Long codigo_professor;

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

    public Long getCodigo_aluno() {
        return codigo_aluno;
    }

    public void setCodigo_aluno(Long codigo_aluno) {
        this.codigo_aluno = codigo_aluno;
    }

    public Long getCodigo_professor() {
        return codigo_professor;
    }

    public void setCodigo_professor(Long codigo_professor) {
        this.codigo_professor = codigo_professor;
    }

    public Ficha(String descricao, Long codigo_aluno, Long codigo_professor) {
        this.descricao = descricao;
        this.codigo_aluno = codigo_aluno;
        this.codigo_professor = codigo_professor;
    }
    
    public Ficha(){
        
    }
}
