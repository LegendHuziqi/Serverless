package org.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.pojo.Node;

import java.util.ArrayList;
import java.util.List;

public class frontendJson {
    String serviceName;
    List<Node> node;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<Node> getNode() {
        return node;
    }

    public void setNode(List<Node> node) {
        this.node = node;
    }

    public static frontendJson init(){
        frontendJson f=new frontendJson();
        f.serviceName="TestService";
        Node a=new Node();
        Node b=new Node();
        a.setNodeId(135862142313L);
        //天津
        a.setOuterIP("211.94.245.249");
        b.setNodeId(135915190723L);
        //宁波
        b.setOuterIP("115.220.168.1");
        f.node=new ArrayList();
        f.node.add(a);
        f.node.add(b);
        return f;
    }

    public static void main(String[] args) {

        frontendJson f=new frontendJson();
        f=frontendJson.init();
        System.out.println(f.serviceName);
        System.out.println(f.node.get(1).getOuterIP());
        ArrayList<frontendJson> list=new ArrayList();
        list.add(f);
        String result2=JSONArray.toJSONString(list);
        System.out.println(result2);
    }
}
