package com.test.nongli

import android.os.Bundle
import com.common.base.BaseAppCompactActivitiy
import com.test.R
import kotlinx.android.synthetic.main.activity_nong_li.*
import com.test.nongli.Lunar.chineseDateFormat
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.StringBuilder
import java.util.*


class NongLiActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nong_li)

        initActivitys()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("农历")
    }

    override fun initViews() {
        val today = Calendar.getInstance()
        today.time = chineseDateFormat.parse("2017年8月18日")
        val lunar = Lunar(today)

        val msg = "北京时间：" + chineseDateFormat.format(today.getTime()) + "　农历" + lunar

        System.out.println("北京时间：" + chineseDateFormat.format(today.getTime()) + "　农历" + lunar);

        infoTextView.text = "H: ".plus(msg).plus(" -- ").plus(lunar.animalsYear()).plus(" ---- ").plus(lunar.cyclical())


        doAsync {
            val builder = StringBuilder()

            //测试获取一周天气
            val listData = WeatherUtil.getWeekWeatherMap("101280601")
            for (j in listData.indices) {
                val wMap = listData[j]

                builder.append(wMap.get("city").toString().plus("-"))
                        .append(wMap.get("date_y").toString().plus("-"))
                        .append(wMap.get("week").toString().plus("-"))
                        .append(wMap.get("weather").toString().plus("-"))
                        .append(wMap.get("temp").toString().plus("-"))
                        .append(wMap.get("wind").toString().plus("-"))
                        .append(wMap.get("fl").toString().plus("-"))

            }

            uiThread {
                wetherTextView.text = "一周天气: ".plus(builder.toString())
            }
        }





    }

}
