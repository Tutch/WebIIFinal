/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Aluno;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Yuri
 */
public class AlunoDAO {
     public Session openSession(){
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry standardRegistry= new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(standardRegistry);
            Session session = sessionFactory.openSession();
            return session;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void create(Aluno a){
        try {
            Session session = openSession();
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
            Session session = openSession();
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
            Session session = openSession();
            if(session !=null){
                Aluno up = session.get(Aluno.class, a.getCodigo());
                up.setCpf(a.getCpf());
                up.setNome(a.getNome());
                up.setEndereco(a.getEndereco());
                up.setEmail(a.getEmail());
                up.setAtestado(a.isAtestado());
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
            Session session = openSession();
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
