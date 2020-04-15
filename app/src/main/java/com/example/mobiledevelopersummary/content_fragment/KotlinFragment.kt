package com.example.mobiledevelopersummary.content_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.MainActivity
import com.example.mobiledevelopersummary.databinding.FragmentCategoryBinding
import com.example.mobiledevelopersummary.viewmodel.ContentListViewModel
import com.example.mobiledevelopersummary.viewmodel.ContentViewModelFactory


class KotlinFragment : Fragment() {

    private lateinit var viewModelFactory: ContentViewModelFactory
    private lateinit var viewmodel: ContentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        viewModelFactory = ContentViewModelFactory(Category.KOTLIN.id_category, application)
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
