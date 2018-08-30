package org.client;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

import static org.client.Utils_uploadFile.execShell;
import static org.client.Utils_uploadFile.mkshscpdir;
import static org.connection.TetsPost.jsonPost;

public class Service {
    public static String execService(String msg) throws InterruptedException, SQLException, IOException {
        /*
          到这里的msg应该是报文中的args部分
          即:{"serviceId":"asdasdasd","info":"/test"}
         */
        JSONObject obj = JSONObject.parseObject(msg);
        String cmd = "{\"service\":\"findService\",\"args\":\""+obj.getString("serviceId")+"\"}";
        System.out.println(cmd);
        //此处应填写中心服务器的url
        String appropriateIP = jsonPost("http://192.168.56.1:8888", cmd);
        cmd = "{\"service\":\"provideService\",\"args\":\""+obj.getString("info")+"\"}";
        System.out.println(cmd);
        return jsonPost("http://"+appropriateIP+":8888",msg);
    }

    public static String createService(String msg){
        //此处同上
        return jsonPost("http://192.168.56.1:8888","{\"service\":\"createService\",\"args\":\""+msg+"\"}");
    }

    public static String uploadFile(String msg) throws IOException, InterruptedException {
        JSONObject obj = JSONObject.parseObject(msg);
        mkshscpdir(obj.getString("path"),obj.getString("serviceId"));
        execShell();
        System.out.println("uploadFileuploadFileuploadFile");
        return "aaa";
    }
}
