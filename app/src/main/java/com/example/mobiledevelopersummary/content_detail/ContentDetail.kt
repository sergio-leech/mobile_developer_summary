package com.example.mobiledevelopersummary.content_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.databinding.ActivityContentDetailBinding
import com.example.mobiledevelopersummary.viewmodel.DetailViewModel
import com.example.mobiledevelopersummary.viewmodel.DetailViewModelFactory
import kotlinx.android.synthetic.main.activity_content_detail.*

class ContentDetail : AppCompatActivity() {
private lateinit var binding:ActivityContentDetailBinding
    private lateinit var viewModel:DetailViewModel
    private lateinit var viewModelFactory:DetailViewModelFactory
   // private val args:ContentDetailArgs by navArgs<>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_content_detail)
      val arg=ContentDetailArgs.fromBundle(intent.extras!!).contentId
        viewModelFactory= DetailViewModelFactory(arg)
        viewModel=ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel=viewModel
        binding.lifecycleOwner = this


       var isToolbarShown = false

       // scroll change listener begins at Y = 0 when image is fully collapsed
       content_detail_scrollview.setOnScrollChangeListener(
           NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

               // User scrolled past image to height of toolbar and the title text is
               // underneath the toolbar, so the toolbar should be shown.
               val shouldShowToolbar = scrollY > toolbar?.height!!

               // The new state of the toolbar differs from the previous state; update
               // appbar and toolbar attributes.
               if (isToolbarShown != shouldShowToolbar) {
                   isToolbarShown = shouldShowToolbar

                   // Use shadow animator to add elevation if toolbar is shown
                   appbar.isActivated = shouldShowToolbar

                   // Show the plant name if toolbar is shown
                   toolbar_layout?.isTitleEnabled = shouldShowToolbar
               }
           }
       )

       toolbar?.setNavigationOnClickListener {
           onBackPressed()
       }
    }
}
