/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Aluno;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yuri
 */
public class AlunoDAO {
    
    static public void create(Aluno a) throws Exception{
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.save(a);
                trans.commit();
            }
            ArrayList<Aluno> al = (ArrayList<Aluno>) AlunoDAO.read();
            Aluno alu = al.get(0);
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
    
    static public void update(Aluno a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.saveOrUpdate(a);
                trans.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static public void delete(Aluno a){
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
    
    static public boolean authenticateUser(String nome, String password){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from Aluno a WHERE a.nome=:nome")
                .setParameter("nome", nome);
                List<Aluno> alunos = query.list();
                for(Aluno aluno:alunos){
                    if(aluno.getPassword().hashCode() == password.hashCode()){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
