/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Entidades.AlunoDAO;
import Entidades.Aluno;
import java.util.Date;
import javax.ejb.Stateless;
/**
 *
 * @author Sergio Marinho
 */
@Stateless
public class Cadastro implements CadastroLocal {
    
    @Override
    public boolean cadastraAluno(){
        Aluno aluno = new Aluno("eas", Long.MIN_VALUE, 'c', true, "egas", new Date(), "ehas");
        AlunoDAO dao = new AlunoDAO();
        try{
            dao.create(aluno);
        }catch(Exception e){
            return false;
        }
        System.out.println("ta aqui");
        return true;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}