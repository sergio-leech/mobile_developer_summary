package com.example.mobiledevelopersummary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopersummary.bottom_navigation.MainFolderFragmentDirections
import com.example.mobiledevelopersummary.databinding.ListItemBinding
import com.example.mobiledevelopersummary.models.Content

class ContentAdapter:ListAdapter<Content,ContentAdapter.ContentViewHolder>(ContentDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContentViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
              binding.setClickListener {
                  binding.content?.let { content ->
                      navigateToContentDetail(content,it)
                  }
              }
        }


        private fun navigateToContentDetail(content: Content, view: View) {
            val direction= MainFolderFragmentDirections.actionMainFolderFragmentToContentDetail(content.contentId)
            view.findNavController().navigate(direction)
        }
        fun bind(item: Content) {
            binding.apply {
                content = item
                executePendingBindings()
            }
        }

    }

}
private class ContentDiffCallback:DiffUtil.ItemCallback<Content>(){
    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem.contentId==newItem.contentId
    }

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem==newItem
    }

}