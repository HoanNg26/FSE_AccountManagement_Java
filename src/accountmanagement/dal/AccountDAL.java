/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanagement.dal;

import accountmanagement.dto.AccountDTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Administrator
 */
public class AccountDAL {
    
    private String connectionString;
    private String username;
    private String password;
    
    public AccountDAL() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("app.properties"));
        connectionString = prop.getProperty("hostname");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        
    }
    
    public AccountDAL(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * @return the connectionString
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
    public boolean insert(AccountDTO accdto) {
        
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.connectionString, this.username, this.password);

            // the mysql insert statement
            String query = " insert into account (fullname, username, password, createdate)"
                    + " values (?, ?, ?, ?)";
            //System.out.print("Database is connected !");
            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, accdto.getFullname());
            preparedStmt.setString(2, accdto.getUsername());
            preparedStmt.setString(3, accdto.getPassword());
            preparedStmt.setDate(4, accdto.getCreatedate());
            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            System.out.print("Do not connect to DB - Error:" + e);
            return false;
        } finally {
            try {
                rs.close();
            } catch (Exception e) { /* ignored */ }
            try {
                preparedStmt.close();
            } catch (Exception e) { /* ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* ignored */ }
        }
        return true;
    }
}
