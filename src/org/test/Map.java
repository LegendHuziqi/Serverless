
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Map {
    public static void main(String[] args) throws IOException{
        List<String> l = new ArrayList<>();
        l.add("aa");
        l.add("bb");
        List l1=new ArrayList();
        l1=(List) l;
//        Map <String,String> map= new HashMap<Object, Object>();
//        map.put("1","bb");
//        map.put("2","cc");
//        System.out.println(map,get("1"));
        FileInputStream fis = new FileInputStream("/home/lxx/untitled/src/xxx");
//        get.a
        Properties p = new Properties();
        p.load(fis);
        Scanner sc =new Scanner(System.in);
        String str =sc.next();
        System.out.println(p.getProperty(str));
        HashMap m=new HashMap();
        m.put(sc,p.getProperty(str));
        System.out.println(m.get(sc));

    }
}