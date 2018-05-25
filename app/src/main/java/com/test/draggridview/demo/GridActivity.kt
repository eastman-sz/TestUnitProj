package com.test.draggridview.demo

import android.os.Bundle
import com.common.base.BaseAppCompactActivitiy
import com.test.R
import com.test.draggridview.DynamicGridView
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        initActivitys()
    }

    override fun initViews() {
        val adapter = CheeseDynamicAdapter(context , Cheeses.sCheeseStrings.asList(),
                resources.getInteger(R.integer.column_count))
        dynamicGridView.adapter = adapter

        dynamicGridView.setOnDragListener(object : DynamicGridView.OnDragListener{
            override fun onDragStarted(position: Int) {

            }

            override fun onDragPositionsChanged(oldPosition: Int, newPosition: Int) {

            }
        })

        dynamicGridView.setOnItemLongClickListener { parent, view, position, id ->
            dynamicGridView.startEditMode(position)

            true
        }

    }

    override fun onBackPressed() {
        if (dynamicGridView.isEditMode()) {
            dynamicGridView.stopEditMode()
        } else {
            super.onBackPressed()
        }
    }
}
