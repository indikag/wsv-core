package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/7/18
 * Time: 5:32 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import java.util.ArrayList;

/**
 * Common format of the service
 */
public class ServiceModel {
    private String serviceName;
    private String serviceDescription;
    private ArrayList<ServiceMethod > serviceMethods;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public ArrayList<ServiceMethod> getServiceMethods() {
        return serviceMethods;
    }

    public void setServiceMethods(ArrayList<ServiceMethod> serviceMethods) {
        this.serviceMethods = serviceMethods;
    }

    @Override public String toString() {
        return "ServiceModel{" + "serviceName='" + serviceName + '\'' + ", serviceDescription='" + serviceDescription
                + '\'' + ", serviceMethods=" + serviceMethods + '}';
    }
}
