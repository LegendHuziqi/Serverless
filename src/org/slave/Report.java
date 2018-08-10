package org.slave;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Report {
    public static String reportCPU() {
        try {
            String shpath = "/home/legendhu/IdeaProjects/Serverless/testCPU.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String result = sb.toString();
            String core=result.substring(6,7);
            String tmp=result.substring(result.length()-7,result.length()-3);
            Float tmp2=Float.parseFloat(tmp)*1000;
            Integer frequency=(int)tmp2.floatValue();
            System.out.println(result);
            System.out.println(core);
            System.out.println(frequency);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static String reportRAM(){
//
//    }
    public static void main(String[] args) {
        Report.reportCPU();
    }
}