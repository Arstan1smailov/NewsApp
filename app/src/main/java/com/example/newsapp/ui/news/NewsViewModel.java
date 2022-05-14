package com.example.newsapp.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.MainActivity;
import com.example.newsapp.common.Resource;
import com.example.newsapp.data.model.MainResponse;
import com.example.newsapp.data.repositories.NewsRepositoriesImpl;
import com.example.newsapp.domain.repositories.NewsRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NewsViewModel extends ViewModel {
    private NewsRepositoriesImpl repositories;
    public LiveData<Resource<MainResponse>> liveData;
    @Inject
    public NewsViewModel(NewsRepositoriesImpl repositories) {
        this.repositories = repositories;
    }
    public void getTopNews(){
        liveData = repositories.getTopNews();
    }
}
