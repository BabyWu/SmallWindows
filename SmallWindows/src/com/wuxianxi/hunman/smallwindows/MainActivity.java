package com.wuxianxi.hunman.smallwindows;

import com.wuxianxi.hunman.smallwindows.MyDialog.onClickInterface;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button btnWindow;
	private Button btnDialog;
	private Button btnActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}

	private void initView() {
		btnWindow = (Button) findViewById(R.id.btn_window);
		btnDialog = (Button) findViewById(R.id.btn_dialog);
		btnActivity = (Button) findViewById(R.id.btn_activity);
		
		btnWindow.setOnClickListener(this);
		btnDialog.setOnClickListener(this);
		btnActivity.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_window:
			MyWindow myWindow = new MyWindow(this);
			myWindow.showMyWindow();
			break;
		case R.id.btn_dialog:
			//注意下面第一个参数不能为getApplicationContext()，而应该是Activity, 因为办有activity才能添加窗口
			final MyDialog dialog = new MyDialog(MainActivity.this, "Hunman - Dialog", "Hunman is a Dialog\nYes or No!");
			dialog.show();
			dialog.setOnClickInterface(new MyDialog.onClickInterface() {
				
				@Override
				public void clickYes() {
					dialog.dismiss();
					Toast.makeText(getApplicationContext(), "Yes, Hunman is Dialog", Toast.LENGTH_LONG).show();
				}
				
				@Override
				public void clickNo() {
					dialog.dismiss();
					Toast.makeText(getApplicationContext(), "Yes, Hunman is not Dialog", Toast.LENGTH_LONG).show();
				}
			});
			break;
		case R.id.btn_activity:
			Intent intent3 = new Intent(MainActivity.this, MyActivity.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	}

}
