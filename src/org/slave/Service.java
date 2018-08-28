package org.slave;

import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.json.JsonObjectDecoder;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.slave.Utils_provideService.post2Service;
import static org.slave.Utils_startServer.readProps;
import static org.slave.Utils_startServer.runServer;

public class Service {
    public void startService(String serviceId) throws IOException, InterruptedException {
        runServer(serviceId,readProps(serviceId).getProperty("start"));
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set(serviceId, readProps(serviceId).getProperty("port"));
    }
    public String provideService(String msg) throws IOException, InterruptedException {
        JSONObject obj = JSONObject.parseObject(msg);
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return post2Service(jedis.get(obj.getString("serviceId")),obj.getString("info"));
    }
    public String runService(String msg) throws IOException, InterruptedException {
        String shpath = "/home/ubuntu/userfiles/"+msg+"/"+readProps(msg).getProperty("start");
        Process ps = Runtime.getRuntime().exec(shpath);
        ps.waitFor();
        return "aaa";
    }
}
