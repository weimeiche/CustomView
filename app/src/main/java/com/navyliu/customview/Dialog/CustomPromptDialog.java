package com.navyliu.customview.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.navyliu.customview.R;


/**
 * @Title: CustomPromptDialog.java
 * @Description: TODO
 * @author: navyLiu
 * @data: 2016-8-15 上午12:23:32
 * @version: V1.0
 */

/**
 * 修改为链式调用 2017-12-08
 * @author: navyLiu
 */
public class CustomPromptDialog extends Dialog {

	//定义回调事件，用于dialog的点击事件
	private Context mContext;

	private TextView dialogTitleTxt;
	private TextView dialogPromptTxt; // 提示语
	private LinearLayout promptBtnTwoLlayout; //
	private Button dialogOneBtn; // 第一个按钮
	private Button oneBtn; // 第二个按钮
	private Button seconedBtn; // 第二个按钮
	private RelativeLayout promptBtnOneRlayout; //

	private String title = "友情提醒";
	private String positiveBtnTxt = "确认";
	private String negativeBtnTxt = "取消";
	private SpannableStringBuilder message = null;
	private View.OnClickListener positiveListener = null;
	private View.OnClickListener negativeListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			cancel();
		}
	};

	public CustomPromptDialog(Context context) {
		super(context, R.style.loading);
		this.setCanceledOnTouchOutside(false);
		this.getWindow().setBackgroundDrawableResource(R.color.transparent);
		this.mContext = context;
		this.show();
	}

//	public static CustomPromptDialog build(Context context) {
//		return new CustomPromptDialog(context);
//	}

	public CustomPromptDialog setPromptMessage(SpannableStringBuilder message) {
		this.message = message;
		return this;
	}

	public CustomPromptDialog setPromotTitle(String title) {
		this.title = title;
		return this;
	}


	public CustomPromptDialog setPositiveListener(String positiveBtnTxt, View.OnClickListener positiveListener) {
		this.positiveBtnTxt = positiveBtnTxt;
		this.positiveListener = positiveListener;
		promptBtnOneRlayout.setVisibility(View.GONE);
		promptBtnTwoLlayout.setVisibility(View.VISIBLE);
		return this;
	}

	public CustomPromptDialog setNegativeListener(String negativeBtnTxt, View.OnClickListener negativeListener) {
		this.negativeBtnTxt = negativeBtnTxt;
		this.negativeListener = negativeListener;
		return this;
	}

	public void execute() {
		dialogTitleTxt.setText(title);
		dialogOneBtn.setOnClickListener(negativeListener);
		oneBtn.setOnClickListener(positiveListener);
		oneBtn.setText(positiveBtnTxt);
		seconedBtn.setOnClickListener(negativeListener);
		seconedBtn.setText(negativeBtnTxt);
		dialogPromptTxt.setText(message);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawableResource(R.color.transparent);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_prompt);
		//设置标题
		dialogTitleTxt = (TextView) this.findViewById(R.id.txt_dialog_title);
		promptBtnTwoLlayout = (LinearLayout) this.findViewById(R.id.llayout_prompt_btn_two);
		dialogPromptTxt = (TextView) this.findViewById(R.id.txt_dialog_prompt);
		oneBtn = (Button) this.findViewById(R.id.btn_dialog_one);
		seconedBtn = (Button) this.findViewById(R.id.btn_dialog_seconed);
		promptBtnOneRlayout = (RelativeLayout) this.findViewById(R.id.rlayout_prompt_btn_one);
		dialogOneBtn = (Button) this.findViewById(R.id.btn_dialog_only_one);
	}

	public void cancelDialog(){
		this.dismiss();
	}

}
