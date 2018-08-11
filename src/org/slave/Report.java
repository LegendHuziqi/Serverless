package org.slave;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.hyperic.sigar.*;
import com.alibaba.fastjson.*;

import static com.alibaba.fastjson.JSON.toJSONString;

public class Report {
    public static String reportCPU() {
        class CPU{
            Integer core;
            Integer frequency;
        }
        try {
            CPU cpu=new CPU();
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
            cpu.core=Integer.parseInt(result.substring(6,7));
            String tmp=result.substring(result.length()-7,result.length()-3);
            Float tmp2=Float.parseFloat(tmp)*1000;
            cpu.frequency=(int)tmp2.floatValue();
            result=toJSONString(cpu);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String reportRAM() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        System.out.println(mem.getTotal());
        return null;
    }

//    public static String reportRAM(){
//
//    }
    public static void main(String[] args) {
        Report.reportCPU();
    }
}