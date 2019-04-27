/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devis;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class MyConnection {
    public static String username = "root";
    public static String password = "root";
//    public static String url = "jdbc:derby://localhost:1527/Expert";
    public static String url = "jdbc:derby:Expert;create=true;";
    public Connection connection;
    public Statement stmt;
    public ResultSet rs;
   
    public static boolean testDbConnection(){
        try{
            Connection testConnection = DriverManager.getConnection(MyConnection.url, MyConnection.username, MyConnection.password);
            testConnection.close();
            return true;
        }catch (SQLException e){
            return false;
        }

    }
    
//    public void connect(){
//        try {
//            this.connection = DriverManager.getConnection(MyConnection.url, MyConnection.username, MyConnection.password);
//        } catch (SQLException ex) {
//            
//        }
//    }
    
    public void connect () {
        try {
            Driver derbyEmbeddedDriver = new org.apache.derby.jdbc.EmbeddedDriver();
            DriverManager.registerDriver(derbyEmbeddedDriver);
            
            this.connection = DriverManager.getConnection(MyConnection.url, MyConnection.username, MyConnection.password);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> getItems(String itemType){
        List<String> results = new ArrayList<String>();
        results.add("vide");
        String query = "select * from APP.\"Components\" where type = '"+itemType+"'";
        try {
            connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                 results.add(rs.getString("NAME"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results;
        
    }
    
    public List<String> getItems2(String itemType){
        // connect to the db.
        List<String> results = new ArrayList<String>();
        results.add("vide");
        // prepare query first
        String query = "select * from APP.\"Components\" where type = '"+itemType+"'";
        try {
            connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                 results.add(rs.getString("NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results;
    }

    public String getValue(String itemName, String valueType){
        if( itemName.equals("vide")){
            return String.valueOf(0);
        }
        String query = "select "+valueType+" from APP.\"Components\" where Name = '"+itemName+"'";
        String result = "";
        try {
            connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                result = rs.getString(valueType);
            }
        } catch (SQLException e) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public Object[][] loadData(){
        int nbArticles = getNbArticles();
        Object[][] data = new Object[nbArticles][6];
        String query = "select * from App.\"Components\"";
        try {
            connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getString("Id");
                data[i][1] = rs.getString("Name");
                data[i][2] = rs.getString("Type");
                data[i][3] = rs.getString("Prix_Achat");
                data[i][4] = rs.getString("Prix_Gros");
                data[i][5] = rs.getString("Prix_Detail");
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Not connected yet to the DB");
        }
        
        return data;
    }
    
    public int getNbArticles(){
        try {
            String query = "SELECT count('id') FROM APP.\"Components\"";
            connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                return Integer.valueOf(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        // No articles.
        return 0;
    }
    
    public int insertNewArticle(String name, String type, String achat, String gros, String detail) {
        String query = "INSERT INTO APP.\"Components\" (\"NAME\", \"TYPE\", PRIX_ACHAT, PRIX_GROS, PRIX_DETAIL) VALUES ('"+name+"', '"+type+"', "+achat+", "+gros+", "+detail+")";
        connect();
        try {
            stmt = connection.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    public int deleteArticle(String id) {
        String query = "DELETE FROM APP.\"Components\" WHERE \"Id\" = "+id+"";
        connect();
        try {
            stmt = connection.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int editArticle(String name, String type, String achat, String gros, String detail, String id) {
        String query = "UPDATE APP.\"Components\" SET \"NAME\" = '"+name+"', \"TYPE\" = '"+type+"', \"PRIX_ACHAT\" = "+achat+", \"PRIX_GROS\" = "+gros+", \"PRIX_DETAIL\" = "+detail+" WHERE \"Id\" ="+id+"";
        connect();
        try {
             stmt = connection.createStatement();
             return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            return 0;
        }    
        
    }
}
