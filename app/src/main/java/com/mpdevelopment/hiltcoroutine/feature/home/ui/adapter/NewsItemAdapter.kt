package com.mpdevelopment.hiltcoroutine.feature.home.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mpdevelopment.hiltcoroutine.R


object NewsItemAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImage(imageView: ImageView, uri: String?) = uri?.let {
        var requestOptions = RequestOptions().apply {
            transform(CenterCrop(), RoundedCorners(16))
        }
        Glide.with(imageView.context)
            .load(it)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_football)
            .apply(requestOptions)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("circleSrc")
    fun setThumbnail(imageView: ImageView, uri: String?) = uri?.let {
        Glide.with(imageView.context)
            .load(it)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_football)
            .circleCrop()
            .into(imageView)
    }
}