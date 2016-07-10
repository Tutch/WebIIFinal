/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entidades.Aluno;
import Entidades.Professor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import others.AlertClass;

/**
 *
 * @author Sergio Marinho
 */
public class AceitarAlunoBean extends BeanChecadorProfessor implements Serializable{
    private ArrayList<String> alunosPendentes; 
    private List<Aluno> alunos;
    private String alunoAtual;

    public AceitarAlunoBean() {
        super();
        AlertClass.limparMsg();
        alunoAtual="";
        alunos = DAO.AlunoDAO.read();
        alunosPendentes = new ArrayList<String>();
        if(alunos!=null && !alunos.isEmpty()){
            removeValidos();
            for(Aluno a:alunos){
                alunosPendentes.add(a.getNome()+" (CPF:"+a.getCpf()+")");
            }
        }
    }
    
    private void removeValidos(){
        List<Aluno> alunosValidos = new ArrayList<>();
        for(Aluno a:alunos){
            if(a.isAtestado()){
                System.out.println("entrou aqui");
                alunosValidos.add(a);
            }
        }
        if(!alunosValidos.isEmpty()){
            for(Aluno a:alunosValidos){
                alunos.remove(a);
            }
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getAlunoAtual() {
        return alunoAtual;
    }

    public void setAlunoAtual(String alunoAtual) {
        this.alunoAtual = alunoAtual;
    }
    
    public ArrayList<String> getAlunosPendentes() {
        return alunosPendentes;
    }
    public void setAlunosPendentes(ArrayList<String> alunosPendentes) {
        this.alunosPendentes = alunosPendentes;
    }
    
    public String aceitaAluno(){
        String cpfEscolhido = getCpf();
        System.out.println("usuario escolhido: "+cpfEscolhido);
        Aluno alunoEscolhido = null;
        for(Aluno a:alunos){
            if(a.getCpf().equalsIgnoreCase(cpfEscolhido)){
                alunoEscolhido = a;
                break;
            }
        }
        Professor professor = getProfessor();
        if(alunoEscolhido!=null && professor!=null){
            alunoEscolhido.setInstrutor(professor);
            alunoEscolhido.setAtestado(true);
            boolean rolou=DAO.AlunoDAO.update(alunoEscolhido);
            if(rolou){
                AlertClass.redirecionaMsg("Aluno aceito com sucesso! agora esse aluno é seu aluno!", "../faces/alunosPendentes.xhtml");
                return "alunoAceito";
            }
        }
        AlertClass.redirecionaMsg("Erro ao aceitar aluno!", "../faces/alunosPendentes.xhtml");
        return "erroAlunoAceito";
    }
    public String getCpf(){
        String retorno=""; 
        try{    
            retorno = alunoAtual.substring(alunoAtual.indexOf(" (CPF:"));
            retorno = retorno.replace(" (CPF:", "");
            retorno = retorno.replace(")", "");
         }catch(Exception e){
             AlertClass.redirecionaMsg("Erro, nenhum aluno está esperando aceitação!", "../faces/alunosPendentes.xhtml");
         }
         return retorno;
    }
    public Professor getProfessor(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if(session!=null){
            Object o = session.getAttribute("user");
            if(o instanceof Entidades.Professor){
                Professor prof = (Professor)o;
                return prof;
            }
        }else{
            return null;
        }
        return null;
    }
    
}
