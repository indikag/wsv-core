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
            // get the service definition for the provided token.
            try {
                jsonString = ServiceDal.getServiceDefinitionByToken(this.requestHandler.getServiceId());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Validate the service for the provided token.
            if (jsonString == null) {
                throw new IllegalArgumentException("There is no service for the provided token, token = " + this.requestHandler.getServiceId());
            }

            // Prepare the service model from the JSON string
            serviceModel = Helper.readJSONString(jsonString);

            // Find the method
            ArrayList<ServiceMethod> serviceMethods = serviceModel.getServiceMethods();
            String serviceMethod = this.requestHandler.getServiceMethod();
            for (ServiceMethod method : serviceMethods) {
                if (method.getMethodName().equalsIgnoreCase(serviceMethod)) {
                    requestedMethod = method;
                    break;
                }
            }

            // Validate the requested method
            if (requestedMethod == null) {
                throw new IllegalArgumentException("Requested method is not available. Requested = " + this.requestHandler.getServiceMethod());
            }

            // Validate the HTTP method
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
