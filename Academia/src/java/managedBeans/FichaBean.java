/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.AlunoDAO;
import DAO.FichaDAO;
import Entidades.Aluno;
import Entidades.Exercicios;
import Entidades.Ficha;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.io.FileOutputStream;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import others.pdfWriter;






/**
 *
 * @author Yuri
 */
public class FichaBean extends BeanChecadorAluno implements Serializable{
    String fichaSelecionada;
    List<String> fichasNomes = new ArrayList<String>();
    List<Ficha> fichas;

    public String getFichaSelecionada() {
        return fichaSelecionada;
    }

    public void setFichaSelecionada(String fichaSelecionada) {
        this.fichaSelecionada = fichaSelecionada;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public List<String> getFichasNomes() {
        return fichasNomes;
    }

    public void setFichasNomes(List<String> fichasNomes) {
        this.fichasNomes = fichasNomes;
    }
    
    
    public FichaBean(){
        super();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        Aluno aluno = (Aluno)request.getSession().getAttribute("user");
        System.out.println(request.getSession().getAttribute("user") instanceof Aluno);
        fichas = AlunoDAO.getFicha(aluno);
        if(fichas.size() > 0 ){
            for(Ficha ficha:fichas){
                fichasNomes.add(ficha.getDescricao());
            }
        }
    }
    
    public Ficha getFichaFromString(){
        for(Ficha ficha:fichas){
            if(ficha.getDescricao().hashCode() == fichaSelecionada.hashCode()){
               return ficha;
            }
        }
        return null;
    }

    public void printFicha(){
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest)context.getRequest();
            Aluno aluno = (Aluno)request.getSession().getAttribute("user");
            Ficha fichaEscolhida = getFichaFromString();
            List<Exercicios> exercicios = FichaDAO.getAllExercicios(fichaEscolhida);
            String filePath = System.getProperty("user.home") + "\\Desktop\\ficha" + aluno.getNome() +" "+ fichaEscolhida.getDescricao() + ".pdf" ;
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();
                pdfWriter.addTitlePage(document, fichaEscolhida.getDescricao(), aluno.getNome());
                document.add(Chunk.NEWLINE);
                pdfWriter.createTableExercicios(document, exercicios);
                document.close();
                
                
                File file = new File(filePath);
                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                
                response.reset();
                response.setHeader("Content-Disposition", "attachment;filename=Ficha.pdf");
                response.setContentLength((int) file.length());
                ServletOutputStream out = null;
                try {
                    FileInputStream input = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    out = response.getOutputStream();
                    int i = 0;
                    while ((i = input.read(buffer)) != -1) {
                        out.write(buffer);
                        out.flush();
                    }
                    FacesContext.getCurrentInstance().getResponseComplete();
                } catch (IOException err) {
                    err.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        } catch (Exception e) {
        }
      
       
    }
    
    
}
