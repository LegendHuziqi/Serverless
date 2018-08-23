package org.connection;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    public static Connection getconn(){
        String driver = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://127.0.0.1/serverless?useSSL=false";
        String username="root";
        String password="root";
        Connection conn=null;
        try {
            Class.forName(driver);
            conn=(Connection) DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
