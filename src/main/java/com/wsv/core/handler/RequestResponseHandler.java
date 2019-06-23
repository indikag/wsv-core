package com.wsv.core.handler;
/*
 * User: Indika Gunawardana
 * Date: 12/5/18
 * Time: 5:23 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestResponseHandler implements Preparable {
    private HttpServletResponse response;

    @Override public RequestModel prepareRequest(HttpServletRequest request) throws IllegalArgumentException {
        if (request == null) {
            throw new IllegalArgumentException("Http request cannot be null");
        }

        String[] requestURI = request.getRequestURI().split("/");

        // this check will be disabled temporarily. Last two item will be taken
        /*if (requestURI.length != 3) {
            throw new IllegalArgumentException("Request URL contains invalid number of arguments");
        }*/

        String requestBody = null;
        try {
            requestBody = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            throw new IllegalArgumentException("Body data cannot be read");
        }

        RequestModel requestModel = new RequestModel();
        requestModel.setServiceId(requestURI[requestURI.length - 2]);
        requestModel.setServiceMethod(requestURI[requestURI.length - 1]);
        requestModel.setServiceParameters(request.getParameterNames());
        requestModel.setRequestBody(requestBody);

        return requestModel;
    }

    @Override public void sendResponse(JSONObject data, HttpServletResponse response) throws HttpResponseException {
        if (data == null) {
            throw new IllegalArgumentException("Response body cannot be null");
        }
        if (response == null) {
            throw new IllegalArgumentException("Response cannot be null");
        }

        response.setContentType("application/json");
        try {
            response.getWriter().write(data.toString());
        } catch (IOException e) {
            throw new HttpResponseException(HttpStatus.SC_BAD_REQUEST, "Cannot");
        }
    }
}
