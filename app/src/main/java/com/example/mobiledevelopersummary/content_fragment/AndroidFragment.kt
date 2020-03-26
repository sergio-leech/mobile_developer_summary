package com.example.mobiledevelopersummary.content_fragment

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.MainActivity
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.databinding.FragmentKotlinBinding
import com.example.mobiledevelopersummary.viewmodel.ContentListViewModel
import com.example.mobiledevelopersummary.viewmodel.ContentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class AndroidFragment : Fragment() {
    lateinit var viewModelFactory: ContentViewModelFactory
    lateinit var viewmodel: ContentListViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentKotlinBinding.inflate(inflater, container, false)
        val contentOrderByChild = "02"
        val application= requireNotNull(this.activity).application
        viewModelFactory = ContentViewModelFactory(contentOrderByChild,application)
        viewmodel = ViewModelProvider(this, viewModelFactory).get(ContentListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewmodel
        binding.refreshBtn.setOnClickListener {
            activity?.finish()
            startActivity(Intent(application, MainActivity::class.java))

        }



        return binding.root

    }
}