package com.example.mobiledevelopersummary.bottom_navigation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
       // binding.viewModel=myContentListViewModel
        binding.lifecycleOwner=this
        val adapter=MyContentListAdapter()
        val recyclerView=binding.myContentList
        recyclerView.adapter=adapter
        recyclerView.setHasFixedSize(true)
        myContentListViewModel.myContent.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setData(it)
            }
        })

          // Swipe delete item
        /*val itemTouchHelperCallback=object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                      return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            myContentListViewModel.delete(adapter.getMyContent(viewHolder.adapterPosition))
                adapter.deletePosition(viewHolder.adapterPosition)


            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                val itemView = viewHolder.itemView
                val deleteIcon=ContextCompat.getDrawable(application,R.drawable.delete)

                val colorDrawableBackground=ColorDrawable(Color.WHITE)
                val iconMarginVertical = (viewHolder.itemView.height - deleteIcon!!.intrinsicHeight) / 2

                if (dX > 0) {
                    colorDrawableBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                    deleteIcon.setBounds(
                        itemView.left + iconMarginVertical,
                        itemView.top + iconMarginVertical,
                        itemView.left + iconMarginVertical + deleteIcon.intrinsicWidth,
                        itemView.bottom - iconMarginVertical
                    )
                } else {
                    colorDrawableBackground.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    deleteIcon.setBounds(
                        itemView.right - iconMarginVertical - deleteIcon.intrinsicWidth,
                        itemView.top + iconMarginVertical,
                        itemView.right - iconMarginVertical,
                        itemView.bottom - iconMarginVertical
                    )
                    deleteIcon.level = 0
                }

                colorDrawableBackground.draw(c)

                c.save()

                if (dX > 0)
                    c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                else
                    c.clipRect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)

                deleteIcon.draw(c)

                c.restore()
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)*/
        val swipeHandler = object : SwipeToDeleteCallback(application){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myContentListViewModel.delete(adapter.getMyContent(viewHolder.adapterPosition))
                adapter.deletePosition(viewHolder.adapterPosition)
            }
        }
        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerView)
        return binding.root
    }



}
