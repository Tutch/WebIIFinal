
import DAO.AlunoDAO;
import DAO.ExercicioFichaDAO;
import DAO.ExerciciosDAO;
import DAO.FichaDAO;
import DAO.ProfessorDAO;
import Entidades.Aluno;
import Entidades.ExercicioFicha;
import Entidades.Exercicios;
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
//        
        for(Aluno aluno: alunos){
            System.out.println(aluno.getNome());
        }
        System.out.println(alunos.get(2).getNome());
        List <Ficha> fichas = AlunoDAO.getFicha(alunos.get(2));
        
        for(Ficha ficha:fichas){
            System.out.println(ficha.getDescricao());
        }
        
//        System.out.println(AlunoDAO.authenticateUser("Cara pequeno", "teste"));
//        System.out.println(ProfessorDAO.authenticateUser("Teste prof", "1234"));

//          Exercicios e = new Exercicios("Trapezio decendente", "Trapezio");;
//          ExerciciosDAO.create(e);
//          Exercicios d = new Exercicios("Remada baixa", "Costa");
//          ExerciciosDAO.create(d);
//          Exercicios c = new Exercicios("Martelo", "Biceps");
//          ExerciciosDAO.create(c);
//          Exercicios s = new Exercicios("Bench Press", "Peito");
//          ExerciciosDAO.create(s);
//          
//         
//         ExercicioFicha ef = new ExercicioFicha(fichas.get(0), e);
//         ExercicioFichaDAO.create(ef);
//         
//         ExercicioFicha ed = new ExercicioFicha(fichas.get(0), d);
//         ExercicioFichaDAO.create(ed);
//         
//         ExercicioFicha ec = new ExercicioFicha(fichas.get(0), c);
//         ExercicioFichaDAO.create(ec);
//         
//         ExercicioFicha es = new ExercicioFicha(fichas.get(0), s);
//         ExercicioFichaDAO.create(es);
         
         List<Exercicios> exercicios = FichaDAO.getAllExercicios(fichas.get(0));
         for(Exercicios exercicio:exercicios){
             System.out.println(exercicio.getDescricao());
         }
    }
}
