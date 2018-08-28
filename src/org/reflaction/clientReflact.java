package org.reflaction;

import org.client.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class clientReflact {
    public static String reflact(String serviceName,String args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class c = Class.forName("org.client.Service");
        Constructor constructor = c.getConstructor();
        Service service = (Service) constructor.newInstance();
        Method[] method = c.getMethods();
        Map<String,Method> map1=new HashMap();
//        Map<String,Object> map2=new HashMap();
        for(Method tempmethod:method){
            map1.put(tempmethod.getName(),tempmethod);
//            map2.put(tempmethod.getName(),service);
        }
        Method m2 = map1.get(serviceName);
        String str=(String) m2.invoke(service,args);
        return str;
    }
}