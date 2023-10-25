package com.example.groccery

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.ListView

class CustomListViewView(context: Context, private val listView: ListView) : androidx.appcompat.widget.AppCompatImageView(context) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Get the list view's height for measurement
        val listViewHeight = listView.height

        // Measure and layout the custom view to match the list view's height
        measure(
            MeasureSpec.makeMeasureSpec(listViewHeight, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(listViewHeight, MeasureSpec.EXACTLY)
        )
        layout(0, 0, listViewHeight, listViewHeight)

        // Draw the ListView onto the custom view's canvas
        listView.draw(canvas)
    }
}


