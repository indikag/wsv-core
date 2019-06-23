package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/14/18
 * Time: 5:30 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import com.google.gson.*;

import java.lang.reflect.Type;

public class ResponseDeserilizer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        //Possible types are ResponseFormatModel, MethodResponseModel
        //parent models is MethodResponseModel
        MethodResponseModel model = new Gson().fromJson(jsonElement, MethodResponseModel.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.has("minSize")) {
            JsonObject object = jsonObject.getAsJsonObject("");
        } else {

        }

        return null;
    }
}
