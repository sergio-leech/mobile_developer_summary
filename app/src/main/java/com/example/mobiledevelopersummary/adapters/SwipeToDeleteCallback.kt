package com.example.mobiledevelopersummary.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopersummary.R

open class SwipeToDeleteCallback(val context: Context) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    @SuppressLint("ResourceAsColor")
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val deleteIcon = ContextCompat.getDrawable(context, R.drawable.delete_item_content)

        val colorDrawableBackground = ColorDrawable(Color.WHITE)
        val iconMarginVertical = (viewHolder.itemView.height - deleteIcon!!.intrinsicHeight) / 2

        if (dX > 0) {
            colorDrawableBackground.setBounds(
                itemView.left,
                itemView.top,
                dX.toInt(),
                itemView.bottom
            )
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

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}