package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.Services.ApiService
import com.example.myapplication.databinding.EachRowBinding
import javax.inject.Inject

class MoviesAdapter @Inject constructor(): PagingDataAdapter<Movies, MoviesAdapter.MoviesViewHolder>(Diff()) {


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = getItem(position)
        if(movies!= null){
            holder.binds(movies)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder  =
        MoviesViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    class MoviesViewHolder(private val binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun binds(movies:Movies){
            binding.apply {

                movieTitle.text = movies.title
                moviePoster.load(ApiService.POSTER_URL + movies.poster_path)

            }
        }
    }

    class Diff : DiffUtil.ItemCallback<Movies>(){
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean  =
            oldItem.poster_path == newItem.poster_path

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean =
            oldItem == newItem
    }
}