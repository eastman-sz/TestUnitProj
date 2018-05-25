package com.test.floatView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import com.test.R;
import com.test.application.IApplication;
import com.utils.lib.ss.info.DeviceInfo;

@SuppressLint({ "InflateParams", "RtlHardcoded", "SetJavaScriptEnabled" })
public class FloatView {

	private Context mContext = null;
	private static final FloatView ldFloatView = new FloatView();
	private WindowManager mWindowManager = null;
	private WindowManager.LayoutParams param= new WindowManager.LayoutParams();
	private View mLayout = null;

	private int x, y, width, height ;
	
	private boolean floatViewShow = false;
	
	public static FloatView getInstance(){
		return ldFloatView;
	}

    private FloatView() {
        initFloatParams(15 , 350 , DeviceInfo.dip2px(IApplication.getContext() , 80f) , DeviceInfo.dip2px(IApplication.getContext() , 50f));
    }

    private void initFloatParams(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void showFloatView(){
		if (floatViewShow) {
			return;
		}
		floatViewShow = true;

		Context context = IApplication.getContext();

		mLayout = LayoutInflater.from(context).inflate(R.layout.float_view, null);

	    //获取WindowManager
	     mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
	        //设置LayoutParams(全局变量）相关参数
	     param.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT ;//WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;     // 系统提示类型,重要
	     param.format = 1;
	     param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 不能抢占聚焦点
	     param.flags = param.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
	     param.flags = param.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制
	      
	     param.alpha = 1.0f;
	        
	     param.gravity= Gravity.LEFT| Gravity.TOP;   //调整悬浮窗口至左上角
	        //以屏幕左上角为原点，设置x、y初始值
	     param.x = x;
	     param.y = y; //在横屏时，如果选择param.type = TYPE_SYSTEM_ALERT，则需要aram.y = y - 状态栏高度;
	     
	        //设置悬浮窗口长宽数据
	     param.width= width;
	     param.height= height;
	        
	        //显示myFloatView图像
	     mWindowManager.addView(mLayout, param);

	     mLayout.findViewById(R.id.returnBtn).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
//                 ActivityManager activityManager = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
//                 activityManager.killBackgroundProcesses(Settings.ACTION_SETTINGS);
//
//                 SuUtil.kill(Settings.ACTION_SETTINGS);
 /*                   try{
                        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
                        Method forceStopPackage = am.getClass().getDeclaredMethod("forceStopPackage", String.class);
                        forceStopPackage.setAccessible(true);
                        forceStopPackage.invoke(am, Settings.ACTION_SETTINGS);
                    }catch (Exception e){
                        ILog.e("操作异常");
                    }*/

                    if (null != onReturnClickListener){
                        onReturnClickListener.onReturn();
                    }

                 removeFloatView();
             }
         });
	}

	public void removeFloatView(){
		//在程序退出(Activity销毁）时销毁悬浮窗口
		if (null != mWindowManager && null != mLayout) {
			mWindowManager.removeView(mLayout);
			mWindowManager = null;
			mLayout = null;
		}
		floatViewShow = false;
	}	

	public interface OnReturnClickListener{
	    void onReturn();
    }

    private OnReturnClickListener onReturnClickListener = null;

    public void setOnReturnClickListener(OnReturnClickListener onReturnClickListener) {
        this.onReturnClickListener = onReturnClickListener;
    }
}
