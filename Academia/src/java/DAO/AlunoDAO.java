/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Aluno;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yuri
 */
public class AlunoDAO {
    
    public void create(Aluno a){
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
    
    public List<Aluno> read(){
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
    
    public void update(Aluno a){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Aluno up = session.get(Aluno.class, a.getCodigo());
                up.setCpf(a.getCpf());
                up.setNome(a.getNome());
                up.setEndereco(a.getEndereco());
                up.setEmail(a.getEmail());
                up.setAtestado(a.isAtestado());
                up.setPassword(a.getPassword());
                Transaction trans = session.beginTransaction();
                session.saveOrUpdate(up);
                trans.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Long codigo){
        try {
            Session session = BaseDAO.openSession();
            if(session != null){
                Transaction tx = session.beginTransaction();
                Aluno a = session.get(Aluno.class, codigo);
                session.delete(a);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
