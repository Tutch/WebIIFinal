/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Yuri
 */
public class ExercicioFichaPK implements Serializable{
    protected Ficha codigoFicha;
    protected Exercicios codigoExercicio;
    
    public ExercicioFichaPK(){
        
    }
    
    public ExercicioFichaPK(Ficha codigo, Exercicios exe){
        this.codigoFicha = codigo;
        this.codigoExercicio = exe;
    }
    
     @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(codigoFicha).
            append(codigoExercicio).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof ExercicioFichaPK))
            return false;
        if (obj == this)
            return true;

        ExercicioFichaPK rhs = (ExercicioFichaPK) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(codigoFicha, rhs.codigoFicha).
            append(codigoExercicio, rhs.codigoExercicio).
            isEquals();
    }
}
