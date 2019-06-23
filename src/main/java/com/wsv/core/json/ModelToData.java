package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 1/28/19
 * Time: 12:58 PM
 * Copyright(c) 2018 AXIS, LLC.
 */


import org.json.JSONObject;

import java.util.ArrayList;

public class ModelToData {
    private ServiceModel serviceModel;
    private JSONObject out;
    private ServiceMethod serviceMethod;

    public ModelToData(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
        this.out = new JSONObject();
    }

    public JSONObject getData() {
        return this.out;
    }

    public void prepare(String methodName) {
        //
        ArrayList<ServiceMethod> serviceMethods = serviceModel.getServiceMethods();
        for (ServiceMethod method :
                serviceMethods) {
            if (method.getMethodName().equalsIgnoreCase(methodName)) {
                this.serviceMethod = method;
                break;
            }
        }

        if (this.serviceMethod != null) {
            generateData();
        } else {
            System.out.println("Method not found");
        }
    }

    private void generateData() {
        //service method is the input to this method.
        MethodResponseModel response = this.serviceMethod.getResponse();

        //TODO do validation part of the request with the format such as HTTP method matching etc.

        //Start data generation for the method
        int min = response.getMinSize();
        int max = response.getMaxSize();
        String name = response.getName();
        ArrayList<ResponseFormatModel> formats = response.getFormat();
        out = handleComplexData(min, max, name, formats);
    }

    private JSONObject handleComplexData(int min, int max, String name, ArrayList<ResponseFormatModel> formats) {
        JSONObject out = new JSONObject();

        //goes to the recursion
        boolean isArray = max - min != 1;
        if (isArray) {
            for(int a = 0; a < max - min; a++) {
                JSONObject data = new JSONObject();
                for (int b = 0; b < formats.size(); b++) {
                    ResponseFormatModel model = formats.get(b);
                    if (model.getSimpleDataFormat() != null) {
                        data.put(model.getName(), model.getType());
                    } else {
                        //data.put(model.getName(), "complex");
                        //we need to call this method again
                        MethodResponseModel complexDataFormat = model.getComplexDataFormat();
                        int min_ = complexDataFormat.getMinSize();
                        int max_ = complexDataFormat.getMaxSize();
                        String name_ = complexDataFormat.getName();
                        ArrayList<ResponseFormatModel> formats_ = complexDataFormat.getFormat();
                        JSONObject object = handleComplexData(min_, max_, name_, formats_);
                        data.put(model.getName(), object);
                    }
                }
                out.append(name, data);
            }
        } else {
            JSONObject data = new JSONObject();
            for (int b = 0; b < formats.size(); b++) {
                data.put(formats.get(b).getName(), 12);
            }

            out.put(name, data);
        }


        return out;
    }

}
