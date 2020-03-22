package com.example.mobiledevelopersummary.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mobiledevelopersummary.adapters.ContentAdapter
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.models.Content


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
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
    val adapter = MyContentAdapter()
    recyclerView.adapter = adapter
    adapter.submitList(data)

}


