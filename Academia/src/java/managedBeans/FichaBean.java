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
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import others.pdfWriter;






/**
 *
 * @author Yuri
 */
public class FichaBean implements Serializable{
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
       FacesContext context = FacesContext.getCurrentInstance();
       HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       Aluno aluno = (Aluno)request.getSession().getAttribute("user"); 
       Ficha fichaEscolhida = getFichaFromString();
       List<Exercicios> exercicios = FichaDAO.getAllExercicios(fichaEscolhida);
        System.out.println(System.getProperty("user.home"));
       String File = System.getProperty("user.home") + "\\Desktop\\ficha" + aluno.getNome() +" "+ fichaEscolhida.getDescricao() + ".pdf" ;
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(File));
            document.open();
            pdfWriter.addTitlePage(document, fichaEscolhida.getDescricao(), aluno.getNome());
            document.add(Chunk.NEWLINE);
            pdfWriter.createTableExercicios(document, exercicios);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    
}
