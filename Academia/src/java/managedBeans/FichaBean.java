/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.AlunoDAO;
import DAO.FichaDAO;
import Entidades.Aluno;
import Entidades.Exercicios;
import Entidades.Ficha;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Yuri
 */
public class FichaBean implements Serializable{
    String fichaSelecionada;
    Ficha fichaObj;
    List<String> fichasNomes = new ArrayList<String>();
    List<Ficha> fichas;

    public String getFichaSelecionada() {
        return fichaSelecionada;
    }

    public void setFichaSelecionada(String fichaSelecionada) {
        this.fichaSelecionada = fichaSelecionada;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public List<String> getFichasNomes() {
        return fichasNomes;
    }

    public void setFichasNomes(List<String> fichasNomes) {
        this.fichasNomes = fichasNomes;
    }
    
    
    public FichaBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        Aluno aluno = (Aluno)request.getSession().getAttribute("user");
        System.out.println(request.getSession().getAttribute("user") instanceof Aluno);
        fichas = AlunoDAO.getFicha(aluno);
        if(fichas.size() > 0 ){
            for(Ficha ficha:fichas){
                fichasNomes.add(ficha.getDescricao());
            }
        }
    }
    
    public void getFichaFromString(){
        for(Ficha ficha:fichas){
            if(ficha.getDescricao().hashCode() == fichaSelecionada.hashCode()){
                fichaObj = ficha;
            }
        }
    }
    public void printFicha(){
       getFichaFromString();
       List<Exercicios> exercicios = FichaDAO.getAllExercicios(fichaObj);
        System.out.println(fichaSelecionada);
       for(Exercicios exercicio : exercicios){
           System.out.println(exercicio.getDescricao());
       }
       
       
    }
    
    
}
