package com.example.mobiledevelopersummary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.databinding.ListItemMyFolderBinding


class MyContentAdapter :
    ListAdapter<MyContent, MyContentAdapter.MyContentViewHolder>(MyContentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyContentViewHolder {
        return MyContentViewHolder(
            ListItemMyFolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyContentViewHolder(private val binding: ListItemMyFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyContent) {
            binding.apply {
                mycontent = item
                executePendingBindings()
            }
        }

    }


}

private class MyContentDiffCallback : DiffUtil.ItemCallback<MyContent>() {
    override fun areItemsTheSame(oldItem: MyContent, newItem: MyContent): Boolean {
        return oldItem.contentId == newItem.contentId
    }

    override fun areContentsTheSame(oldItem: MyContent, newItem: MyContent): Boolean {
        return oldItem == newItem
    }


}