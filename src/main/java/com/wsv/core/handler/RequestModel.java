package com.wsv.core.handler;
/*
 * User: Indika Gunawardana
 * Date: 12/5/18
 * Time: 5:23 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import java.util.Enumeration;

public class RequestModel {
    private String serviceId;
    private String serviceMethod;
    private String requestBody;
    private Enumeration<String> serviceParameters;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public Enumeration<String> getServiceParameters() {
        return serviceParameters;
    }

    public void setServiceParameters(Enumeration<String> serviceParameters) {
        this.serviceParameters = serviceParameters;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
