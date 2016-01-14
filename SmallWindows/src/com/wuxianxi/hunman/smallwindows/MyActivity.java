package com.wuxianxi.hunman.smallwindows;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_activity);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//点击外围，退出窗口
		this.finish();
		return true;
	}
	
	public void YesButton(View v) {
		this.finish();
		Toast.makeText(getApplicationContext(), "Yes, Hunman is Activity", Toast.LENGTH_LONG).show();
	}
	
	public void NoButton(View v) {
		this.finish();
		Toast.makeText(getApplicationContext(), "No, Hunman is not Activity", Toast.LENGTH_LONG).show();
	}
}
