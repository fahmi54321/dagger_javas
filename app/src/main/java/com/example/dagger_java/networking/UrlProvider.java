package com.example.dagger_java.networking;

import com.example.dagger_java.Constants;

public class UrlProvider {
    public String baseUrl1(){
        return Constants.BASE_URL;
    }

    public String baseUrl2(){
        return "base_url_2";
    }
}
