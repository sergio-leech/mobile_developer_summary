package com.example.mobiledevelopersummary.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.adapters.ContentAdapter
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.models.Content

import com.example.mobiledevelopersummary.viewmodel.MyDetailViewModel
import com.github.chrisbanes.photoview.PhotoView


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
@BindingAdapter("imageDetailFromUrl")
fun bindImageDetailFromUrl(view: PhotoView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Content>?) {
    val adapter = ContentAdapter()
    recyclerView.adapter = adapter
    adapter.submitList(data)

}

@BindingAdapter("myListData")
fun myBindRecyclerView(recyclerView: RecyclerView, data: List<MyContent>?) {
    val myContentListAdapter = MyContentListAdapter()
    recyclerView.adapter = myContentListAdapter
    if (data != null) {
        myContentListAdapter.setData(data)

    }

}

/*@BindingAdapter("contentFirebaseStatus")
fun bindStatus(statusImageView: ImageView, status:ContentFirebaseStatus?) {
    when (status) {
        ContentFirebaseStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ContentFirebaseStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ContentFirebaseStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}*/
