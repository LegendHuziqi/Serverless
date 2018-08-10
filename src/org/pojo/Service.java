package org.pojo;

import java.util.List;

public class Service {
    private Long serviceId;
    private String serviceName;
    private String serviceUpdater;
    private String serviceDate;
    private List<Node> nodelist;

    public List<Node> getNodelist() {
        return nodelist;
    }

    public void setNodelist(List<Node> nodelist) {
        this.nodelist = nodelist;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceUpdater() {
        return serviceUpdater;
    }

    public void setServiceUpdater(String serviceUpdater) {
        this.serviceUpdater = serviceUpdater;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }


}
