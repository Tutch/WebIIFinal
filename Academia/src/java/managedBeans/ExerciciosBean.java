/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.ExerciciosDAO;
import Entidades.Exercicios;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Yuri
 */
//@ManagedBean
//@SessionScoped
public class ExerciciosBean {
    private ArrayList<String> gruposMusculo;
    private String nome, descricao, musculo;

    public ExerciciosBean(){
        gruposMusculo = new ArrayList<>();
        gruposMusculo.add("Biceps");
        gruposMusculo.add("Triceps");
        gruposMusculo.add("Pernas");
        gruposMusculo.add("Abdominal");
        gruposMusculo.add("Costas Superior");
        gruposMusculo.add("Costas Inferior");
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
        try{
            Exercicios ex = new Exercicios();
            ex.setNome(nome);
            ex.setMusculo(musculo);
            ex.setDescricao(descricao);
            
            //ExerciciosDAO.create(ex);
            return "Cadastrado";
        }catch(Exception e){
            return "Falha";
        }
        
    }
}
