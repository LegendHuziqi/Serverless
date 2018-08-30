package org.master;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.pojo.Node;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.alibaba.fastjson.JSON.parseObject;
import static org.connection.DBConn.getconn;

public class Utils_joinCluster {
    public static Node parseJson(String data){
        Node node = new Node();
        JSONObject obj = parseObject(data); // 把JSON文本parse为JavaBean
        node = JSON.parseObject(data, new TypeReference<Node>() {});
        return node;
    }

    public static void execSQL(Node node) throws SQLException {
        Connection conn=getconn();
        String sql="insert into node values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        ResultSet rs=null;
        ps=(PreparedStatement) conn.prepareStatement(sql);
        ps.setString(1,node.getNodeId());
        ps.setString(2,node.getNodeName());
        ps.setString(3,node.getNodeLocation());
        ps.setString(4,node.getNodeAbility());
        ps.setString(5,node.getNodeCPUCore());
        ps.setString(6,node.getNodeCPUFrequency());
        ps.setString(7,node.getNodeRAM());
        ps.setString(8,node.getNodeAvailableROM());
        ps.setString(9,node.getInnerIP());
        ps.setString(10,node.getOuterIP());
        ps.setString(11,node.getRunService());
        ps.execute();
        }
}
