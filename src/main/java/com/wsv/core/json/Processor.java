package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/7/18
 * Time: 5:32 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import com.wsv.core.Util;
import com.wsv.core.data.DataGenerator;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Processor {
    private ServiceModel serviceModel;

    public Processor(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }

    public JSONObject getResponse(String methodName) throws FileNotFoundException {
        if (methodName == null || methodName.isEmpty()) {
            throw new IllegalArgumentException("Path tot he json file is not valid.");
        }

        JSONObject out = null;

        ArrayList<ServiceMethod> serviceMethods = this.serviceModel.getServiceMethods();
        for (ServiceMethod method : serviceMethods) {
            if (method.getMethodName().equalsIgnoreCase(methodName)) {
                out = generateData(method);
            }
        }

        if (out == null) {
            System.out.println("There is no such method " + methodName);
        }

        return out;
    }

    /**
     * Generate fake data for the method.
     * @param method method model
     * @return JSON object with fake data
     */
    private JSONObject generateData(ServiceMethod method) {
        //validate http method
        if (!Util.HttpMethod.validValue(method.getType())) {
            return JsonUtil.getInvalidMethodJSON();
        }

        ArrayList<ResponseFormatModel> format = method.getResponse().getFormat();
        JSONObject out = new JSONObject();

        //for models max size will be 1, for list it can be  any number which is >1
        int minSize = method.getResponse().getMinSize() == null? 1 : method.getResponse().getMinSize();
        int maxSize = method.getResponse().getMaxSize() == null? 1 : method.getResponse().getMaxSize();
        //creation of the list
        for (int a = minSize; a <= maxSize; a++) {
            //creation of a single item
            JSONObject item = DataGenerator.generate(format);
            out.append(method.getResponse().getName(), item);
        }

        System.out.println("==========");
        System.out.println(out.toString());

        return out;
    }
}
