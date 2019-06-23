package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/8/18
 * Time: 2:51 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

public class ResponseFormatModel {
    private String name;
    private String type;
    //For simple variables
    private SimpleDataFormatModel simpleDataFormat;
    //For complex objects
    private MethodResponseModel complexDataFormat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SimpleDataFormatModel getSimpleDataFormat() {
        return simpleDataFormat;
    }

    public void setSimpleDataFormat(SimpleDataFormatModel simpleDataFormat) {
        this.simpleDataFormat = simpleDataFormat;
    }

    public MethodResponseModel getComplexDataFormat() {
        return complexDataFormat;
    }

    public void setComplexDataFormat(MethodResponseModel complexDataFormat) {
        this.complexDataFormat = complexDataFormat;
    }

    @Override
    public String toString() {
        return "ResponseFormatModel{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", simpleDataFormat=" + simpleDataFormat +
                ", complexDataFormat=" + complexDataFormat +
                '}';
    }
}
