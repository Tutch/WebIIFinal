/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

import Entidades.Aluno;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sergio Marinho
 */
public class FiltroGeral implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private String[] paginasRaiz = {"/Academia/","/Academia/faces/index.xhtml","/Academia/faces/erroLogin.xhtml"};
    private String[] paginasAluno = {"/Academia/faces/alunoVerFicha.xhtml","/Academia/faces/alunoAlterarDados.xhtml","/Academia/faces/home.xhtml"};
    private String[] paginasProfessor = {"/Academia/faces/cadastrarExercicio.xhtml","/Academia/faces/cadastroProfessor.xhtml","/Academia/faces/erroCadastroProfessor.xhtml","/Academia/faces/homeProfessor.xhtml","/Academia/faces/sucessoCadastroProfessor.xhtml"};
    
    public boolean ehPaginaRaiz(String a){
        for(String b:paginasRaiz){
            if(a.contains(b)){
                return true;
            }
        }
        return false;
    }
    public boolean ehPaginaAluno(String a){
        for(String b:paginasAluno){
            if(a.contains(b)){
                return true;
            }
        }
        return false;
    }
    public boolean ehPaginaProfessor(String a){
        for(String b:paginasProfessor){
            if(a.contains(b)){
                return true;
            }
        }
        return false;
    }
    
    public FiltroGeral() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroGeral:DoBeforeProcessing");
        }

    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroGeral:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)throws IOException, ServletException {   
        /*String uri="";
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (request instanceof HttpServletRequest) {
            uri = ((HttpServletRequest)request).getRequestURI();
            System.out.println(" --------------------------- filtro ativado para a seguinte página: "+uri);
        }
        
        if(request.getAttribute("msg")!=null){
            return;
        }
        
        //para 0=nulo; 1=aluno; 2=professor
        int tipoUsuario = 0;
        
        HttpSession sessao = req.getSession(false);
        if(sessao!=null && sessao.getAttribute("user")!=null){
            Object o = sessao.getAttribute("user");
            if(o instanceof Aluno){
                tipoUsuario = 1;
            }
            else if(o instanceof Entidades.Professor){
                tipoUsuario = 2;
            }else{
                tipoUsuario = 0;
            }
        }else{
            tipoUsuario = 0;
        }

        if(tipoUsuario==1){
            if(ehPaginaRaiz(uri) || ehPaginaProfessor(uri)){
                request.setAttribute("msg", uri);
                res.sendRedirect("../faces/home.xhtml");
            }else{
                request.setAttribute("msg", uri);
                chain.doFilter(request, response);
            }
        }else if(tipoUsuario==2){
            if(ehPaginaRaiz(uri) || ehPaginaAluno(uri)){
                request.setAttribute("msg", uri);
                res.sendRedirect("../faces/homeProfessor.xhtml");
            }else{
                request.setAttribute("msg", uri);
                chain.doFilter(request, response);
            }
        }else{
            if(ehPaginaRaiz(uri)){
                request.setAttribute("msg", uri);
                chain.doFilter(request, response);
            }else{
                request.setAttribute("msg", uri);
                res.sendRedirect("../faces/index.xhtml");
            }
        }
        /*if(uri.contains("/Academia/") || uri.contains("/Academia/faces/index.xhtml") || uri.contains("/Academia/faces/erroLogin.xhtml")){
            System.out.println("paginas root!");
            chain.doFilter(request, response);
        }else{
            HttpSession session = req.getSession(false);
            boolean loggedIn = session != null && session.getAttribute("user") != null;
            boolean loginRequest = ((HttpServletRequest)request).getRequestURI().equals("/Academia/faces/index.xhtml");
            if (loggedIn == false) {
                System.out.println("sessao nula, vai pro index!");
                res.sendRedirect("../faces/index.xhtml");
                return;
            }
            else{
                System.out.println("tem sessao, entao passou!");
                chain.doFilter(request, response);
            }
        }*/
        chain.doFilter(request, response);
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FiltroGeral:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroGeral()");
        }
        StringBuffer sb = new StringBuffer("FiltroGeral(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
