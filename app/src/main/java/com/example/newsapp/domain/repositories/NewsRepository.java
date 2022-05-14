package com.example.newsapp.domain.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.common.Resource;
import com.example.newsapp.data.model.MainResponse;

public interface NewsRepository {
    MutableLiveData<Resource<MainResponse>> getTopNews();

}
