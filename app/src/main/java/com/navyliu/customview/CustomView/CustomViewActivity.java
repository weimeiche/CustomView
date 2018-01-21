package com.navyliu.customview.CustomView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navyliu.customview.Animation.TweenAnimationActivity;
import com.navyliu.customview.R;

/**
 * Created by Administrator on 2018-01-21.
 *
 * @auther navyLiu
 * @Email navyliu666666@gmail.com
 */


public class CustomViewActivity extends AppCompatActivity {

	private TagsLayout tagsLayout;

	private String[] btnTextStr = {"自定义顶部标题栏", "百分比饼图", "自定义输入框", "验证码", "标签云"};
	private String[] btnStr = {"TITLE_BAR", "PERCENT", "CLEAR_EDIT", "CHECK_CODE", "TAG"};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_view);

		// 初始化tagsLayout
		tagsLayout = (TagsLayout)this.findViewById(R.id.tags_layout);
		ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < btnTextStr.length; i++){
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
				case "TITLE_BAR": // 自定义顶部标题栏
					startActivity(CustomTitleBarActivity.class);
					break;
				case "PERCENT": // 百分比饼图
					startActivity(CustomPercentActivity.class);
					break;
				case "CLEAR_EDIT": // 自定义输入框
					startActivity(CustomClearEditActivity.class);
					break;
				case "CHECK_CODE": // 验证码
					startActivity(CheckCodeActivity.class);
					break;
				case "TAG": // 标签云
					startActivity(TagActivity.class);
					break;
				default:
					break;
			}
		}
	}


	private void startActivity(Class<?> activity) {
		Intent intent = new Intent(this, activity);
		startActivity(intent);
	}
}
