/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sayre
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Planner {

    
    public static void main(String[] args) {
        new Planner().run();
    }
     
    public static void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
          
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
                        
        } 
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
          
    }
    public static void createNewTable() {
        
        // SQLite connection string
        String url = "jdbc:sqlite:C:/sqlite/db/planner.db";
        
         // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS planner (\n"
                + "	class text KEY VALUE,\n"
                + "	assignment text,\n"
                + "     duedate text \n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table Created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection connect() {
        Connection conn = null;
        // db parameters
            String url = "jdbc:sqlite:C:/sqlite/db/planner.db";
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("...");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    
        return conn;
    }
    public void insert(String clas, String assignment, String duedate) {
        String sql = "INSERT INTO planner(class,assignment,duedate) VALUES(?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, clas);
            pstmt.setString(2, assignment);
            pstmt.setString(3, duedate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public void selectAll(){
        String sql = "SELECT class, assignment, duedate FROM planner";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            
            while (rs.next()) {
                System.out.println(rs.getString("class") +  "\t" + 
                                   rs.getString("assignment") + "\t" +
                                   rs.getString("duedate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
      public void delete() {
        String sql = "DELETE FROM planner";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void run() {
        Planner app = new Planner();
        createNewDatabase("planner.db");
        connect();
        createNewTable();
        app.insert("JAVA", "SQLITE assignment", "10/31");
        app.insert("Compiler", "Recursive Decent Parser", "11/7");
        app.insert("Network", "Humidity Sensor", "11/31");
        app.selectAll();
        app.delete();
    }
    
}
