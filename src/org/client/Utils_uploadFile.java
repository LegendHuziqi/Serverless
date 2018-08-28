package org.client;

import java.io.FileWriter;
import java.io.IOException;

public class Utils_uploadFile {
        public static void mkshscpdir(String path,String serviceId) {
            try {
                FileWriter fw = new FileWriter("/home/legendhu/IdeaProjects/Serverless/upload.sh", false);
                fw.write("#!/bin/sh \n");
                fw.write("scp -r " + path + " legendhu@127.0.0.1:/home/ubuntu/userfiles/" + serviceId);
                fw.flush();
                fw.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        public static String execShell() throws IOException, InterruptedException {
            String shpath = "/home/legendhu/IdeaProjects/Serverless/upload.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();
            return "aaa";
        }
}
