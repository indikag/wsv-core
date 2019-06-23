package com.wsv.core;
/*
 * User: Indika Gunawardana
 * Date: 12/13/18
 * Time: 4:23 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

public class Util {
    /**
     * Data format of the json file
     */
    public enum DataFormat {
        NAME("name"), ADDRESS("address"), DATE("date"), PHONE_NUMBER("phoneNumber"), COUNTRY("country"), COMPANY(
                "company"), BOOLEAN("yes_no"), AGE("age"), PARAGRAPH("paragraph");

        private final String format;

        DataFormat(String format) {
            this.format = format;
        }

        public static DataFormat parseEnum(String value) {
            DataFormat df = null;
            switch (value) {
                case "name":
                    df = NAME;
                    break;
                case "address":
                    df = ADDRESS;
                    break;
                case "date":
                    df = DATE;
                    break;
                case "phoneNumber":
                    df = PHONE_NUMBER;
                    break;
                case "country":
                    df = COUNTRY;
                    break;
                case "company":
                    df = COMPANY;
                    break;
                case "yes_no":
                    df = BOOLEAN;
                    break;
                case "age":
                    df = AGE;
                    break;
                case "paragraph":
                    df = PARAGRAPH;
                    break;
                default:
                    return null;
            }

            return df;
        }

        public String getValue() {
            return this.format;
        }
    }

    /**
     * Response type can be a list or a model
     */
    public enum ResponseType {
        MODEL("model"), LIST("list");

        private final String type;

        ResponseType(String type) {
            this.type = type;
        }

        public static ResponseType parseEnum(String value) {
            ResponseType df = null;
            switch (value) {
                case "model":
                    df = MODEL;
                    break;
                case "list":
                    df = LIST;
                    break;
                default:
                    return null;
            }

            return df;
        }

        public String getValue() {
            return this.type;
        }
    }

    /**
     * Response items count can be a fixed or variable size.
     */
    public enum ResponseVariation {
        FIXED("fixed"), VARIABLE("variable");

        private final String type;

        ResponseVariation(String type) {
            this.type = type;
        }

        public static ResponseVariation parseEnum(String value) {
            ResponseVariation df = null;
            switch (value) {
                case "fixed":
                    df = FIXED;
                    break;
                case "variable":
                    df = VARIABLE;
                    break;
                default:
                    return null;
            }

            return df;
        }

        public String getValue() {
            return this.type;
        }
    }

    /**
     * Define the specific status of the data format.
     * "sampleDataFormat": {
     * "type": "name",
     * "specific": "no",
     * "defaultValue": "Indika Gunawardana"
     * }
     */
    public enum DefaultValueStatus {
        YES("yes"), NO("no");

        private final String type;

        DefaultValueStatus(String type) {
            this.type = type;
        }

        public static DefaultValueStatus parseEnum(String value) {
            DefaultValueStatus df = null;
            switch (value) {
                case "yes":
                    df = YES;
                    break;
                case "no":
                    df = NO;
                    break;
                default:
                    return null;
            }

            return df;
        }

        public String getValue() {
            return this.type;
        }
    }

    /**
     * Supporting methods
     */
    public enum HttpMethod {
        GET("get"), POST("post"), PUT("put"), DELETE("delete"), HEAD("head"), CONNECT("connect"), OPTIONS(
                "options"), TRACE("trace");

        private final String type;

        HttpMethod(String type) {
            this.type = type;
        }

        public static HttpMethod parseEnum(String value) {
            HttpMethod df = null;
            switch (value) {
                case "get":
                    df = GET;
                    break;
                case "post":
                    df = POST;
                    break;
                case "put":
                    df = PUT;
                    break;
                case "delete":
                    df = DELETE;
                    break;
                case "head":
                    df = HEAD;
                    break;
                case "connect":
                    df = CONNECT;
                    break;
                case "options":
                    df = OPTIONS;
                    break;
                case "trace":
                    df = TRACE;
                    break;
                default:
                    return null;
            }

            return df;
        }

        public String getValue() {
            return this.type;
        }

        public static boolean validValue(String value) {
            boolean valid = false;
            for (HttpMethod method : HttpMethod.values()) {
                if (method.getValue().equalsIgnoreCase(value)) {
                    valid = true;
                    break;
                }
            }

            return valid;
        }
    }
}
