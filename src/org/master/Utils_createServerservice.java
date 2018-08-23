package org.master;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;

public class Utils_createServerservice {
    private String driver;
    private String url;
    private String user;
    private String pass;
    public void initParam(String paramFile) throws Exception {
        //使用properties类来加载属性文件
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    }

    public void createTable(String sql) throws Exception {
        //加载驱动类
        Class.forName(driver);
        try (
                //获取数据库连接
                Connection conn = DriverManager.getConnection(url, user, pass);
                //使用Connection来创建一个Statement对象
                Statement stmt = conn.createStatement()) {
            //执行DDL语句，创建数据表
            stmt.executeUpdate(sql);
        }
    }

    public int insertDate(String sql) throws Exception {
        //加载驱动类
        Class.forName(driver);
        try (
                //获取数据库连接
                Connection conn = DriverManager.getConnection(url, user, pass);
                //使用Connection来创建一个Statement对象
                Statement stmt = conn.createStatement()) {
            //执行DML语句,返回受影响的记录条数
            return stmt.executeUpdate(sql);
        }
    }



    public static void mkdir(String serviceId){
        String path = "/home/legendhu/userfiles/"+serviceId;
        File file1 = new File(path);
        file1.mkdir();
        File file2 = new File(path+"/code");
        file2.mkdir();
        File file3 = new File(path+"/env");
        file3.mkdir();
    }
}
