package org.master;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.pojo.Service;
import static org.connection.DBConn.getconn;

public class Algorithm {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }

    public static String selectServiceMachine(List<String> ipList){
        return  ipList.get(0);
    }

    //如果服务未被加载进集群,调用该算法选择一台机器
    public static String selectLoadMachine(String serviceid) throws SQLException {
        Connection conn=getconn();
        String sql="select * FROM node ORDER BY runService ASC";
        PreparedStatement ps;
        ResultSet rs=null;
        ps=(PreparedStatement) conn.prepareStatement(sql);
        rs=ps.executeQuery();
        //下面是有集群节点可用的情况
        if (rs.next()){
            sql="insert into relationship (serviceId, nodeIP) values (?,?)";
            ps=(PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1,serviceid);
            ps.setString(2,rs.getString("innerip"));
            ResultSet rs2=null;
            ps.execute();
            return rs.getString("innerip");
        }
        else return "error";
    }


}
