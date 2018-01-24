package com.navyliu.customview.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.navyliu.customview.R;


/**
 * 自定义dialog
 *
 */
public class CustomProgressDialog extends Dialog {
	//定义回调事件，用于dialog的点击事件
	private Context mContext;
	private boolean cancelOutside = false;
	private TextView loadingTxt;
	
	public CustomProgressDialog(Context context, String name) {
		super(context, R.style.loading);
		this.mContext = context;
		this.setCanceledOnTouchOutside(cancelOutside);
		this.getWindow().setBackgroundDrawableResource(R.color.transparent);
		this.show();
		loadingTxt.setText(name);
	}

	public CustomProgressDialog(Context context, int id) {
		super(context, R.style.loading);
		this.setCanceledOnTouchOutside(cancelOutside);
		this.getWindow().setBackgroundDrawableResource(R.color.transparent);
		this.show();
		loadingTxt.setText(id);
	}

	public CustomProgressDialog(Context context) {
		super(context, R.style.loading);
		this.setCanceledOnTouchOutside(cancelOutside);
		this.getWindow().setBackgroundDrawableResource(R.color.transparent);
		this.show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawableResource(R.color.transparent);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_loading);

		loadingTxt = (TextView)this.findViewById(R.id.txt_loading);
	}


	public void cancelDilog() {
		if (this == null){
			return;
		}
		this.cancel();
	}
}
