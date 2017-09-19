package com.github.sejoung.api.util.json;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component("jsonUtil")
public class JsonUtil {
    private Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * Vo Object 직렬화
     * 
     * @param obj
     * @return
     */
    public String generateJson(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     * requestBody를 객체로 변환한다.
     * 
     * @param body
     * @return
     */
    public Object parseRequestJson(String body, Class<?> classzz) {
        return GSON.fromJson(body, classzz);
    }

    /**
     * requestBody를 List 객체로 변환한다
     * @param json
     * @param clazz
     * @return
     */
    public <T> List<T> getList(String json,Class<T[]> clazz) {
        T[] jsonToObject = GSON.fromJson(json, clazz);
        return Arrays.asList(jsonToObject);
    }
}
