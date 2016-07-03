/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.Aluno;
import ejb.CadastroLocal;
import ejb.CadastroLocal;
import ejb.CadastroLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Sergio Marinho
 */
@Path("cadastrousuario")
public class CadastroUsuarioResource {

    CadastroLocal cadastro1 = lookupCadastroLocal();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CadastroUsuarioResource
     */
    public CadastroUsuarioResource() {
    }
        
    /**
     * Retrieves representation of an instance of Negocio.CadastroUsuarioResource
     * @return an instance of java.lang.String
     */
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes("application/x-www-form-urlencoded")
    @Path("cadastrar")
    public String casdastrarUsuario(@FormParam("nome") String nome,@FormParam("cpf") String cpf,@FormParam("endereco") String endereco,@FormParam("password") String password,@FormParam("sexo") String sexo,@FormParam("nascimento") String nascimento,@FormParam("email") String email){     
        return "";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("testa")
    public String testa(){
        return "<raiz><msg>"+cadastro1.cadastraAluno()+"</msg></raiz>";
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CadastroUsuarioResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    private CadastroLocal lookupCadastroLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CadastroLocal) c.lookup("java:global/CadastroForaDeSessaoAcademia/CadastroForaDeSessaoAcademia-ejb/Cadastro!ejb.CadastroLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
