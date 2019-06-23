package com.wsv.core.test;
/*
 * User: Indika Gunawardana
 * Date: 12/14/18
 * Time: 9:41 AM
 * Copyright(c) 2018 AXIS, LLC.
 */

import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Deserialize {
    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void main(String[] args) throws IOException {
        //String json = "{\"id\":1,\"list\":[{\"item1\":{\"regNo\":33333333}},{\"item2\":{\"down\":false}}]}";
        //System.out.println(json);
        String path = "/Users/indikagunawardana/Personal/MCS/Final Project" + "/Solution/Project/wsv/json/sample3.json";
        String json = readFile(path, StandardCharsets.UTF_8);
        //System.out.println(json);


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Top.class, new JsonDeserializer<Top>() {
            @Override
            public Top deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                //System.out.println(jsonObject.toString());
                JsonElement type = jsonObject.get("serviceMethods");
                ArrayList list = new ArrayList();
                if (type != null) {
                    if (type.isJsonArray()) {
                        JsonArray array = type.getAsJsonArray();
                        for (JsonElement element : array) {
                            if (element.getAsJsonObject().get("item1") != null) {
                                //System.out.println("Item object contains " + element.toString());
                                Item1 item1 = new Gson().fromJson(element.getAsJsonObject().get("item1"), Item1.class);
                                System.out.println(item1.toString());
                                list.add(item1);
                            }

                            if (element.getAsJsonObject().get("item2") != null) {
                                //System.out.println("Item2 object contains " + element.toString());
                                Item2 item2 = new Gson().fromJson(element.getAsJsonObject().get("item2"), Item2.class);
                                System.out.println(item2.toString());
                                list.add(item2);
                            }
                        }
                    } else {
                        System.out.println("Type is not a json array");
                    }
                } else {
                    System.out.println("Cannot find \"format\" tag");
                }

                //for (Object item: list) {
                //    System.out.println("HERE : " + item.toString());
                //}

                //Top top = context.deserialize(jsonObject, Top.class);

                //Take the rest of the json by default
                Gson g = new Gson();
                Top t = g.fromJson(json, Top.class);

                t.setList(list);

                return t;
            }

        });

        //now we are getting the null error since above method returns null, pls fix that

        Gson data = builder.create();
        Top top = data.fromJson(json, Top.class);
        //System.out.println("");
        //System.out.println(top.toString());

        for (Object item : top.getList()) {
            if (item instanceof Item1) {
                int regNo = ((Item1) item).getRegNo();
                System.out.println(regNo);
            }

            if (item instanceof Item2) {
                boolean is = ((Item2) item).isDown();
                System.out.println(is);
            }
        }
    }
}


class Top {
    private int id;
    private ArrayList<?> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Top{" + "id=" + id + ", list=" + list + '}';
    }
}

class Item {
    @Override
    public String toString() {
        return "Item{}";
    }
}

class Item1 extends Item {
    private int regNo;

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    @Override
    public String toString() {
        return "Item1{" +
                "regNo=" + regNo +
                "} " + super.toString();
    }
}

class Item2 extends Item {
    private boolean down;

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    @Override
    public String toString() {
        return "Item2{" + "down=" + down + "} ";
    }
}
