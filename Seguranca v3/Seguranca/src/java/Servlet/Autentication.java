/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yuri
 */
public class Autentication extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        boolean isOk = false;
        
        try {
           isOk = UserDAO.AuthenticateUser(login, senha);
        } catch (SQLException ex) {
            Logger.getLogger(Autentication.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
        request.setAttribute("statusLogin", isOk);        
        
        if(isOk){
            HttpSession sessao = request.getSession(true);
            sessao.setAttribute("login", login);
            sessao.setMaxInactiveInterval(300);
            ServletContext sc = this.getServletContext();
            
            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dataAcesso = formatador.format(data);
            dataAcesso = dataAcesso.replace(" ", " às ");
            System.out.println(dataAcesso);
            sc.setAttribute("dataUltLogin", dataAcesso);
            System.out.println("Entrou");
            
            RequestDispatcher despachante = request.getRequestDispatcher("menuInicial");
            despachante.forward(request, response);
        }
        else{
            RequestDispatcher despachante = request.getRequestDispatcher("/erroUser.jsp");
            despachante.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
