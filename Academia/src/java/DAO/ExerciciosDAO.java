/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Entidades.Exercicios;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yuri
 */
public class ExerciciosDAO {
    
    static public boolean create(Exercicios a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.save(a);
                trans.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    static public List<Exercicios> read(){
        List <Exercicios> resultado;
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Query query =session.createQuery("from Exercicios");
                resultado = query.list();
                return resultado;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    static  public boolean update(Exercicios a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.saveOrUpdate(a);
                trans.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(Exercicios a){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                session.delete(a);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
