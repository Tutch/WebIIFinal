
import DAO.AlunoDAO;
import Entidades.Aluno;
import java.sql.Date;
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yuri
 */
public class teste {
    public static void main(String[] args) {
        Date nasc = Date.valueOf(LocalDate.now());
        Aluno a;
        a = new Aluno("Teste", 04345674241l, 'M', false, "Rua do teste", nasc, "Email Teste", "teste");
        
        AlunoDAO dao = new AlunoDAO();
        dao.create(a);
        System.out.println("hello");
    }
}
