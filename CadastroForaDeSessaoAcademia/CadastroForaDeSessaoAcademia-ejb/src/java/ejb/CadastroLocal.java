/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Entidades.Aluno;
import javax.ejb.Local;

/**
 *
 * @author Sergio Marinho
 */
@Local
public interface CadastroLocal {
    public boolean cadastraAluno();
}
