package com.example.newsapp.di;

import com.example.newsapp.data.remote.NewsApi;
import com.example.newsapp.data.repositories.NewsRepositoriesImpl;
import com.example.newsapp.domain.repositories.NewsRepository;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @OtherInterceptorOkHttpClient
    public static OkHttpClient provideBaseOkHttpClient(){
     return new OkHttpClient.Builder().addInterceptor(
             new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

     ).build();
    }
    @Provides
    public static Retrofit provideRetrofit(@OtherInterceptorOkHttpClient OkHttpClient client){
        return new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
    }
    @Provides
    public static NewsApi provideApi(Retrofit retrofit){
        return retrofit.create(NewsApi.class);
    }
    @Provides
    public static NewsRepository providesNewsRepository(NewsApi api){
        return new NewsRepositoriesImpl(api);
    }
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    private @interface AuthInterceptorOkHttpClient {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    private @interface OtherInterceptorOkHttpClient {}
}
