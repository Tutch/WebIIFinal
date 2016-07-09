/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managedBeans.CadastrarProfessorBean;

/**
 *
 * @author Sergio Marinho
 */
public class AlertClass {
    public static void limparMsg(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) facesContext.getExternalContext().getSession(false);        
        if(sessao!=null){
            sessao.setAttribute("msg", null);
        }
    }
    
    public static void redirecionaMsg(String msg, String pathRetorno){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        HttpSession sessao = (HttpSession) facesContext.getExternalContext().getSession(false);        
        sessao.setAttribute("msg", msg);
        try {
            response.sendRedirect(pathRetorno);
        } catch (IOException ex) {
            Logger.getLogger(CadastrarProfessorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
