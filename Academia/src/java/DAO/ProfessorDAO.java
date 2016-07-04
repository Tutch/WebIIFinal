/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Entidades.Aluno;
import Entidades.Professor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yuri
 */
public class ProfessorDAO {
    
    static public void create(Professor a){
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
    
    static public List<Professor> read(){
        List <Professor> resultado;
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
    
    static public void update(Professor a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
//                Professor up = session.get(Professor.class, a.getCodigo());
//                up.setCpf(a.getCpf());
//                up.setNome(a.getNome());
//                up.setEmail(a.getEmail());
//                up.setPassword(a.getPassword());
                Transaction trans = session.beginTransaction();
                session.saveOrUpdate(a);
                trans.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static public void delete(Professor a){
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
    
    static public List<Aluno> getAllAlunos(Professor a){
       List<Aluno> alunos;
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from Aluno A WHERE A.instrutor=" + a.getCodigo());
                alunos = query.list();
                return alunos;
            }
        } catch (Exception e) {
        }
       return null;
    }
    static public boolean authenticateUser(String cpf, String password){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from Professor p WHERE p.cpf=:cpf")
                .setParameter("cpf", cpf);
                List<Professor> professores = query.list();
                for(Professor professor:professores){
                    if(professor.getPassword().hashCode() == password.hashCode()){
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
