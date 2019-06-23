package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/7/18
 * Time: 9:28 PM
 * Copyright(c) 2018 AXIS, LLC.
 */

import java.util.ArrayList;

public class MethodResponseModel {
    private Integer minSize;
    private Integer maxSize;
    private String name;
    private ArrayList<ResponseFormatModel> format;

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ResponseFormatModel> getFormat() {
        return format;
    }

    public void setFormat(ArrayList<ResponseFormatModel> format) {
        this.format = format;
    }

    @Override public String toString() {
        return "MethodResponseModel{" + "minSize=" + minSize + ", maxSize=" + maxSize + ", name='" + name + '\''
                + ", format=" + format + '}';
    }
}
