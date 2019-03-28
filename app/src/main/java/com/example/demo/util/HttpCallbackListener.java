package com.example.demo.util;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
