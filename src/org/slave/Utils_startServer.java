package org.slave;

import java.io.*;
import java.util.Properties;

public class Utils_startServer {
    public static Properties readProps(String serviceId) throws IOException {
        FileInputStream fis = new FileInputStream("/home/ubuntu/userfiles/"+serviceId+"/settings.prop");
        Properties p = new Properties();
        p.load(fis);
        return p;
    }
    public static void runServer(String serviceId,String shellpath) throws IOException, InterruptedException {
        String shpath = "/home/ubuntu/userfiles/"+serviceId+"/"+shellpath;
        Process ps = Runtime.getRuntime().exec(shpath);
        ps.waitFor();
    }
}
