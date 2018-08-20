package org.reflaction;

import org.slave.Report;

public class ReflactService {
    public static String reflact(String serviceName,String args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String pkg = "org.slave."+serviceName;
        System.out.println(pkg);
        Class c = Class.forName(pkg);
        Report r = (Report) c.newInstance();
        System.out.println(r.reportCPU());
        return r.reportCPU();
    }
}
