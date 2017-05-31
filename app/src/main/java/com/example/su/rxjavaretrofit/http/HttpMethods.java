package com.example.su.rxjavaretrofit.http;

import com.example.su.rxjavaretrofit.entity.HttpResult;
import com.example.su.rxjavaretrofit.entity.Subject;
import com.example.su.rxjavaretrofit.service.MovieService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by su on 2017/5/26.
 */

public class HttpMethods {
    private static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit sRetrofit;

    private MovieService movieService;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        sRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ResonseConvertory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        movieService = sRetrofit.create(MovieService.class);

    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count) {
        Observable observable = movieService.getTopMovie(start, count)
                .map(new HttpResultFunc<>());
        toSubscrible(observable, subscriber);
    }


    private <T> void toSubscrible(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return tHttpResult.getSubjects();
        }
    }
}

