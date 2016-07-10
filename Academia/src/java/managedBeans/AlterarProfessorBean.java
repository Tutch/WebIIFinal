/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.ProfessorDAO;
import Entidades.Professor;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import others.AlertClass;
import util.FilterInput;

/**
 *
 * @author Yuri
 */
public class AlterarProfessorBean extends BeanChecadorProfessor implements Serializable {
    private String nome;
    private String email;
    private String password;
    private String confirmaSenha;
    private String msg;

    public AlterarProfessorBean() {
        super();
        AlertClass.limparMsg();
    }
    
    
    
    @PostConstruct
    public void init(){
       System.out.println("entrei aqui");
       FacesContext context = FacesContext.getCurrentInstance();
       HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       Professor professor = (Professor)request.getSession().getAttribute("user");
       
       this.nome = professor.getNome();
       this.email = professor.getEmail();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String enviar(){
       FacesContext context = FacesContext.getCurrentInstance();
       HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       
        try {
            Professor professor = (Professor)request.getSession().getAttribute("user");
            if(FilterInput.noDangerousCharacters(nome) && FilterInput.noDangerousCharacters(password) && FilterInput.noDangerousCharacters(email)){
                professor.setEmail(email);
                professor.setNome(nome);
                professor.setPassword(password);

                ProfessorDAO.update(professor);

                AlertClass.redirecionaMsg("Dados alterados com suceso!", "../faces/professorAlterarDados.xhtml");
                return "sucesso";
            }else{
                AlertClass.redirecionaMsg("Erro ao alterar dados!", "../faces/professorAlterarDados.xhtml");
                return "falhou";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
        AlertClass.redirecionaMsg("Erro ao alterar dados!", "../faces/professorAlterarDados.xhtml");
        return "falhou";
    }
       
    
}
