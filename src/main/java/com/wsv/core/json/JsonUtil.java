package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/13/18
 * Time: 6:59 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import org.json.JSONObject;

public class JsonUtil {

    public static JSONObject getInvalidMethodJSON() {
        return new JSONObject("{\n" + "  \"status\":\"error\",\n" + "  \"description\":\"Unsupported Http type\",\n"
                + "  \"code\": 123\n" + "}");
    }
}
