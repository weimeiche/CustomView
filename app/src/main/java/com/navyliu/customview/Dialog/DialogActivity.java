package com.navyliu.customview.Dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.navyliu.customview.CustomView.TagsLayout;
import com.navyliu.customview.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-01-24.
 *
 * @auther navyLiu
 * @Email navyliu666666@gmail.com
 */

public class DialogActivity extends AppCompatActivity {

	private TagsLayout tagsLayout;

	private String[] btnTextStr = {"提示弹框", "Spinner弹框", "Progress弹框", "DatePicker弹框"};
	private String[] btnStr = {"PROMPT", "SPINNER", "PROGRESS", "DATE_PICKER"};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag);
		// 初始化tagsLayout
		tagsLayout = (TagsLayout) this.findViewById(R.id.tags_layout);
		ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < btnTextStr.length; i++) {
			TextView textView = new TextView(this);
			textView.setText(btnTextStr[i]);
			textView.setTextColor(Color.WHITE);
			textView.setBackgroundResource(R.drawable.round_square_blue);
			textView.setOnClickListener(new onclickLentenser(i));
			tagsLayout.addView(textView);
		}
	}


	private class onclickLentenser implements View.OnClickListener {
		int category;

		public onclickLentenser(int i) {
			category = i;
		}

		@Override
		public void onClick(View view) {
			switch (btnStr[category]) {
				case "PROMPT": // 提示弹框
					showPromptDialog();
					break;
				case "SPINNER": // Spinner弹框
					showSpinnerDialog();
					break;
				case "PROGRESS": // Progress弹框
					showLoadDialog("");
					break;
				case "DATE_PICKER":
					new DatePickerDialog(DialogActivity.this, onDateSetListencer, 2018, 1, 24).show();
				default:
					break;
			}
		}
	}


	private DatePickerDialog.OnDateSetListener onDateSetListencer = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
			ToastUtils.showShort(String.format("您选择的日期是：%d-%02d-%02d", year, month + 1, dayOfMonth));
		}
	};




	private ProgressDialog dialog = null;
	/**
	 * 加载进度提示
	 **/
	protected void showLoadDialog(String msg) {
		if (StringUtils.isEmpty(msg)) {
			msg = "数据加载中，请稍候...";
		}
		if (dialog == null || !dialog.isShowing()) {
			dialog = ProgressDialog.show(this, "", msg, true, true);
		}
	}
	/***关闭提示*/
	protected void dismissLoadDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}





	private String[] juTitleArr = {"京剧", "川剧", "豫剧", "现代剧"};
	private String[] juArr = {"jing", "chuan", "yu", "now"};
	private ArrayList<SpinnerBean> sList = new ArrayList<SpinnerBean>();

	private void showSpinnerDialog() {
		sList.clear();
		SpinnerBean spinnerBean;
		for (int i = 0; i < juTitleArr.length; i++) {
			spinnerBean = new SpinnerBean();
			spinnerBean.setItemId(juArr[i])
					.setItemStr(juTitleArr[i]);
			sList.add(spinnerBean);
		}

		final CustomSpinnerDialog spinnerDialog = new CustomSpinnerDialog(this, sList);
		spinnerDialog.setOnItemClick(new SpinnerAdapter.OnRecyclerItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				ToastUtils.showShort(String.format("您选择了%s", sList.get(position).getItemStr()));
				spinnerDialog.dismiss();
			}
		}).execute();
	}





	private void showPromptDialog() {
		final CustomPromptDialog promptDialog = new CustomPromptDialog(DialogActivity.this);
		promptDialog.setPromotTitle("系统提示")
				.setPromptMessage(new SpannableStringBuilder("这里是提示内容，设置了setPositiveListener监听，则显示两个按钮，否则显示一个按钮。"))
				.setNegativeListener("取消", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ToastUtils.showShort("您点击了取消按钮。");
						promptDialog.dismiss();
					}
				})
				.setPositiveListener("确认", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ToastUtils.showShort("您点击了确认按钮。");
						promptDialog.dismiss();
					}
				})
				.execute();
	}

}
