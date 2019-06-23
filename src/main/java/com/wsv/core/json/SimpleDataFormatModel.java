package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/8/18
 * Time: 2:47 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

public class SimpleDataFormatModel {
    private String type;
    private String specific;
    private String defaultValue;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override public String toString() {
        return "SimpleDataFormatModel{" + "type='" + type + '\'' + ", specific='" + specific + '\'' + ", defaultValue='"
                + defaultValue + '\'' + '}';
    }
}
