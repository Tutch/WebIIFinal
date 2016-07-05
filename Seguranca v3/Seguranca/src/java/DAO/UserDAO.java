/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuri
 */
public class UserDAO {
    private static final String DB_PATH = "jdbc:postgresql://localhost:5432/security";
    private static final String DB_USER = "postgres";
    private static final String DB_PW = "ufc123";
    
    
    static public boolean AuthenticateUser(String login, String password) throws SQLException{
        String retrievedPW = "";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("SELECT password from USUARIO WHERE name = ?");
            psmt.setString(1, login);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                retrievedPW = rs.getString("password");
            }
            rs.close();
            psmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(retrievedPW.length() > 0){
           if(password.hashCode() == retrievedPW.hashCode()){
               return true;
           } 
        }
        return false;
    }
    
    static public void createUser(String name, String description) throws SQLException{
        name=Util.Seguranca.trata(name);
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("INSERT INTO USUARIO VALUES(?, ?)");
            psmt.setString(1, name);
            psmt.setString(2, description);
            psmt.executeUpdate();
            
            psmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static public void updateUser(String name, String description, int itemID) throws SQLException{
        name=Util.Seguranca.trata(name);
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("UPDATE USUARIO SET name = ?, password = ? WHERE id = ?");
            psmt.setString(1, name);
            psmt.setString(2, description);
            psmt.setInt(3, itemID);
            psmt.executeUpdate();
            
            psmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static public void deleteUsuario(int itemID) throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("DELETE FROM USUARIO WHERE id = ?");
            psmt.setInt(1, itemID);
            psmt.executeUpdate();
            
            psmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
