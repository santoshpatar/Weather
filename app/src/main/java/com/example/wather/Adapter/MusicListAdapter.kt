package com.example.wather.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wather.R
import com.example.wather.data.source.remote.model.ResultsItem
import com.example.wather.databinding.RowItemBinding
import java.util.*

class MusicListAdapter : RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>() {


    private lateinit var mCommentsArrayList: ArrayList<ResultsItem>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding : RowItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),(R.layout.row_item),parent,false);
        return MusicViewHolder(binding)
    }

    fun updateMusicList(commentsList: List<ResultsItem>?) {
        mCommentsArrayList = commentsList!! as ArrayList<ResultsItem>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (::mCommentsArrayList.isInitialized) mCommentsArrayList.size else 0
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val resultsItem = mCommentsArrayList.get(position)
        holder.bind(resultsItem)
        holder.rowIteMusicViewModel
    }




    inner class MusicViewHolder(val item: RowItemBinding)
        : RecyclerView.ViewHolder(item.root) {
        val rowIteMusicViewModel = com.example.wather.viewmodel.RowIteMusicViewModel()
        fun bind(comments: ResultsItem) {

            rowIteMusicViewModel.bind(comments)
            item.rowitem = rowIteMusicViewModel

        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, comments: ResultsItem, position: Int)

    }
}



