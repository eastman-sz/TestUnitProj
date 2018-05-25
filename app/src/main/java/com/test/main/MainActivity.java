package com.test.main;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.common.base.BaseAppCompactActivitiy;
import com.common.base.CommonTitleView;
import com.test.R;
import com.test.draggridview.demo.GridActivity;
import com.test.floatView.FloatView;
import com.test.floatView.FloatViewActivity;
import com.test.nongli.NongLiActivity;
import com.test.tb.TbControllerActivity;
import com.test.util.PermissionHelpler;
import com.test.webview.WebViewActivity;
import com.utils.lib.ss.common.PkgHelper;

import java.util.ArrayList;

public class MainActivity extends BaseAppCompactActivitiy {

    private ListView listView = null;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivitys();

        PermissionHelpler.requestPermissions(this);

        if (Build.VERSION.SDK_INT >= 23) {
            if (! Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
        }
    }

    @Override
    protected void initTitle() {
        CommonTitleView commonTitleView = findViewById(R.id.commontitle_view);
        commonTitleView.setCenterTitleText("TestUnit");
        commonTitleView.setLeftBtnText("");
    }

    @Override
    protected void initViews() {
        list.add("Html");
        list.add("TbController");
        list.add("FloatView");
        list.add("webView");
        list.add("NongLi");
        list.add("DragGridView");

        listView = findViewById(R.id.listView);
        TextAdapter adapter = new TextAdapter(this , list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (0 == position){
                    startActivity(new Intent(context ,com.html.main.MainActivity.class));
                }else if (1 == position){
                    startActivity(new Intent(context ,TbControllerActivity.class));
                }else if (2 == position){
                    startActivity(new Intent(context ,FloatViewActivity.class));
                }else if (3 == position){
                    startActivity(new Intent(context ,WebViewActivity.class));
                }else if (4 == position){
                    startActivity(new Intent(context ,NongLiActivity.class));
                }else if (5 == position){
                    startActivity(new Intent(context ,GridActivity.class));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(MainActivity.this, "not granted", Toast.LENGTH_SHORT);
                }
            }
        }
    }

}
