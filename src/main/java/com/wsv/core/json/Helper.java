package com.wsv.core.json;
/*
 * User: Indika Gunawardana
 * Date: 12/7/18
 * Time: 5:33 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

/**
 * Utility class to keep supporting methods
 */
public class Helper {
    static Gson gson = new Gson();

    /**
     * Read the json file and load into a java object.
     *
     * @param file path to the JSON file
     * @return ServiceModel
     * @throws FileNotFoundException when the given path is incorrect
     */
    public static ServiceModel readJSONFile(String file) throws FileNotFoundException {
        ServiceModel serviceModel = null;
        try {
            JsonReader reader = gson.newJsonReader(new FileReader(file));
            Type REVIEW_TYPE = new TypeToken<ServiceModel>() {
            }.getType();
            serviceModel = new Gson().fromJson(reader, REVIEW_TYPE);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Cannot find the json file");
        }

        return serviceModel;
    }

    /**
     * Convert json String to ServiceModel object
     * @param jsonString json string
     * @return Service model
     */
    public static ServiceModel readJSONString(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            throw new IllegalArgumentException("Json string cannot be null or empty");
        }

        ServiceModel serviceModel = null;
        try {
            Type REVIEW_TYPE = new TypeToken<ServiceModel>() {
            }.getType();
            serviceModel = new Gson().fromJson(jsonString, REVIEW_TYPE);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return serviceModel;
    }
}
