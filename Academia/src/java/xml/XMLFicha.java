/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

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
    
    
}
