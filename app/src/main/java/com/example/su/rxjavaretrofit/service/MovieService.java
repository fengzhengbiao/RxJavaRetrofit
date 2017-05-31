package com.example.su.rxjavaretrofit.service;

import com.example.su.rxjavaretrofit.entity.HttpResult;
import com.example.su.rxjavaretrofit.entity.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by su on 2017/5/26.
 */

public interface MovieService {
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
