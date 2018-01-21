package com.navyliu.customview.CustomView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.navyliu.customview.R;

/**
 * Created by Administrator on 2018-01-21.
 *
 * @auther navyLiu
 * @Email navyliu666666@gmail.com
 */

public class CheckCodeActivity extends AppCompatActivity {


	private ImageView codeImg;
	private Button codeBtn;
	private CheckCodeUtil checkCode = new CheckCodeUtil(); // 验证码

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_code);


		codeBtn = (Button) this.findViewById(R.id.btn_code);
		codeImg = (ImageView) this.findViewById(R.id.img_code);
		changeCheckCode();

		codeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				changeCheckCode();
			}
		});

	}

	private void changeCheckCode() {
		codeImg.setImageBitmap(checkCode.getInstance().createBitmap());
		// 进行字符串比对的时候可以直接用equals，这个要区分大小写
		// 可以用equalsIgnoreCase，不区分大小写
		Log.d(this.getClass().getName(), "curr check code is" + checkCode.getInstance().getCheckCode());
	}
}
