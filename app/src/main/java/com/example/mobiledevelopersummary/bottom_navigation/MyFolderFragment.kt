package com.example.mobiledevelopersummary.bottom_navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.adapters.MyContentListAdapter
import com.example.mobiledevelopersummary.adapters.SwipeToDeleteCallback
import com.example.mobiledevelopersummary.databinding.FragmentMyFolderBinding
import com.example.mobiledevelopersummary.viewmodel.MyContentListViewModel
import com.example.mobiledevelopersummary.viewmodel.MyContentListViewModelFactory


class MyFolderFragment : Fragment() {

    private lateinit var binding: FragmentMyFolderBinding
    private lateinit var myContentListViewModel: MyContentListViewModel
    private lateinit var myContentListViewModelFactory: MyContentListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFolderBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        myContentListViewModelFactory = MyContentListViewModelFactory(application)
        myContentListViewModel = ViewModelProvider(
            this,
            myContentListViewModelFactory
        ).get(MyContentListViewModel::class.java)

        val adapter = MyContentListAdapter()
        val recyclerView = binding.myContentList

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val text = binding.textListEmpty
        myContentListViewModel.myContent.observe(viewLifecycleOwner, Observer { list ->
            if (list.isEmpty()) {
                text.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            } else {
                text.visibility = View.GONE
                adapter.setData(list)
            }
        })

        val swipeHandler = object : SwipeToDeleteCallback(application) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myContentListViewModel.delete(adapter.getMyContent(viewHolder.adapterPosition))
                adapter.deletePosition(viewHolder.adapterPosition)
                Toast.makeText(
                    application,
                    getString(R.string.delete_from_my_folder),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerView)
        return binding.root
    }
}
