package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/7/18
 * Time: 9:26 PM
 * Copyright(c) 2018 AXIS, LLC.
 */

import java.util.ArrayList;

public class ServiceMethodModel {
    private String methodName;
    private ArrayList<MethodParameterModel> methodParameterModel;
    private MethodResponseModel methodResponseModel;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public ArrayList<MethodParameterModel> getMethodParameterModel() {
        return methodParameterModel;
    }

    public void setMethodParameterModel(ArrayList<MethodParameterModel> methodParameterModel) {
        this.methodParameterModel = methodParameterModel;
    }

    public MethodResponseModel getMethodResponseModel() {
        return methodResponseModel;
    }

    public void setMethodResponseModel(MethodResponseModel methodResponseModel) {
        this.methodResponseModel = methodResponseModel;
    }

    @Override public String toString() {
        return "ServiceMethodModel{" + "methodName='" + methodName + '\'' + ", methodParameterModel="
                + methodParameterModel + ", methodResponseModel=" + methodResponseModel + '}';
    }
}
