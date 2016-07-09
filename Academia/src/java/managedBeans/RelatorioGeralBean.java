/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.AlunoDAO;
import DAO.ExerciciosDAO;
import DAO.FichaDAO;
import DAO.ProfessorDAO;
import Entidades.Aluno;
import Entidades.Exercicios;
import Entidades.Ficha;
import Entidades.Professor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import others.pdfWriter;

/**
 *
 * @author Yuri
 */
public class RelatorioGeralBean {
    private List<Aluno> listaAlunos;
    private List<Professor> listaProfessores;
    private List<Exercicios> listaExercicios;
    private List<Ficha> listaFichas;
    
    private BarChartModel barModel;

    public RelatorioGeralBean(){
   /*     FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        Professor professor = (Professor)request.getSession().getAttribute("user");
        listaAlunos = AlunoDAO.read();
        listaProfessores = ProfessorDAO.read();
        listaFichas = FichaDAO.read();
        listaExercicios = ExerciciosDAO.read();
    */

   
         Aluno a;
         a = new Aluno("Marcos Teste", "123456789", 'M', false, "Rua do teste", null, "Email Teste", "teste");
        
         Exercicios b = new Exercicios();
         b.setNome("Supino Birl2");
         b.setDescricao("biiiirl");
         b.setMusculo("Biceps");
            
         Professor p = new Professor("Teste prof", "484654654l","sadsda", "1234");
         
         a.setAtestado(true);
         a.setInstrutor(p);
         
         Ficha f;
         f = new Ficha("Crescer pra carai", a, p);
         
        listaAlunos = new ArrayList<Aluno>();
        listaProfessores = new ArrayList<Professor>();
        listaExercicios = new ArrayList<Exercicios>();
        listaFichas = new ArrayList<Ficha>();
        
        listaAlunos.add(a);
        listaAlunos.add(a);
        listaAlunos.add(a);
        listaAlunos.add(a);
        listaAlunos.add(a);
        listaAlunos.add(a);
        
        listaExercicios.add(b);
        listaExercicios.add(b);
        listaExercicios.add(b);
        
        listaProfessores.add(p);
        listaProfessores.add(p);
        
        listaFichas.add(f);
        listaFichas.add(f);
        listaFichas.add(f);
   
        createBarModel();
    }
    
    
    
    
    

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
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
       DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
       Date date = new Date();
       String File = System.getProperty("user.home") + "\\Desktop\\RelatorioGeral" + dateFormat.format(date) + ".pdf" ;
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(File));
            document.open();
            pdfWriter.addTitlePageProfessor(document, "Relatorio Geral", professor.getNome());
            document.add(Chunk.NEWLINE);
            pdfWriter.createTableGeral(document, listaAlunos);
            pdfWriter.addNumbers(document, listaAlunos.size(),listaProfessores.size(), listaExercicios.size(), listaFichas.size());
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        
        ChartSeries alunos = new ChartSeries();
        alunos.setLabel("Alunos");
        alunos.set("Atual", listaAlunos.size());
 
        ChartSeries professores = new ChartSeries();
        professores.setLabel("Professores");
        professores.set("Atual", listaProfessores.size());
        
        ChartSeries exercicios = new ChartSeries();
        exercicios.setLabel("Exercicios");
        exercicios.set("Atual", listaExercicios.size());
        
        ChartSeries fichas = new ChartSeries();
        fichas.setLabel("Fichas");
        fichas.set("Atual", listaFichas.size());
 
        model.addSeries(alunos);
        model.addSeries(professores);
        model.addSeries(exercicios);
        model.addSeries(fichas);
         
        return model;
    }
    private int getMaxNum(){
        int maxNum = listaAlunos.size();
        if(maxNum < listaExercicios.size()){
            maxNum = listaExercicios.size();
        }if(maxNum < listaFichas.size()){
            maxNum = listaFichas.size();
        }if(maxNum < listaProfessores.size()){
            maxNum = listaProfessores.size();
        }
        return maxNum;
    }
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Gráfico do Relatório");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Valor");
        yAxis.setMin(0);
        yAxis.setMax(getMaxNum() + 5);
    }
}
