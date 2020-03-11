package com.example.mobiledevelopersummary.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mobiledevelopersummary.content_fragment.AdditionalToolsFragment
import com.example.mobiledevelopersummary.content_fragment.AndroidFragment
import com.example.mobiledevelopersummary.content_fragment.KotlinFragment


const val KOTLIN_PAGE_INDEX = 0
const val ANDROID_PAGE_INDEX = 1
const val ADDITIONAL_TOOLS_PAGE_INDEX = 2
class ContentViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        KOTLIN_PAGE_INDEX to { KotlinFragment() },
        ANDROID_PAGE_INDEX to { AndroidFragment() },
        ADDITIONAL_TOOLS_PAGE_INDEX to {AdditionalToolsFragment()}
    )
    override fun getItemCount(): Int=tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()

    }
}