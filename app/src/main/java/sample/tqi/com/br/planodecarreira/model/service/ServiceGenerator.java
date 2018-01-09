package sample.tqi.com.br.planodecarreira.model.service;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator<T> {

    public static final String ENDPOINT = "http://10.10.0.46:8024/";
    public static <T>T create(Class<T> apiClass, boolean withToken, Context context) {
//        String BASE_URL = context.getString(R.string.ENDPOINT);
        String BASE_URL = ENDPOINT;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(getLoggingInterceptor());
        if (withToken) {
            builder.addInterceptor(getAuthorizationInterceptor(context));
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build();

        return retrofit.create(apiClass);
    }

    public static <T>T createWithContentType(Class<T> apiClass, boolean withToken, Context context) {
//        String BASE_URL = context.getString(R.string.ENDPOINT);
        String BASE_URL = ENDPOINT;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(getLoggingInterceptor());
        if (withToken) {
            builder.addInterceptor(getAuthorizationInterceptor(context));
            builder.addInterceptor(getContentType(context));
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build();

        return retrofit.create(apiClass);
    }

    public static <T>T createUpload(Class<T> apiClass) {
        String BASE_URL = ENDPOINT;

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        return retrofit.create(apiClass);
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    private static Interceptor getAuthorizationInterceptor(final Context context) {
        return chain -> {
            Request original = chain.request();
            String authToken = "Bearer ";// + DataStorage.getAccessToken();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", authToken)
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private static Interceptor getContentType(final Context context) {
        return chain -> {
            Request original = chain.request();
            String value = "application/json";
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Content-Type", value)
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

}
