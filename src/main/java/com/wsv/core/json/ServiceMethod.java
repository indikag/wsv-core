package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/8/18
 * Time: 3:50 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import java.util.ArrayList;

public class ServiceMethod {
    private String methodName;
    private String type;
    private ArrayList<MethodParameterModel> parameters;
    private MethodResponseModel response;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<MethodParameterModel> getParameters() {
        return parameters;
    }

    public ArrayList<String> getParameterNames() {
        ArrayList<String> items = new ArrayList<>();
        if (parameters == null) {
            return items;
        }
        for (MethodParameterModel model :
                parameters) {
            items.add(model.getParameterName());
        }
        return items;
    }

    public void setParameters(ArrayList<MethodParameterModel> parameters) {
        this.parameters = parameters;
    }

    public MethodResponseModel getResponse() {
        return response;
    }

    public void setResponse(MethodResponseModel response) {
        this.response = response;
    }

    @Override public String toString() {
        return "ServiceMethod{" + "methodName='" + methodName + '\'' + ", type='" + type + '\'' + ", parameters="
                + parameters + ", response=" + response + '}';
    }
}
