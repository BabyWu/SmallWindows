package com.wuxianxi.hunman.smallwindows;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MyDialog extends Dialog implements android.view.View.OnClickListener {

	private Context mContext;
	private String mTitle;
	private String mDetail;
	
	private TextView mTextTitle;
	private TextView mTextDetail;
	private TextView mButtonYes;
	private TextView mButtonNo;
	
	private onClickInterface mOnclClickInterface;
	
	public MyDialog(Context context, String title, String detail) {
		super(context, R.style.MyDialogStyle);
		this.mContext = context;
		this.mTitle = title;
		this.mDetail = detail;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		initView();
	}

	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.layout_dialog, null);
		setContentView(view);
		
		mButtonYes = (TextView) view.findViewById(R.id.dialog_yes);
		mButtonNo = (TextView) view.findViewById(R.id.dialog_no);
		mTextTitle = (TextView) view.findViewById(R.id.dialog_title);
		mTextDetail = (TextView) view.findViewById(R.id.dialog_detail);
		
		mTextTitle.setText(mTitle);
		mTextDetail.setText(mDetail);
		
		mButtonYes.setOnClickListener(this);
		mButtonNo.setOnClickListener(this);
	}
	
	public interface onClickInterface {
		public void clickYes();
		public void clickNo();
	}
	
	public void setOnClickInterface(onClickInterface onclClickInterface) {
		this.mOnclClickInterface = onclClickInterface;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_yes:
			mOnclClickInterface.clickYes();
			break;
		case R.id.dialog_no:
			mOnclClickInterface.clickNo();
			break;
		default:
			break;
		}
		
	}

}
