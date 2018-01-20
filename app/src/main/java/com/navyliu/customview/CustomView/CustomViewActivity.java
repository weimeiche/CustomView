package com.navyliu.customview.CustomView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navyliu.customview.R;

/**
 * Created by Administrator on 2017-10-21.
 */

public class CustomViewActivity extends AppCompatActivity {

	private TagsLayout tagsLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_view);

		// 初始化tagsLayout
		tagsLayout = (TagsLayout)this.findViewById(R.id.tags_layout);
		ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		String[] str = {"从我写代码那天起，我就没有打算写代码","从我写代码那天起","我就没有打算写代码","没打算","写代码"};
		for (int i = 0; i < str.length; i++){
			TextView textView = new TextView(this);
			textView.setText(str[i]);
			textView.setTextColor(Color.WHITE);
			textView.setBackgroundResource(R.drawable.round_square_blue);
			tagsLayout.addView(textView);
		}
	}
}
