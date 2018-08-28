package org.client;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

import static org.client.Utils_uploadFile.execShell;
import static org.client.Utils_uploadFile.mkshscpdir;
import static org.connection.TetsPost.jsonPost;

public class Service {
    public static String execService(String msg) throws InterruptedException, SQLException, IOException {
        JSONObject obj = JSONObject.parseObject(msg);
        String cmd = "{\"service\":\"findService\",\"args\":\""+obj.getString("serviceId")+"\"}";
        System.out.println(cmd);
        String appropriateIP = jsonPost("http://127.0.0.1:8888", cmd);
        cmd = "{\"service\":\"provideService\",\"args\":\""+obj.getString("info")+"\"}";
        System.out.println(cmd);
        return jsonPost("http://"+appropriateIP+":8888",msg);
    }

    public static String createService(String msg){
        return jsonPost("http://127.0.0.1:6666","{\"service\":\"createService\",\"args\":\""+msg+"\"}");
    }

    public static String uploadFile(String msg) throws IOException, InterruptedException {
        JSONObject obj = JSONObject.parseObject(msg);
        mkshscpdir(obj.getString("path"),obj.getString("serviceId"));
        execShell();
        System.out.println("uploadFileuploadFileuploadFile");
        return "aaa";
    }
}
