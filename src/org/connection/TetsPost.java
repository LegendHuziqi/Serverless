package org.connection;

//import com.google.gson.Gson;
//import pojo.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TetsPost {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i=100;i<101;i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    jsonPost("http://127.0.0.1:8888", "{\"service\":\"createServerService\",\"args\":\"test\"}");
                }
            }).start();
        }

    }
    public static String jsonPost(String strURL,String command) {
//        System.setProperty("http.keepalive","false");
        try {System.out.println("read  1");
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
//            connection.setRequestProperty("","");
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "*/*"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "multipart/form-data"); // 设置发送数据的格式
            connection.setRequestProperty("Connection", "keep-alive");
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(command);
            out.flush();
            out.close();


            System.out.println("read 2");
            BufferedReader read=new BufferedReader(new InputStreamReader
                    (connection.getInputStream()));
            String result="";

            while ((result=read.readLine())!=null){
                System.out.println("read");
                System.out.println(result);
            }
            System.out.println(connection.getHeaderField("Cookie"));
        } catch (IOException e) {
//            LOG.error("Exception occur when send http post request!", e);
        }
        return "error"; // 自定义错误信息
    }

}
