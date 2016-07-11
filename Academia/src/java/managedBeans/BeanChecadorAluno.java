/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sergio Marinho
 */
public class BeanChecadorAluno {

    public BeanChecadorAluno() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if(session!=null){
            Object o = session.getAttribute("user");
            if(o instanceof Entidades.Aluno){
                return;
            }else{
                HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
                try {
                    System.out.println("redirecionando não aluno");
                    response.sendRedirect("index.xhtml");
                } catch (IOException ex) {
                    System.out.println("problema ao redirecionar");
                    Logger.getLogger(BeanChecadorAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
                try {
                    System.out.println("redirecionando não aluno");
                    response.sendRedirect("index.xhtml");
                } catch (IOException ex) {
                    System.out.println("problema ao redirecionar");
                    Logger.getLogger(BeanChecadorAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        System.out.println("terminou checagem");
    }
    
}
