/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author Mapurunga
 */

@Entity
@IdClass(ExercicioFichaPK.class)
public class ExercicioFicha {
    @Id
    @ManyToOne
    @JoinColumn(name="codigo_ficha")
    private Ficha codigoFicha;
    @Id
    @ManyToOne
    @JoinColumn(name="codigo_exercicio")
    private Exercicios codigoExercicio;  

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

