package org.master;

import java.io.*;

public class Utils_findService {
    public static String execShell() throws IOException, InterruptedException {
        String shpath = "/home/legendhu/IdeaProjects/Serverless/scpdir.sh";
        Process ps = Runtime.getRuntime().exec(shpath);
        ps.waitFor();
        return "aaa";
    }

    public static void mkshscpdir(String destIP,String serviceId){
        try{
            FileWriter fw = new FileWriter("/home/legendhu/IdeaProjects/Serverless/scpdir.sh",false);
            fw.write("#!/bin/sh \n");
            fw.write("scp -r /home/legendhu/userfiles/");
            fw.write(serviceId+" "+"ubuntu@");
            fw.write(destIP+":/home/ubuntu/userfiles/"+serviceId);
            fw.flush();
            fw.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        mkshscpdir("192.168.56.3","8efbb44d54324279abbaab88b87c52bc");
        execShell();
    }
}
