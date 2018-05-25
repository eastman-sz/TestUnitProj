package com.test.tb

import android.os.Bundle
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.test.R
import com.test.main.TextAdapter
import e.sfs.application.TreadmillControllerApplication
import e.sfs.controller.TreadmillControllerHelper
import e.sfs.tb.TbZlkTreadmillController
import kotlinx.android.synthetic.main.activity_tb_controller.*

class TbControllerActivity : BaseAppCompactActivitiy() {

    private var treadmillControllerHelper: TreadmillControllerHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tb_controller)

        initActivitys()

        TreadmillControllerApplication.init(this)

        treadmillControllerHelper = TreadmillControllerHelper(TbZlkTreadmillController())
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("Tb Controller")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
            override fun onRightBtnClick() {
            }
        })
    }

    override fun initViews() {
        val list = ArrayList<String>()
        list.add("Start")
        list.add("Stop")
        list.add("Pause")
        list.add("Speed Increase")
        val adapter = TextAdapter(context , list)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0 -> treadmillControllerHelper?.machineRun()
                1 -> treadmillControllerHelper?.machineStop()
                2 -> treadmillControllerHelper?.machinePause()
                3 -> treadmillControllerHelper?.increaseSpeed(1)
            }
        }

    }
}
