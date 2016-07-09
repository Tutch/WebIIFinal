package managedBeans;


import DAO.ExercicioFichaDAO;
import java.io.Serializable;
import java.util.ArrayList;
import Entidades.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import others.AlertClass;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sergio Marinho
 */
public class BeanCadastroFicha implements Serializable{
    private List<Aluno> alunosDoProf;
    private ArrayList<String> alunos;
    private Professor professor;
    private String alunoAtual;
    private ArrayList<String> exerciciosSelecionados;
    private ArrayList<String> exerciciosDisponiveis;
    private List<Exercicios> exercicios;
    private String descricao; 

    public ArrayList<String> getExerciciosSelecionados() {
        return exerciciosSelecionados;
    }

    public void setExerciciosSelecionados(ArrayList<String> exerciciosSelecionados) {
        this.exerciciosSelecionados = exerciciosSelecionados;
    }

    public List<Exercicios> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicios> exercicios) {
        this.exercicios = exercicios;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<String> getExerciciosDisponiveis() {
        return exerciciosDisponiveis;
    }

    public void setExerciciosDisponiveis(ArrayList<String> exerciciosDisponiveis) {
        this.exerciciosDisponiveis = exerciciosDisponiveis;
    }
    
    public List<Aluno> getAlunosDoProf() {
        return alunosDoProf;
    }

    public void setAlunosDoProf(List<Aluno> alunosDoProf) {
        this.alunosDoProf = alunosDoProf;
    }

    public ArrayList<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<String> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getAlunoAtual() {
        return alunoAtual;
    }

    public void setAlunoAtual(String alunoAtual) {
        this.alunoAtual = alunoAtual;
    }
    
    
    
    public BeanCadastroFicha(){    
        professor=null;
        alunosDoProf = new ArrayList<Aluno>();
        alunos = new ArrayList<String>();
        exerciciosDisponiveis = new ArrayList<String>();
        exerciciosSelecionados= new ArrayList<String>();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        AlertClass.limparMsg();
        if(session!=null){
            Object o = session.getAttribute("user");
            if(o instanceof Entidades.Professor){
                professor = (Professor)o;
                pegaAlunosDoProfessor();
                if(alunosDoProf.isEmpty()){
                    alunos.add("Você não possui alunos cadastrados!");
                }else{
                    for(Aluno a:alunosDoProf){
                        alunos.add(a.getNome()+" (CPF:"+a.getCpf()+")");
                    }
                }
            }
        }
        
        exercicios = DAO.ExerciciosDAO.read();
        if(exercicios!=null && !exercicios.isEmpty()){
            for(Exercicios e:exercicios){
                if(e.getDescricao()==null){
                    exerciciosDisponiveis.add(e.getCodigo() +" -"+ e.getNome() + " (" +e.getMusculo()+")");
                }else{
                    exerciciosDisponiveis.add(e.getCodigo() +" -"+ e.getNome() + " (" +e.getMusculo()+")");
                }
            }
        }else{
            exerciciosDisponiveis.add("O sistema não possui exercicios cadastrados");
        }
        
    }
    public String cadastraFicha(){
        List<Exercicios> exerciciosEscolhidos = getExercEscolhidos();
        System.out.println("aluno escolhido: " + getCpf());
        String cpfEscolhido = getCpf();
        Aluno alunoAEnviar=null;
        for(Aluno a:alunosDoProf){
            if(a.getCpf().equalsIgnoreCase(cpfEscolhido)){
                alunoAEnviar = a;
                break;
            }
        }
        Ficha f= new Ficha(descricao, alunoAEnviar, professor);
        if(!DAO.FichaDAO.create(f)){
            AlertClass.redirecionaMsg("Erro ao cadastrar ficha!","../faces/cadastrarFicha.xhtml");
            return "erro";
        }
        List<Ficha> fichas = DAO.FichaDAO.read();
        if(fichas!=null&&!fichas.isEmpty()){
            for(Exercicios e: exerciciosEscolhidos){
                ExercicioFicha ef = new ExercicioFicha(fichas.get(fichas.size()-1), e);
                if(!ExercicioFichaDAO.create(ef)){
                    AlertClass.redirecionaMsg("Erro ao cadastrar ficha!","../faces/cadastrarFicha.xhtml");
                    return "erro";
                }
            }
        }
        System.out.println("descricao: "+ descricao);
        System.out.println("codigo professor: "+professor.getCodigo().longValue());
        AlertClass.redirecionaMsg("Ficha cadastrada com sucesso!","../faces/cadastrarFicha.xhtml");
        return "sucesso";
    }
    
    public String getCpf(){
         String retorno="";
         retorno = alunoAtual.substring(alunoAtual.indexOf(" (CPF:"));
         retorno = retorno.replace(" (CPF:", "");
         retorno = retorno.replace(")", "");
         return retorno;
    }
    
    public List<Exercicios> getExercEscolhidos(){
        ArrayList<Long> listaNum = new ArrayList<Long>();
        ArrayList<Exercicios> exerciciosEscolhidos = new ArrayList<Exercicios>();
        if(exerciciosSelecionados!=null && !exerciciosSelecionados.isEmpty()){
            for(String b:exerciciosSelecionados){
                String s = b.substring(0, b.indexOf(" -"));
                Long a = new Long(Long.parseLong(s));
                listaNum.add(a);
            }
        }
        if(listaNum!=null && !listaNum.isEmpty()){
            for(Long l:listaNum){
                for(Exercicios e:exercicios){
                    if(l.longValue()==e.getCodigo()){
                        exerciciosEscolhidos.add(e);
                    }
                }
            }
        }
        return exerciciosEscolhidos;
    }
    
    public void pegaAlunosDoProfessor(){
        List<Aluno> todos = DAO.AlunoDAO.read();
        if(todos!=null){
            for(Aluno a:todos){
                if(a.getInstrutor()!=null){
                    if(a.getInstrutor().getCodigo().longValue()==professor.getCodigo().longValue()){
                        alunosDoProf.add(a);
                    }
                }
            }
        }
    }
    
}
