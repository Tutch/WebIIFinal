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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import others.pdfWriter;

/**
 *
 * @author Yuri
 */
public class ListarAlunosBean extends BeanChecadorProfessor{
    private List<Aluno> listaAlunos;

    public ListarAlunosBean(){
        super();
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
       ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
       HttpServletRequest request = (HttpServletRequest)context.getRequest(); 
       Professor professor = (Professor)request.getSession().getAttribute("user");
       String filePath = System.getProperty("user.home") + "\\Desktop\\ficha" + professor.getNome() + ".pdf" ;
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            pdfWriter.addTitlePageProfessor(document, "Lista de alunos do professor " + professor.getNome(), professor.getNome());
            document.add(Chunk.NEWLINE);
            pdfWriter.createTableAlunos(document, listaAlunos);
            document.close();
            
            File file = new File(filePath);
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=Alunos.pdf");
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
    }
    
    
}
