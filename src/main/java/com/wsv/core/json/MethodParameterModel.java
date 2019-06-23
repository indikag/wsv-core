package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/7/18
 * Time: 9:27 PM
 * Copyright(c) 2018 AXIS, LLC.
 */

public class MethodParameterModel {
    private String parameterName;
    private String parameterValue;
    private String parameterType;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    @Override public String toString() {
        return "MethodParameterModel{" + "parameterName='" + parameterName + '\'' + ", parameterValue='"
                + parameterValue + '\'' + ", parameterType='" + parameterType + '\'' + '}';
    }
}
