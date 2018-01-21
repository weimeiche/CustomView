package com.navyliu.customview.CustomView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.navyliu.customview.R;

/**
 * Created by Administrator on 2018-01-21.
 *
 * @auther navyLiu
 * @Email navyliu666666@gmail.com
 */

public class TagActivity extends AppCompatActivity {


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag);

		TagsLayout tagsLayout = (TagsLayout)this.findViewById(R.id.tags_layout);
		String[] btnTextStr = {"姹紫嫣红开遍", "似这般都付与断井颓垣", "良辰美景奈何天", "赏心乐事谁家院"
				, "朝飞暮卷", "云霞翠轩", "雨丝风片", "烟波画船", "锦屏人忒看的这韶光贱"};
		for (int i = 0; i < btnTextStr.length; i++){
			TextView textView = new TextView(this);
			textView.setText(btnTextStr[i]);
			textView.setTextColor(Color.WHITE);
			textView.setBackgroundResource(R.drawable.round_square_blue);
			tagsLayout.addView(textView);
		}
	}
}
