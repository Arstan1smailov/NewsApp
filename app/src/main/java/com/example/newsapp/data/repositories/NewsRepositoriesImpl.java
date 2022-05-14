package com.example.newsapp.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.data.remote.NewsApi;
import com.example.newsapp.domain.repositories.NewsRepository;
import com.example.newsapp.common.Resource;
import com.example.newsapp.data.model.MainResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepositoriesImpl implements NewsRepository {
    private final String API_KEY = "bfe58d5883544f1d8d0b1447c0d98243";
    private MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
    private NewsApi api;
    @Inject
    public NewsRepositoriesImpl(NewsApi api) {
        this.api = api;
    }

    @Override
    public MutableLiveData<Resource<MainResponse>> getTopNews() {
        liveData.setValue(Resource.loading());
        api.getTopNews(API_KEY, "ru").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                }else{
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }


            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
}
