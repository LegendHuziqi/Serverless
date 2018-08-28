package org.slave;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.pojo.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.alibaba.fastjson.JSON.parseObject;

public class Utils_provideService {
    public static String post2Service(String port,String command) {
//        System.setProperty("http.keepalive","false");
        try {System.out.println("post");
            URL url = new URL("http://127.0.0.1:"+port);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "*/*"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "multipart/form-data"); // 设置发送数据的格式
            connection.setRequestProperty("Connection", "keep-alive");
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(command);
            out.flush();
            out.close();


            System.out.println("response");
            BufferedReader read=new BufferedReader(new InputStreamReader
                    (connection.getInputStream()));
            String result=read.readLine();
            return result;
//            while ((result=read.readLine())!=null){
//                System.out.println(result);
//            }
        } catch (IOException e) {
//            LOG.error("Exception occur when send http post request!", e);
        }
        return "error"; // 自定义错误信息
    }
    public static JSONObject parseJson(String data){
        JSONObject obj=JSON.parseObject(data);
        return obj;
    }

}
