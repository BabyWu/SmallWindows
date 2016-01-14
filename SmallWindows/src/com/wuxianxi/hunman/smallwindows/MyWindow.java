package com.wuxianxi.hunman.smallwindows;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

public class MyWindow {
	
	private Context mContext;
	private WindowManager mwinWindowManager;
	private View mView;
	private static boolean isShow = false;
	
	public MyWindow(Context context) {
		mContext = context.getApplicationContext();
	}

	public void showMyWindow() {
		if (isShow) {
			return;
		}
		
		mwinWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		
		//设置WindowManager.LayoutParams的属性
		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		//类型
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		//flags
		//如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View没焦点，收不到Back键的事件
		//当按Back、Home键时，背景应用退出，弹出的view就可以悬浮在桌面了。
		params.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
		params.format = PixelFormat.TRANSLUCENT;
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		params.gravity = Gravity.CENTER;
		
		//初始化View
		mView = initView(mContext);
		
		//点击back键，关闭window
		mView.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.d("wxx", "onKey");
				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:
					Log.d("wxx", "onKey BACK");
					hideMyWindow();
					return true;

				default:
				return false;
				}
			}
		});
		
		mwinWindowManager.addView(mView, params);
		isShow = true;
	}

	private View initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.layout_window, null);
		
		Button btnYes = (Button) view.findViewById(R.id.window_yes);
		btnYes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "Window yes!", Toast.LENGTH_LONG).show();
				hideMyWindow();
			}
		});
		
		Button btnNO = (Button) view.findViewById(R.id.window_no);
		btnNO.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "Window No!", Toast.LENGTH_LONG).show();
				hideMyWindow();
			}
		});		
		
		//点击window窗口外围，关闭window
		final View wView = view.findViewById(R.id.view_layout);
		view.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int x = (int) event.getX();
				int y = (int) event.getY();
				Rect rect = new Rect();
				wView.getGlobalVisibleRect(rect);
				if (!rect.contains(x, y)) {
					hideMyWindow();
				}
				return false;
			}
		});
		
		
		
		return view;
	}
	
	public void hideMyWindow() {
		if (isShow && mView != null) {
			mwinWindowManager.removeView(mView);
			isShow = false;
		}
	}
}
