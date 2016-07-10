/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.Serializable;

/**
 *
 * @author Sergio Marinho
 */
public class BeanMenuAluno extends BeanChecadorAluno implements Serializable{
    private String stringCheck_naoApagar;
    public BeanMenuAluno() {
        super();
    }

    public String getStringCheck_naoApagar() {
        return stringCheck_naoApagar;
    }

    public void setStringCheck_naoApagar(String stringCheck_naoApagar) {
        this.stringCheck_naoApagar = stringCheck_naoApagar;
    }    
    
}
