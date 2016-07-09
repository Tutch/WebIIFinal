/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Aluno;
import Entidades.Ficha;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yuri
 */
public class AlunoDAO {
    
    static public boolean create(Aluno a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.save(a);
                trans.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    static public List<Aluno> read(){
        List <Aluno> resultado;
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Query query =session.createQuery("from Aluno");
                resultado = query.list();
                return resultado;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    static public boolean update(Aluno a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.saveOrUpdate(a);
                trans.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    static public boolean delete(Aluno a){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                session.delete(a);
                tx.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    static public List<Ficha> getFicha(Aluno a){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from Ficha F WHERE F.aluno=" + a.getCodigo());
                List<Ficha> fichas = query.list();
                session.close();
                return fichas;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    static public Aluno authenticateUser(String cpf, String password){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from Aluno a WHERE a.cpf=:cpf")
                .setParameter("cpf", cpf);
                List<Aluno> alunos = query.list();
                for(Aluno aluno:alunos){
                    if(aluno.getPassword().hashCode() == password.hashCode()){
                        return aluno;
                    }
                }
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
