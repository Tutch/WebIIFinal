/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuri
 */
public class ItemDAO {
    private static final String DB_PATH = "jdbc:postgresql://localhost:5432/security";
    private static final String DB_USER = "postgres";
    private static final String DB_PW = "ufc123";
    
    

    static public void createItem(String name, String description) throws SQLException{
        name=Util.Seguranca.trata(name);
        description=Util.Seguranca.trata(description);
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("INSERT INTO ITEM VALUES(?, ?)");
            psmt.setString(1, name);
            psmt.setString(2, description);
            psmt.executeUpdate();
            
            psmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static public void updateItem(String name, String description, int itemID) throws SQLException{
        name=Util.Seguranca.trata(name);
        description=Util.Seguranca.trata(description);
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("UPDATE ITEM SET name = ?, description = ? WHERE id = ?");
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
    static public void deleteItem(int itemID) throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("DELETE FROM ITEM WHERE id = ?");
            psmt.setInt(1, itemID);
            psmt.executeUpdate();
            
            psmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static public Item retrieveItem(int itemID) throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("SELECT * FROM ITEM WHERE id = ?");
            psmt.setInt(1, itemID);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                Item a  = new Item(rs.getString("name"), rs.getString("description"));
                return a;
            }
            rs.close();
            psmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    static public ArrayList<Item> retrieveAllItens() throws SQLException{
        ArrayList<Item> al = new ArrayList<Item>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
            PreparedStatement psmt = connection.prepareStatement("SELECT * FROM ITEM");
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                Item a  = new Item(rs.getString("name"), rs.getString("description"));
                al.add(a);
            }
            rs.close();
            psmt.close();
            connection.close();
            return al;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
