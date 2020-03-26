package com.example.mobiledevelopersummary.bottom_navigation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.adapters.MyContentListAdapter
import com.example.mobiledevelopersummary.adapters.SwipeToDeleteCallback
import com.example.mobiledevelopersummary.database.ContentDatabase
import com.example.mobiledevelopersummary.databinding.FragmentMainFolderBinding
import com.example.mobiledevelopersummary.databinding.FragmentMyFolderBinding
import com.example.mobiledevelopersummary.viewmodel.MyContentListViewModel
import com.example.mobiledevelopersummary.viewmodel.MyContentListViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class MyFolderFragment : Fragment() {
private lateinit var binding:FragmentMyFolderBinding
    private lateinit var myContentListViewModel: MyContentListViewModel
    private lateinit var myContentListViewModelFactory: MyContentListViewModelFactory
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentMyFolderBinding.inflate(inflater,container,false)

        val application = requireNotNull(this.activity).application
         val dataSource= ContentDatabase.getInstance(application).contentDatabaseDao
        myContentListViewModelFactory= MyContentListViewModelFactory(dataSource)
        myContentListViewModel=ViewModelProvider(this,myContentListViewModelFactory).get(MyContentListViewModel::class.java)
        val text=binding.textListEmpty
       // binding.viewModel=myContentListViewModel
        binding.lifecycleOwner=this
        val adapter=MyContentListAdapter()
        val recyclerView=binding.myContentList
        recyclerView.adapter=adapter
        recyclerView.setHasFixedSize(true)

        myContentListViewModel.myContent.observe(viewLifecycleOwner, Observer {
          if(it.isEmpty()){
              text.visibility=View.VISIBLE
              adapter.notifyDataSetChanged()
          }else{
              text.visibility=View.GONE
              adapter.setData(it)
          }


        })


        val swipeHandler = object : SwipeToDeleteCallback(application){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myContentListViewModel.delete(adapter.getMyContent(viewHolder.adapterPosition))
                adapter.deletePosition(viewHolder.adapterPosition)
                Toast.makeText(application,"Deleted from < MY FOLDER >",Toast.LENGTH_SHORT).show()
            }
        }
        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerView)
        return binding.root
    }



}
