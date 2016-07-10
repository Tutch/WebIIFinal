/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.ExerciciosDAO;
import Entidades.Exercicios;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import others.AlertClass;
import util.FilterInput;

/**
 *
 * @author Yuri
 */
//@ManagedBean
//@SessionScoped
public class ExerciciosBean extends BeanChecadorProfessor implements Serializable{
    private Exercicios exercicioSelecionado;

    private ArrayList<String> gruposMusculo;
    private String nome, descricao, musculo;

    public ExerciciosBean(){
        super();
        AlertClass.limparMsg();
        gruposMusculo = new ArrayList<>();
        gruposMusculo.add("Biceps");
        gruposMusculo.add("Triceps");
        gruposMusculo.add("Peito");
        gruposMusculo.add("Pernas");
        gruposMusculo.add("Abdominal");
        gruposMusculo.add("Costas Superior");
        gruposMusculo.add("Costas Inferior");
    }

    public Exercicios getExercicioSelecionado() {
        return exercicioSelecionado;
    }

    public void setExercicioSelecionado(Exercicios exercicioSelecionado) {
        this.exercicioSelecionado = exercicioSelecionado;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public ArrayList<String> getGruposMusculo() {
        return gruposMusculo;
    }

    public void setGruposMusculo(ArrayList<String> gruposMusculo) {
        this.gruposMusculo = gruposMusculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public String cadastrar(){
        System.out.println("Musculo " + getMusculo());
        System.out.println("Desc " + getDescricao());
        
        if(FilterInput.noDangerousCharacters(nome) && FilterInput.noDangerousCharacters(descricao)){
            
            Exercicios ex = new Exercicios();
            ex.setNome(nome);
            ex.setMusculo(musculo);
            ex.setDescricao(descricao);
            
            try{
                boolean rolou=ExerciciosDAO.create(ex);
                if(rolou){
                    AlertClass.redirecionaMsg("Exercicio cadastrado com sucesso!", "../faces/cadastrarExercicio.xhtml");
                    return "ExercicioCadastrado";
                }else{
                    AlertClass.redirecionaMsg("Erro ao cadastrar exercicio!", "../faces/cadastrarExercicio.xhtml");
                    return "ExercicioCadastrado";
                }
            }catch(Exception e){
                AlertClass.redirecionaMsg("Erro ao cadastrar exercicio!", "../faces/cadastrarExercicio.xhtml");
                return "Failed";
            }
        }
        AlertClass.redirecionaMsg("Erro ao cadastrar exercício, insira apenas letras e números!", "../faces/cadastrarExercicio.xhtml");
        return "caracteresInvalidos";
    }
}
