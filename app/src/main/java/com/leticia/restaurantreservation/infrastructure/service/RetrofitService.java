package com.leticia.restaurantreservation.infrastructure.service;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.leticia.restaurantreservation.domain.model.ResponseError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leticia on 12/2/17.
 */

public class RetrofitService {

    private static final String BASE_URL = "http://104.131.189.211:8085/";
    private static Retrofit retrofit;
    private static Converter<ResponseBody, ResponseError> errorConverter;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit;
    }

    public static String convertThrowableToHttpMessage(Throwable throwable) throws IOException {
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            if (errorConverter != null) {
                ResponseError error = errorConverter.convert(responseBody);
                return error.getMessage();
            }
        }
        return "";
    }

    private static Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        errorConverter = retrofit
                .responseBodyConverter(ResponseError.class, new Annotation[0]);
        return retrofit;
    }

}
