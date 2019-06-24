package com.wsv.core.action;
/*
 * User: Indika Gunawardana
 * Date: 12/4/18
 * Time: 3:50 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.wsv.core.dal.ServiceDal;
import com.wsv.core.handler.RequestModel;
import com.wsv.core.handler.RequestResponseHandler;
import com.wsv.core.json.Helper;
import com.wsv.core.json.Processor;
import com.wsv.core.json.ServiceMethod;
import com.wsv.core.json.ServiceModel;
import org.apache.http.client.HttpResponseException;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestModel requestHandler;

    public String method;
    public String service;

    private ServiceModel serviceModel = null;
    private ServiceMethod requestedMethod = null;

    @Override public String execute() {
        try {
            String jsonString = null;
            try {
                jsonString = ServiceDal.getServiceDefinitionByToken(this.requestHandler.getServiceId());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (jsonString == null) {
                System.out.println("There is no service by the given token");
                return null;
            }



            // validate the service.
            //String serviceToken = request.getServletPath().split("/")[1];
            //String methodName = request.getServletPath().split("/")[2];
            //System.out.println(serviceToken);
            //System.out.println(methodName);



            /*ServiceModel service1 = Helper.readJSONFile(
                    "/Users/indikagunawardana/Personal/MCS/Final Project/Solution/Project/wsv/json/sample.json");
            ServiceModel service2 = Helper.readJSONFile(
                    "/Users/indikagunawardana/Personal/MCS/Final Project/Solution/Project/wsv/json/service2.json");*/
            /*switch (requestHandler.getServiceId()) {
                case "serv1":
                    serviceModel = Helper.readJSONFile(
                            "/Users/indikagunawardana/Personal/MCS/Final Project/Solution/Project/wsv/json/sample.json");
                    break;
                case "serv2":
                    serviceModel = Helper.readJSONFile(
                            "/Users/indikagunawardana/Personal/MCS/Final Project/Solution/Project/wsv/json/service2.json");
                    break;
                case "serv3":
                    serviceModel = Helper.readJSONFile(
                            "/Users/indikagunawardana/Personal/MCS/Final Project/Solution/Project/wsv/json/sample3.json");
                    break;
            }*/

            serviceModel = Helper.readJSONString(jsonString);

            ArrayList<ServiceMethod> serviceMethods = serviceModel.getServiceMethods();
            String serviceMethod = this.requestHandler.getServiceMethod();
            for (ServiceMethod method : serviceMethods) {
                if (method.getMethodName().equalsIgnoreCase(serviceMethod)) {
                    requestedMethod = method;
                    break;
                }
            }

            if (!requestedMethod.getType().equalsIgnoreCase(this.requestHandler.getHttpMethod())) {
                throw new UnsupportedOperationException("Requested http method is not supported. requested = "
                        + requestedMethod.getType() + " provided = " + this.requestHandler.getHttpMethod());
            }

            //generate data for the service
            Processor processor = new Processor(this.serviceModel);
            JSONObject out = processor.getResponse(requestedMethod.getMethodName());
            new RequestResponseHandler().sendResponse(out, response);
        } catch (FileNotFoundException e) {
            //cannot find the json file
            e.printStackTrace();
        } catch (HttpResponseException e) {
            //cannot send data
            e.printStackTrace();
        }

        return null;
    }


    @Override public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
        this.requestHandler = new RequestResponseHandler().prepareRequest(httpServletRequest);
    }

    @Override public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
