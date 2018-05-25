package com.test.webview

import android.net.http.SslError
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import com.test.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.util.*


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        val settings = webview.settings


        settings.setLoadWithOverviewMode(true)
//        settings.setBuiltInZoomControls(true)
        settings.setJavaScriptEnabled(true)
        settings.setUseWideViewPort(true)
//        settings.setSupportZoom(true)
        settings.setJavaScriptCanOpenWindowsAutomatically(true)
        settings.setCacheMode(WebSettings.LOAD_DEFAULT)
        settings.setGeolocationEnabled(true)
        settings.setDomStorageEnabled(true)
        settings.setDatabaseEnabled(true)
        settings.setUseWideViewPort(true) // 关键点
        settings.setAllowFileAccess(true) // 允许访问文件
//        settings.setSupportZoom(true) // 支持缩放
        settings.setLoadWithOverviewMode(true)
        settings.setPluginState(WebSettings.PluginState.ON)
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE) // 不加载缓存内容

        // 此方法禁止APP使用默认浏览器，必须写 不然会跳出APP 打开默认浏览器
        webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, s: String): Boolean {
                //必须重写的方法 解决了优酷 百度视频不播放视频加载失败的问题
 /*               return if (s.startsWith("intent") || s.startsWith("youku")) {
                    true
                } else {
                    super.shouldOverrideUrlLoading(view, s)
                }*/
                return false
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed();// 接受所有网站的证书

            }
        })

//        webview.loadUrl("http://www.iqiyi.com/")
        webview.loadUrl("https://m.youku.com/")

        returnBtn.setOnClickListener {
//            webview.clearHistory()
//            webview.clearMatches()
//            webview.pauseTimers()
//            webview.destroy()

            while (webview.canGoBack()){
                webview.goBack()
            }
            finish()

        }

        playBtn.setOnClickListener {
            try {
                webview.javaClass.getMethod("onResume").invoke(webview, null)
            }catch (e : Exception){
                e.printStackTrace()
            }

        }

        pauseBtn.setOnClickListener {
            try {
                webview.javaClass.getMethod("onPause").invoke(webview, null)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }


    override fun onBackPressed() {
        if (webview.canGoBack()){
            webview.goBack()

            return
        }
        super.onBackPressed()
    }
}
