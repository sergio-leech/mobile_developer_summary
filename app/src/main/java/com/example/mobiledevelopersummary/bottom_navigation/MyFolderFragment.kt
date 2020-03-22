package com.example.mobiledevelopersummary.bottom_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.database.ContentDatabase
import com.example.mobiledevelopersummary.databinding.FragmentMainFolderBinding
import com.example.mobiledevelopersummary.databinding.FragmentMyFolderBinding
import com.example.mobiledevelopersummary.viewmodel.MyContentListViewModel
import com.example.mobiledevelopersummary.viewmodel.MyContentListViewModelFactory

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
        binding.viewModel=myContentListViewModel
        binding.lifecycleOwner=this

        return binding.root
    }

}
