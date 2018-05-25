package com.html.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView contentWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_main);

        initViews();
    }

    @SuppressLint("JavascriptInterface")
    private void initViews(){
        contentWebView = findViewById(R.id.webview);
        // 启用javascript
        contentWebView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        contentWebView.loadUrl("file:///android_asset/html/webtest.html");
        contentWebView.addJavascriptInterface(MainActivity.this , "android");
    }

    public void onBtnClick(View v){
        int vid = v.getId();
        if (vid == R.id.button){
            // 无参数调用 JS的方法
            contentWebView.loadUrl("javascript:javacalljs()");

        }else if (vid == R.id.button2){

            // 传递参数调用JS的方法
            contentWebView.loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");
        }
    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
    public void startFunction(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"show",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();

            }
        });


    }


}
