/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import Entidades.Aluno;
import Entidades.Professor;
import java.util.List;

/**
 *
 * @author Yuri
 */
public class AssinalarBean {
    private Aluno aluno;
    private Professor professor;
    private List<Aluno> alunos;
    private List<Professor> professores;
    
    public AssinalarBean(){
        alunos = AlunoDAO.read();
        professores = ProfessorDAO.read();
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
    
    public void cadastrarProfessor(){
        System.out.println("entrei");
        aluno.setInstrutor(professor);
        AlunoDAO.update(aluno);
    }
    
}
