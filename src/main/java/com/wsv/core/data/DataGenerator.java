package com.wsv.core.data;
/*
 * User: Indika Gunawardana
 * Date: 12/6/18
 * Time: 4:06 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import com.github.javafaker.Number;
import com.github.javafaker.*;
import com.wsv.core.Util;
import com.wsv.core.json.MethodResponseModel;
import com.wsv.core.json.ResponseFormatModel;
import com.wsv.core.json.SimpleDataFormatModel;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Generate random data
 */
public class DataGenerator {
    //Faker object to generate some strings
    private static Faker faker = new Faker(new Locale("en-US"));

    /**
     * Generate simple data based on SampleDataFormat model
     *
     * @param format format list of the data to be generated
     * @return JSON object of the generated data
     */
    public static JSONObject generate(ArrayList<ResponseFormatModel> format) {
        JSONObject dataJson = new JSONObject();
        for (ResponseFormatModel formatModel : format) {
            SimpleDataFormatModel simpleDataFormat = formatModel.getSimpleDataFormat();
            MethodResponseModel complexDataFormat = formatModel.getComplexDataFormat();

            if (simpleDataFormat != null && complexDataFormat == null) {
                //Found simple data
                if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.NAME.getValue())) {
                    if (Util.DefaultValueStatus.parseEnum(simpleDataFormat.getSpecific()) == Util.DefaultValueStatus.YES) {
                        dataJson.put(simpleDataFormat.getType(), simpleDataFormat.getDefaultValue());
                    } else {
                        dataJson.put(simpleDataFormat.getType(), DataGenerator.getName().name());
                    }
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.ADDRESS.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getAddress().fullAddress());
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.DATE.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getDateAndTime().birthday());
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.PHONE_NUMBER.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getPhoneNumber().phoneNumber());
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.COUNTRY.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getChuckNorris().fact());
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.COMPANY.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getCompany().name());
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.BOOLEAN.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getBool().bool());
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.AGE.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getAge());
                } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.PARAGRAPH.getValue())) {
                    dataJson.put(simpleDataFormat.getType(), DataGenerator.getArbitraryParagraph());
                } else {
                    throw new IllegalArgumentException("Data type is not supporting " + simpleDataFormat.getType());
                }
            }

            if (complexDataFormat != null && simpleDataFormat == null) {
                //Found a complex data
                System.out.println("complex data model");
            }
        }

        return dataJson;
    }

    public static JSONObject generate(SimpleDataFormatModel simpleDataFormat) {
        if (simpleDataFormat == null) {
            throw new IllegalArgumentException("SimpleDataFormatModel cannot be null");
        }

        JSONObject dataJson = new JSONObject();
        if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.NAME.getValue())) {
            if (Util.DefaultValueStatus.parseEnum(simpleDataFormat.getSpecific()) == Util.DefaultValueStatus.YES) {
                dataJson.put(simpleDataFormat.getType(), simpleDataFormat.getDefaultValue());
            } else {
                dataJson.put(simpleDataFormat.getType(), DataGenerator.getName().name());
            }
        } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.ADDRESS.getValue())) {
            if (Util.DefaultValueStatus.parseEnum(simpleDataFormat.getSpecific()) == Util.DefaultValueStatus.YES) {
                dataJson.put(simpleDataFormat.getType(), simpleDataFormat.getDefaultValue());
            } else {
                dataJson.put(simpleDataFormat.getType(), DataGenerator.getAddress().fullAddress());
            }
        } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.DATE.getValue())) {
            if (Util.DefaultValueStatus.parseEnum(simpleDataFormat.getSpecific()) == Util.DefaultValueStatus.YES) {
                dataJson.put(simpleDataFormat.getType(), simpleDataFormat.getDefaultValue());
            } else {
                dataJson.put(simpleDataFormat.getType(), DataGenerator.getDateAndTime().birthday());
            }
        } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.PHONE_NUMBER.getValue())) {
            if (Util.DefaultValueStatus.parseEnum(simpleDataFormat.getSpecific()) == Util.DefaultValueStatus.YES) {
                dataJson.put(simpleDataFormat.getType(), simpleDataFormat.getDefaultValue());
            } else {
                dataJson.put(simpleDataFormat.getType(), DataGenerator.getPhoneNumber().phoneNumber());
            }
        } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.COUNTRY.getValue())) {
            if (Util.DefaultValueStatus.parseEnum(simpleDataFormat.getSpecific()) == Util.DefaultValueStatus.YES) {
                dataJson.put(simpleDataFormat.getType(), simpleDataFormat.getDefaultValue());
            } else {
                dataJson.put(simpleDataFormat.getType(), DataGenerator.getChuckNorris().fact());
            }
        } else if (simpleDataFormat.getType().equalsIgnoreCase(Util.DataFormat.COMPANY.getValue())) {
            if (Util.DefaultValueStatus.parseEnum(simpleDataFormat.getSpecific()) == Util.DefaultValueStatus.YES) {
                dataJson.put(simpleDataFormat.getType(), simpleDataFormat.getDefaultValue());
            } else {
                dataJson.put(simpleDataFormat.getType(), DataGenerator.getCompany().name());
            }
        } else {
            throw new IllegalArgumentException("Data type is not supporting " + simpleDataFormat.getType());
        }

        return dataJson;
    }


    private static Address getAddress() {
        return faker.address();
    }

    private static App getApp() {
        return faker.app();
    }

    private static Beer getBeer() {
        return faker.beer();
    }

    private static Book getBook() {
        return faker.book();
    }

    private static Bool getBool() {
        return faker.bool();
    }

    private static Business getBusiness() {
        return faker.business();
    }

    private static ChuckNorris getChuckNorris() {
        return faker.chuckNorris();
    }

    private static Code getCode() {
        return faker.code();
    }

    private static Color getColor() {
        return faker.color();
    }

    private static Commerce getCommerce() {
        return faker.commerce();
    }

    private static Company getCompany() {
        return faker.company();
    }

    private static Crypto getCrypto() {
        return faker.crypto();
    }

    private static DateAndTime getDateAndTime() {
        return faker.date();
    }

    private static Educator getEducator() {
        return faker.educator();
    }

    private static Finance getFinance() {
        return faker.finance();
    }

    private static Hacker getHacker() {
        return faker.hacker();
    }

    private static IdNumber getIdNumber() {
        return faker.idNumber();
    }

    private static Internet getInternet() {
        return faker.internet();
    }

    private static Lorem getLorem() {
        return faker.lorem();
    }

    private static Name getName() {
        return faker.name();
    }

    private static Number getNumber() {
        return faker.number();
    }

    private static Options getOptions() {
        return faker.options();
    }

    private static PhoneNumber getPhoneNumber() {
        return faker.phoneNumber();
    }

    private static Shakespeare getShakespeare() {
        return faker.shakespeare();
    }

    private static Superhero getSuperhero() {
        return faker.superhero();
    }

    private static Team getTeam() {
        return faker.team();
    }

    private static University getUniversity() {
        return faker.university();
    }

    private static Integer getAge() {
        return faker.number().numberBetween(0, 80);
    }

    private static String getArbitraryParagraph() {
        return faker.lorem().paragraph();
    }

    private static String getParagraph(int sentenceCount) {
        if (sentenceCount <= 0) {
            throw new IllegalArgumentException("Sentence count cannot be zero or negative");
        }
        return faker.lorem().paragraph(sentenceCount);
    }

    private static String getParagraphs(int paragraphCount) {
        if (paragraphCount <= 0) {
            throw new IllegalArgumentException("Paragraph count cannot be zero or negative");
        }
        List<String> paragraphs = faker.lorem().paragraphs(paragraphCount);
        if (paragraphCount == 1) {
            return paragraphs.get(0);
        } else {
            StringBuilder out = new StringBuilder();
            for (String item :
                    paragraphs) {
                out.append(item);
                out.append("\n");
            }
            return out.toString();
        }
    }

    private static String getFixedString(int numberOfLetters) {
        return faker.lorem().fixedString(numberOfLetters);
    }
}
