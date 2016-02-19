package com.huhaoyu.bundleless;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Bundleless Class Manager
 * Created by coderhuhy on 16/2/19.
 */
public class BundlelessManager {

    private static class ManagerHolder {
        static BundlelessManager instance = new BundlelessManager();
    }

    private BundlelessManager() {}

    static BundlelessManager getInstance() {
        return ManagerHolder.instance;
    }

    private Map<String, Class> classStore;
    private Gson gson;
    private JsonSerializer<Date> dateSerializer = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return src != null ? new JsonPrimitive(src.getTime()) : null;
        }
    };
    private JsonDeserializer<Date> dateDeserializer = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json != null ? new Date(json.getAsLong()) : null;
        }
    };

    void init() {
        classStore = new HashMap<>();
        gson = new GsonBuilder().serializeNulls()
                .registerTypeAdapter(Date.class, dateSerializer)
                .registerTypeAdapter(Date.class, dateDeserializer)
                .create();
    }

    String serialize(String key, Object object) {
        Class cls = extractClass(object);
        add(key, cls);
        return gson.toJson(object, cls);
    }

    String serialize(String key, String string) {
        Class cls = extractClass(string);
        add(key, cls);
        return gson.toJson(string, cls);
    }

    String serialize(String key, int i) {
        Class cls = extractClass(i);
        add(key, cls);
        return gson.toJson(i, cls);
    }

    String serialize(String key, Date date) {
        Class cls = extractClass(date);
        add(key, cls);
        return gson.toJson(date, cls);
    }

    String serialize(String key, double d) {
        Class cls = extractClass(d);
        add(key, cls);
        return gson.toJson(d, cls);
    }

    String serialize(String key, float f) {
        Class cls = extractClass(f);
        add(key, cls);
        return gson.toJson(f, cls);
    }

    Object deserialize(String key, String source) {
        Class cls = classStore.remove(key);
        return gson.fromJson(source, cls);
    }

    private Class extractClass(Object o) {
        return o.getClass();
    }

    private void add(String key, Class cls) {
        classStore.put(key, cls);
    }

    private Class get(String key) {
        return classStore.get(key);
    }

    private Class remove(String key) {
        return classStore.remove(key);
    }

}
