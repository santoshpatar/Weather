package com.example.wather.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.data.source.remote.model.ResultsItem
import com.squareup.picasso.Picasso

class RowIteMusicViewModel : ViewModel(){

    private val mTrackCensoredName = MutableLiveData<String>()
    private val mPreviewUrl = MutableLiveData<String>()
    private val mTrackViewUrl = MutableLiveData<String>()
    private val mArtistName = MutableLiveData<String>()
    private val mPrice = MutableLiveData<String>()


    fun bind(resultItem: ResultsItem) {
        mTrackCensoredName.value = resultItem.trackCensoredName;
        mPreviewUrl.value = resultItem.artworkUrl100
        mTrackViewUrl.value = resultItem.trackViewUrl
        mArtistName.value = resultItem.artistName
        mPrice.value = resultItem.collectionPrice.toString()


    }

    fun getOnlineComment() = mTrackCensoredName
    fun getOnLineTimeStamp() = mPreviewUrl
    fun getOnlineUserName() = mTrackViewUrl
    fun getCollectionPrice() = mPrice


    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView, imageUrl: String) {
            Picasso.get().load(imageUrl).into(view)
        }
    }

}