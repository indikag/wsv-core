package com.wsv.core;
/*
 * User: Indika Gunawardana
 * Date: 12/4/18
 * Time: 3:50 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import com.github.javafaker.Faker;
import com.wsv.core.json.Helper;
import com.wsv.core.json.ModelToData;
import com.wsv.core.json.ServiceModel;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Locale;

public class Test {

    public static void main(String[] args) throws ParseException {
        //SecureRandom random = new SecureRandom();
        //long longToken = Math.abs( random.nextLong() );
        //String random2 = Long.toString( longToken, 16 );
        //System.out.println(random2);

        // run();
        // run2();

        /*try {
            String a = ServiceDal.getServiceDefinitionByToken("11c46f238c5fe26a");
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        Faker faker = new Faker( new Locale("en-US"));

        /*String a = "/asdsa/getWelcomeMessage";
        String[] split = a.split("/");
        System.out.println(split);*/
    }

    static void run2() {
        JSONObject object = new JSONObject();
        object.put("a", "123");
        object.put("b", "445");

        JSONObject out = new JSONObject();
        out.append("out", object);

        System.out.println(out.toString());
    }

    static void run() {
        try {
            //ServiceModel jsonFile = Helper.readJSONFile(
            //        "/Users/indikagunawardana/Personal/MCS/Final Project/Solution/Project/wsv/json/service2.json");
            ServiceModel jsonFile = Helper.readJSONFile(
                    "/Users/indikagunawardana/Personal/MCS/Final Project/Solution/Project/wsv/json/sample3.json");


            //Values has been read correctly up to this level.
            //Now check the data generation part for this.

            //TODO old data
            //Processor processor = new Processor(jsonFile);
            //JSONObject out = processor.getResponse("getUserInfo");
            //System.out.println("Final=" + out.toString());

            //TODO new way
            ModelToData data = new ModelToData(jsonFile);
            data.prepare("getUserInfo");
            JSONObject response = data.getData();
            System.out.println(response.toString());

        } catch (FileNotFoundException e) {
            System.out.println("json file error");
            e.printStackTrace();
        }
    }
}

