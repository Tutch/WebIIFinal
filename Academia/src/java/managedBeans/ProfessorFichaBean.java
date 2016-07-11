/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.AlunoDAO;
import DAO.FichaDAO;
import DAO.ProfessorDAO;
import Entidades.Aluno;
import Entidades.Exercicios;
import Entidades.Ficha;
import Entidades.Professor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import others.pdfWriter;

/**
 *
 * @author Yuri
 */
public class ProfessorFichaBean {
    String fichaSelecionada;
    String alunoSelecionado;
    List<String> alunosNomes = new ArrayList<>();
    List<String> fichasNomes = new ArrayList<>();
    List<Ficha> fichas;
    List<Aluno> alunos;

    public ProfessorFichaBean(){
        super();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        Professor professor = (Professor)request.getSession().getAttribute("user");
        alunos = ProfessorDAO.getAllAlunos(professor);
        if(alunos.size() > 0 ){
            for(Aluno aluno:alunos){
                alunosNomes.add(aluno.getNome());
            }
        }
    }
    public void getFichasFicha(ValueChangeEvent event) throws MalformedURLException{
        fichaSelecionada = (String) event.getNewValue();
    }
    public void getFichasAluno(ValueChangeEvent event) throws MalformedURLException{
        setAlunoSelecionado((String)event.getNewValue());
        fichas = AlunoDAO.getFicha(getAlunoFromString(alunoSelecionado));
        if(fichas.size() > 0 ){
            for(Ficha ficha:fichas){
                fichasNomes.add(ficha.getDescricao());
            }
        }        
    }
    public String getFichaSelecionada() {
        return fichaSelecionada;
    }

    public void setFichaSelecionada(String fichaSelecionada) {
        this.fichaSelecionada = fichaSelecionada;
    }

    public String getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(String alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public List<String> getAlunosNomes() {
        return alunosNomes;
    }

    public void setAlunosNomes(List<String> alunosNomes) {
        this.alunosNomes = alunosNomes;
    }

    public List<String> getFichasNomes() {
        return fichasNomes;
    }

    public void setFichasNomes(List<String> fichasNomes) {
        this.fichasNomes = fichasNomes;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public Aluno getAlunoFromString(String alunoString){
        System.out.println("String qual é " + alunoSelecionado);
        for(Aluno aluno:alunos){
               if(alunoString.hashCode() == aluno.getNome().hashCode()){
                   return aluno;
               }
        }
        return null;
    }
    
    public Ficha getFichaFromString(String fichaString){
        System.out.println("String qual é " + fichaSelecionada);
        for(Ficha ficha:fichas){
            System.out.println("ficha " + ficha.getDescricao() + "FichaSelecionada " + fichaString + " eval" + (ficha.getDescricao().hashCode() == fichaString.hashCode()));
            if(ficha.getDescricao().hashCode() == fichaString.hashCode()){
               return ficha;
            }
        }
        return null;
    }
    
    public void printFicha(String fichaSelecionada, String alunoSelecionado){
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest)context.getRequest();
            Professor professor = (Professor)request.getSession().getAttribute("user");
            Aluno aluno = getAlunoFromString(alunoSelecionado);
            Ficha fichaEscolhida = getFichaFromString(fichaSelecionada);
            System.out.println("Ficha " + fichaEscolhida + "Aluno " + aluno);
            List<Exercicios> exercicios = FichaDAO.getAllExercicios(fichaEscolhida);
            String filePath = System.getProperty("user.home") + "\\Desktop\\ficha" + professor.getNome() +" "+ fichaEscolhida.getDescricao() + ".pdf" ;
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
            e.printStackTrace();
        }
        setAlunoSelecionado(null);
        setFichaSelecionada(null);
    }
    
}
