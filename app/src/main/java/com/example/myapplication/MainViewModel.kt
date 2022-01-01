package com.example.paging3.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.Movies
import com.example.myapplication.MoviesPagingSource
import com.example.myapplication.Services.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val apiService: ApiService) : ViewModel()
{
    val getAllMovies:Flow<PagingData<Movies>> = Pager(config = PagingConfig(50,enablePlaceholders = false)){
        MoviesPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)


}
