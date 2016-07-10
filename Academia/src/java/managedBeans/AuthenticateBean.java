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
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.FilterInput;

/**
 *
 * @author Yuri
 */
public class AuthenticateBean implements Serializable{
    private Aluno aluno;
    private Professor professor;
    private boolean isAuthenticated;
    private String login, password;
    
    public AuthenticateBean(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String path = facesContext.getExternalContext().getRequestContextPath();
        System.out.println(path);
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if(session!=null){
            Object o = session.getAttribute("user");
            if(o instanceof Entidades.Professor){
                HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
                try {
                    System.out.println("redirecionando professor");
                    response.sendRedirect("../Academia/faces/homeProfessor.xhtml");
                } catch (IOException ex) {
                    System.out.println("problema ao redirecionar");
                    Logger.getLogger(BeanChecadorAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(o instanceof Entidades.Aluno){
                HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
                try {
                    System.out.println("redirecionando aluno");
                    response.sendRedirect("../Academia/faces/home.xhtml");
                } catch (IOException ex) {
                    System.out.println("problema ao redirecionar");
                    Logger.getLogger(BeanChecadorAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.out.println("instancia de nenhum dos dois");
            }
        }else{
            System.out.println("sem sessao");
        }
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String logout(){
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            System.out.println("Invalidou");
            return "index";
    }
    
    public String authenticate(){        
        if(FilterInput.noDangerousCharacters(login) && FilterInput.noDangerousCharacters(password)){
            FacesContext context = FacesContext.getCurrentInstance();
            aluno = AlunoDAO.authenticateUser(login, password);
            if( aluno != null){
                context.getExternalContext().getSessionMap().put("user", aluno);
                return "Authenticated";
            }else{
                professor = ProfessorDAO.authenticateUser(login, password);
                if(professor != null){
                    context.getExternalContext().getSessionMap().put("user", professor);
                    return "AuthenticatedProfessor";
                }else{
                    return "Failed";
                }
            }
        }
        return "Failed";
    }
}
