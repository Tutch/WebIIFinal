/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.ProfessorDAO;
import Entidades.Aluno;
import Entidades.Professor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import others.pdfWriter;

/**
 *
 * @author Yuri
 */
public class ListarAlunosBean {
    private List<Aluno> listaAlunos;

    public ListarAlunosBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        Professor professor = (Professor)request.getSession().getAttribute("user");
        listaAlunos = ProfessorDAO.getAllAlunos(professor);
        System.out.println(listaAlunos.size());
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }
    
    public void printToPDF(){
        FacesContext context = FacesContext.getCurrentInstance();
       HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       Professor professor = (Professor)request.getSession().getAttribute("user");
       String File = System.getProperty("user.home") + "\\Desktop\\ficha" + professor.getNome() + ".pdf" ;
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(File));
            document.open();
            pdfWriter.addTitlePageProfessor(document, "Lista de alunos do professor " + professor.getNome(), professor.getNome());
            document.add(Chunk.NEWLINE);
            pdfWriter.createTableAlunos(document, listaAlunos);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
