/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Classes.Item;
import DAO.ItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergio Marinho
 */
public class buscarProdutosServlet extends HttpServlet {

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
        if(request.getSession(false) ==null){
            request.getRequestDispatcher("").forward(request, response);
        }else{
            try {
                ArrayList<Item> a = ItemDAO.retrieveAllItens();
                if(a==null||a.size()==0){                    
                    request.setAttribute("msg", "Você não possui nenhum item cadastrado!");
                    RequestDispatcher despachante = request.getRequestDispatcher("/BuscarProduto");
                    despachante.forward(request, response);
                    return;
                }
                System.out.println("buscando os "  + a.size() + " do bd");       
                request.setAttribute("itens", a);
                RequestDispatcher despachante = request.getRequestDispatcher("paginaProduto");            
                despachante.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
                RequestDispatcher despachante = request.getRequestDispatcher("/erroOperation");
                despachante.forward(request, response);
            }
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
