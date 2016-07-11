/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sergio Marinho
 */
public class BeanLogin implements Serializable{
    
    private String stringCheck_naoApagar;

    public String getStringCheck_naoApagar() {
        return stringCheck_naoApagar;
    }    

    public void setStringCheck_naoApagar(String stringCheck_naoApagar) {
        this.stringCheck_naoApagar = stringCheck_naoApagar;
    }

    public BeanLogin() {
        System.out.println("verificando se há sessão");
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
                    response.sendRedirect("../faces/homeProfessor.xhtml");
                } catch (IOException ex) {
                    System.out.println("problema ao redirecionar");
                    Logger.getLogger(BeanChecadorAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(o instanceof Entidades.Aluno){
                HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
                try {
                    System.out.println("redirecionando aluno");
                    response.sendRedirect("../faces/home.xhtml");
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
    
}
