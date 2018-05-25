package com.test.floatView

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.View
import com.test.R
import com.test.main.MainActivity
import kotlinx.android.synthetic.main.activity_float_view.*

class FloatViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_float_view)
    }


    fun onBtnClick(view : View){
        when(view){
            btn -> {
                startActivity(Intent(Settings.ACTION_SETTINGS).putExtra("extra_prefs_show_button_bar", true))

                val floatView = FloatView.getInstance()
                floatView.showFloatView()
                floatView.setOnReturnClickListener {
                    runOnUiThread {
                        val btm = BitmapFactory.decodeResource(resources,
                                R.drawable.sfs_ic_delete)
                        val mBuilder = NotificationCompat.Builder(
                                this@FloatViewActivity).setSmallIcon(R.drawable.sfs_ic_delete)
                                .setContentTitle("5 new message")
                                .setContentText("twain@android.com")
                        mBuilder.setTicker("New message")//第一次提示消息的时候显示在通知栏上
                        mBuilder.setNumber(12)
                        mBuilder.setLargeIcon(btm)
                        mBuilder.setAutoCancel(true)//自己维护通知的消失

                        //构建一个Intent
                        val resultIntent = Intent(this@FloatViewActivity,
                                MainActivity::class.java)

                        val resultPendingIntent = PendingIntent.getActivity(
                                this@FloatViewActivity, 0, resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT)

                        // 设置通知主题的意图
                        mBuilder.setContentIntent(resultPendingIntent)
                        //获取通知管理器对象
                        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        mNotificationManager.notify(0, mBuilder.build())

                        try {
                            //                                        PkgHelper.openAPKByPkgName(MainActivity.this , "com.test");

                            Log.e("ilog", "点击返回请求000")

                            val resultIntent1 = Intent(this@FloatViewActivity,
                                    FloatViewActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                            val resultPendingIntent1 = PendingIntent.getActivity(
                                    this@FloatViewActivity, 0, resultIntent1,
                                    PendingIntent.FLAG_UPDATE_CURRENT)

                            startActivity(resultIntent1)

                        } catch (e: Exception) {
                            e.printStackTrace()
                            Log.e("ilog", "点击返回请求111")
                        }


                    }
                }

            }
        }
    }
}
