package com.example.mobiledevelopersummary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopersummary.bottom_navigation.MyFolderFragmentDirections
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.databinding.ListItemMyFolderBinding

class MyContentListAdapter: RecyclerView.Adapter<MyContentListAdapter.MyContentViewHolder>(){
    private val contents = ArrayList<MyContent>()

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
        holder.bind(contents[position])
    }
    override fun getItemCount(): Int=contents.size

    fun setData(new_content:List<MyContent>) {
       // val diffCallback=MyContentListDiffCallback(contents,new_content)
       // val diffResult=DiffUtil.calculateDiff(diffCallback)
        contents.clear()
        contents.addAll(new_content)
       // diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()

    }
    //Swipe delete item
    fun getMyContent(position:Int):MyContent{
        return contents[position]
    }
    fun deletePosition(position: Int){
        contents.removeAt(position)
        notifyItemRemoved(position)
    }


    class MyContentViewHolder(private val binding: ListItemMyFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.mycontent?.let { myContent ->
                    navigateToMyContentDetail(myContent,it)
                }
            }
        }
        private fun navigateToMyContentDetail(myContent: MyContent, view: View) {
            val direction = MyFolderFragmentDirections.actionMyFolderFragmentToMyContentDetail(myContent.contentId)
            view.findNavController().navigate(direction)
        }

        fun bind(item: MyContent) {
            binding.apply {
                mycontent = item
                executePendingBindings()

            }
        }

    }




}
/*class MyContentListDiffCallback(private val oldList: List<MyContent>, private val newList: List<MyContent>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].contentId === newList[newItemPosition].contentId
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {

        val oldItem=oldList[oldPosition]
        val newItem=newList[newPosition]

        return oldItem==newItem
    }


    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}*/



