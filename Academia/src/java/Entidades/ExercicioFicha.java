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
 * @author Mapurunga
 */

@Entity
public class ExercicioFicha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercicioficha_sequence_generator")
    @SequenceGenerator(name = "exercicioficha_sequence_generator", sequenceName = "exercicioficha_id_seq", allocationSize = 1)
    private Long codigo;
    
    @ManyToOne
    @JoinColumn(name="codigo")
    private Ficha codigoFicha;
    
    @ManyToOne
    @JoinColumn(name="codigo")
    private Exercicios codigoExercicio;  

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Ficha getCodigoFicha() {
        return codigoFicha;
    }

    public void setCodigoFicha(Ficha codigoFicha) {
        this.codigoFicha = codigoFicha;
    }

    public Exercicios getCodigoExercicio() {
        return codigoExercicio;
    }

    public void setCodigoExercicio(Exercicios codigoExercicio) {
        this.codigoExercicio = codigoExercicio;
    }
    
    
    
}
