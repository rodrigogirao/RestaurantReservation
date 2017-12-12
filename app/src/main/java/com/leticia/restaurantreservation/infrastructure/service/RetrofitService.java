package com.leticia.restaurantreservation.infrastructure.service;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.leticia.restaurantreservation.infrastructure.service.response.MessageResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leticia on 12/2/17.
 */

public class RetrofitService {

    private static final int SERVER_ERROR = 500;
    private static final String BASE_URL = "http://104.131.189.211:8085/";
    private static Retrofit retrofit;
    private static Converter<ResponseBody, MessageResponse> errorConverter;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit;
    }

    public static String convertThrowableToHttpMessage(Throwable throwable) throws IOException {
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            if (errorConverter != null && responseBody != null) {
                MessageResponse error = errorConverter.convert(responseBody);
                return error.getMessage();
            }
        }
        return throwable.getMessage();
    }

    public static boolean isHttp500Error(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            return httpException.response().code() == SERVER_ERROR;
        }
        return false;
    }

    private static Retrofit createRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        errorConverter = retrofit
                .responseBodyConverter(MessageResponse.class, new Annotation[0]);
        return retrofit;
    }

}
