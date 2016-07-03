/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.ExercicioFicha;
import Entidades.Exercicios;
import Entidades.Ficha;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yuri
 */
public class FichaDAO {
    
    static public void create(Ficha a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.save(a);
                trans.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static public List<Ficha> read(){
        List <Ficha> resultado;
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Query query =session.createQuery("from Professor");
                resultado = query.list();
                return resultado;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    static public void update(Ficha a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Ficha up = session.get(Ficha.class, a.getCodigo());
                up.setAluno(a.getAluno());
                up.setInstrutor(a.getInstrutor());
                Transaction trans = session.beginTransaction();
                session.saveOrUpdate(up);
                trans.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static public void delete(Ficha a){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                session.delete(a);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static public List<Exercicios> getAllExercicios(Ficha a){
        List <Exercicios> exercicios = new ArrayList<Exercicios>();
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                System.out.println("entrei!!!!!!!!!!!!!!" );
                Query query = session.createQuery("from ExercicioFicha E WHERE E.codigoFicha=:codigo")
                        .setParameter("codigo", a);
                System.out.println(query);
                List<ExercicioFicha> exerciciosCode = query.list();
                System.out.println(exerciciosCode.size());
                for(ExercicioFicha ef:exerciciosCode){
                    exercicios.add(ef.getCodigoExercicio());
                }
                return exercicios;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }
}
