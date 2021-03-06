package com.example.mobiledevelopersummary.bottom_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.adapters.*
import com.example.mobiledevelopersummary.databinding.FragmentMainFolderBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainFolderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainFolderBinding.inflate(inflater, container, false)

        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = ContentViewPagerAdapter(this)
        viewPager.setPageTransformer(DepthPageTransformer())
        // Set the  text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            KOTLIN_PAGE_INDEX -> getString(R.string.kotlin)
            ANDROID_PAGE_INDEX -> getString(R.string.android)
            ADDITIONAL_TOOLS_PAGE_INDEX -> getString(R.string.additional_tools)
            else -> null
        }
    }
}
