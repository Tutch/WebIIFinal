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
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Yuri
 */
public class ExerciciosDAO {
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
    
    public void create(Exercicios a){
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
    
    public List<Exercicios> read(){
        List <Exercicios> resultado;
        try {
            Session session = openSession();
            if(session !=null){
                Query query =session.createQuery("from Exercicios");
                resultado = query.list();
                return resultado;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void update(Exercicios a){
        try {
            Session session = openSession();
            if(session !=null){
                Exercicios up = session.get(Exercicios.class, a.getCodigo());
                up.setDescricao(a.getDescricao());
                up.setMusculo(a.getMusculo());
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
                Exercicios a = session.get(Exercicios.class, codigo);
                session.delete(a);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
