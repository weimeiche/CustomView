package com.navyliu.customview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.navyliu.customview.Animation.TweenAnimationActivity;
import com.navyliu.customview.CustomView.CustomViewActivity;
import com.navyliu.customview.CustomView.TagsLayout;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private static final int CUSTOM_VIEW = 0; // 自定义控件
	private static final int TRANSLUCENT_DECOR = 1; // 沉浸式状态栏
	private static final int PHOTO_PICKER = 2; //
	private static final int ANIMATION = 3;// 动画效果
	private static final int NETWORK_IMAGE_LOADER = 4; // 网络图片加载

	private TagsLayout tagsLayout;

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化tagsLayout
		tagsLayout = (TagsLayout)this.findViewById(R.id.tags_layout);
		ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		String[] str = {"自定义控件", "全屏沉浸式透明状态栏", "多媒体选择器", "动画效果", "网络图片加载"};
		for (int i = 0; i < str.length; i++){
			Button button = new Button(this);
			button.setText(str[i]);
//			button.setTextColor(Color.WHITE);
//			button.setBackgroundResource(R.drawable.btn_theme);
			button.setOnClickListener(new onclickLentenser(i));
			tagsLayout.addView(button);
		}
	}


	private class onclickLentenser implements View.OnClickListener {
		int category;
		public onclickLentenser(int i) {
			category = i;
		}

		@Override
		public void onClick(View view) {
			switch (category){
				case CUSTOM_VIEW: // 自定义控件
					startActivity(CustomViewActivity.class);
					break;
				case TRANSLUCENT_DECOR: // 全屏沉浸式透明状态栏
//					startActivity(TranslucentDecorActivity.class);
					break;
				case PHOTO_PICKER:
//					startActivity(PhotoPickerActivity.class);
					break;
				case ANIMATION: // 动画效果
					startActivity(TweenAnimationActivity.class);
					break;
				case NETWORK_IMAGE_LOADER: // 网络图片加载
//					startActivity(ImageLoadActivity.class);
					break;
			}
		}
	}



	private void startActivity(Class<?> activity) {
		Intent intent = new Intent(this, activity);
		startActivity(intent);
	}
}
