/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ws;

import DAO.AlunoDAO;
import Entidades.Aluno;
import java.util.Date;
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
@Path("addcliente")
public class AddCliente {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddCliente
     */
    public AddCliente() {
    }

    /**
     * Retrieves representation of an instance of Ws.AddCliente
     * @return an instance of java.lang.String
     */
    
    @POST
    @Produces("application/xml")
    @Consumes("application/x-www-form-urlencoded")
    @Path("add")
    public String add(@FormParam("nome")String nome,@FormParam("cpf") long cpf,@FormParam("endereco") String endereco,@FormParam("senha") String senha,@FormParam("sexo") char sexo,@FormParam("atestado") boolean atestado, @FormParam("nascimento") Date nascimento,@FormParam("email")String email){
        String retorno = "";
        try{
            Aluno a = new Aluno(nome, cpf, sexo, atestado, endereco, nascimento, senha, email );
            AlunoDAO ad = new AlunoDAO();
            ad.create(a);
        }catch(Exception e){
            retorno= "<raiz>false</raiz>";
            return retorno;
        }
        retorno = "<raiz>true</raiz>"; 
        return retorno;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AddCliente
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
