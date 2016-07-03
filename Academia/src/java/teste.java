
import DAO.AlunoDAO;
import DAO.FichaDAO;
import DAO.ProfessorDAO;
import Entidades.Aluno;
import Entidades.Ficha;
import Entidades.Professor;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
        a = new Aluno("Cara pequeno 2", 02322344241l, 'M', false, "Rua do teste", nasc, "Email Teste", "teste");
//        AlunoDAO.create(a);
//        
//        Professor p = new Professor("Teste prof", 484654654l,"sadsda", "1234");
//        ProfessorDAO.create(p);
//        
//        a.setAtestado(true);
//        a.setInstrutor(p);
//        AlunoDAO.update(a);
//        
//        Ficha f;
//        f = new Ficha("Crescer pra carai", a, p);
//        FichaDAO.create(f);
        
        List<Aluno> alunos = AlunoDAO.read();
        
        for(Aluno aluno: alunos){
            System.out.println(aluno.getNome());
        }
        System.out.println(alunos.get(2).getNome());
        List <Ficha> fichas = AlunoDAO.getFicha(alunos.get(2));
        
        for(Ficha ficha:fichas){
            System.out.println(ficha.getDescricao());
        }
        
        System.out.println(AlunoDAO.authenticateUser("Cara pequeno", "teste"));
        System.out.println(ProfessorDAO.authenticateUser("Teste prof", "1234"));
    }
}
