/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.FilterInput;

/**
 *
 * @author Yuri
 */
//@ManagedBean
//@SessionScoped
public class AuthenticateBean {
    private boolean isAuthenticated;
    private String login, password;

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
    
    public String authenticate(){
        System.out.println("Login " + getLogin() + "/" + login);
        System.out.println("PW " + getPassword() + "/" + password);
        
        if(FilterInput.noDangerousCharacters(login) && FilterInput.noDangerousCharacters(password)){
            if(AlunoDAO.authenticateUser(login, password) == true){
                return "Authenticated";
            }else{
                if(ProfessorDAO.authenticateUser(login, password) == true){
                    return "AuthenticatedProfessor";
                }else{
                    return "Failed";
                }
            }
        }
        
        return "Failed";
    }
}
