package com.example.su.rxjavaretrofit.http;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by su on 2017/5/26.
 */

public class ResonseConvertory extends Converter.Factory {
    public static ResonseConvertory create() {
        return create(new Gson());
    }

    public static ResonseConvertory create(Gson gson) {
        return new ResonseConvertory(gson);
    }

    private final Gson gson;

    private ResonseConvertory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GsonResonseBodyConvert<>(gson, type);
    }
}
