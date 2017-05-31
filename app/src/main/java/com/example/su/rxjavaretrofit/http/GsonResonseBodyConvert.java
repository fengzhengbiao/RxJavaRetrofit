package com.example.su.rxjavaretrofit.http;

import android.util.Log;

import com.example.su.rxjavaretrofit.entity.HttpResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by su on 2017/5/26.
 */

public class GsonResonseBodyConvert<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResonseBodyConvert(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.d("Network", "response>>" + response);
        //httpResult 只解析result字段
        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        //
        if (httpResult.getCount() == 0) {
            throw new ApiException(100);
        }
        return gson.fromJson(response, type);
    }
}
