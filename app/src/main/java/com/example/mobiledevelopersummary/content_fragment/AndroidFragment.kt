package com.example.mobiledevelopersummary.content_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
        viewModelFactory = ContentViewModelFactory(contentOrderByChild)
        viewmodel = ViewModelProvider(this, viewModelFactory).get(ContentListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewmodel



        return binding.root

    }
}