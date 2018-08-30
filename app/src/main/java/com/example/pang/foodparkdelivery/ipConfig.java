package com.example.pang.foodparkdelivery;

public class ipConfig {
    public String baseUrl= "http://192.168.1.14/testPJ/";

    public ipConfig() {
    }

    public ipConfig(String url) {
        this.baseUrl = url;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBaseUrlFood() {
        String foodUrl = baseUrl+"FoodDB/";
        return foodUrl;
    }


    public String getBaseUrlRes() {
        return baseUrl+"ResTable/";
    }
}
