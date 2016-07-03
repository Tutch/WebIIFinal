/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javax.persistence.Column;
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
public class Exercicios {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercicio_sequence_generator")
    @SequenceGenerator(name = "exercicio_sequence_generator", sequenceName = "exercicio_id_seq", allocationSize = 1)
    private long codigo;
    @Column(name="nome",unique=true)
    private String nome;
    private String descricao;
    private String musculo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public Exercicios(String descricao, String musculo) {
        this.descricao = descricao;
        this.musculo = musculo;
    }
    
    public Exercicios(){
        
    }
    
}
