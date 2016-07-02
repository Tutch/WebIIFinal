/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import Entidades.Aluno;
import Entidades.Professor;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mapurunga
 */

@SuppressWarnings("restriction")
@XmlRootElement(name = "Ficha")	
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLFicha {
    private Long codigo;
    private String descricao;
    private Aluno aluno;
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

    
    
    
}
