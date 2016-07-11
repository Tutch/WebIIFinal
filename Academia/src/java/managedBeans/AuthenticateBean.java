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
public class AuthenticateBean{
    private Aluno aluno;
    private Professor professor;
    private boolean isAuthenticated;
    private String login, password;
    
    public AuthenticateBean(){
        
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
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            try {
                    response.sendRedirect("index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(AuthenticateBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            return "index";
    }
    
    public String authenticate(){        
        if(FilterInput.noDangerousCharacters(login) && FilterInput.noDangerousCharacters(password)){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            aluno = AlunoDAO.authenticateUser(login, password);
            if( aluno != null){
                context.getExternalContext().getSessionMap().put("user", aluno);
                try {
                    response.sendRedirect("home.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(AuthenticateBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "Authenticated";
            }else{
                professor = ProfessorDAO.authenticateUser(login, password);
                if(professor != null){
                    context.getExternalContext().getSessionMap().put("user", professor);
                    try {
                        response.sendRedirect("homeProfessor.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(AuthenticateBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return "AuthenticatedProfessor";
                }else{
                    return "Failed";
                }
            }
        }
        return "Failed";
    }
}
