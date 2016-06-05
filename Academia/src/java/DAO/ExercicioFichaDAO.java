/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.ExercicioFicha;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yuri
 */
public class ExercicioFichaDAO {
    
    public void create(ExercicioFicha ef){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                Transaction trans = session.beginTransaction();
                session.save(ef);
                trans.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<ExercicioFicha> read(){
        List <ExercicioFicha> resultado;
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
    
    public void update(ExercicioFicha ef){
        try {
            Session session = BaseDAO.openSession();
            if(session !=null){
                ExercicioFicha up = session.get(ExercicioFicha.class, ef.getCodigo());
                up.setCodigoExercicio(ef.getCodigoExercicio());
                up.setCodigoFicha(ef.getCodigoFicha());
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
                ExercicioFicha ef = session.get(ExercicioFicha.class, codigo);
                session.delete(ef);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
