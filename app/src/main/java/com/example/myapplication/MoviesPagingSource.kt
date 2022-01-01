package com.example.myapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.Services.ApiService
import retrofit2.HttpException
import java.io.IOException


class MoviesPagingSource
constructor(private val apiService: ApiService) : PagingSource<Int, Movies>() {

    private val DEFAULT_PAGE_INDEX= 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = apiService.getAllMovies(page)
            val result= response.body()!!.results
            LoadResult.Page(
                result,
                prevKey = if(page == DEFAULT_PAGE_INDEX) null else page-1,
                nextKey = if(result.isEmpty()) null else page+1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
