package com.wsv.core.handler;
/*
 * User: Indika Gunawardana
 * Date: 12/5/18
 * Time: 5:22 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import org.apache.http.client.HttpResponseException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Preparable {
    RequestModel prepareRequest(HttpServletRequest request) throws IllegalArgumentException;
    void sendResponse(JSONObject data, HttpServletResponse response) throws HttpResponseException;
}
