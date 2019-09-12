package com.example.myapplication.mvvm

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMusicItemBinding


import com.example.myapplication.mvvm.MusicFragment.OnListFragmentInteractionListener
import com.example.myapplication.mvvm.dummy.DummyContent.DummyItem
import com.example.myapplication.mvvm.model.song.Song

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private var mValues: MutableList<Song>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = DataBindingUtil.inflate<FragmentMusicItemBinding>(LayoutInflater.from(parent.context),R.layout.fragment_music_item,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = mValues[position]
        holder.binding?.song = item
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder : RecyclerView.ViewHolder {
        var binding :FragmentMusicItemBinding?=null

        constructor(binding: FragmentMusicItemBinding) : super(binding.root) {
            this.binding = binding
        }
    }
}
