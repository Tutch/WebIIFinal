/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Entidades.AlunoDAO;
import Entidades.Aluno;
import java.sql.Date;
import javax.ejb.Stateless;
/**
 *
 * @author Sergio Marinho
 */
@Stateless
public class Cadastro implements CadastroLocal {
    
    @Override
    public boolean cadastraAluno(Aluno aluno){
        try{
            AlunoDAO.create(aluno);
        }catch(Exception e){
            System.out.println("execao é: "+e.getClass().getSimpleName());
            return false;
        }
        return true;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}