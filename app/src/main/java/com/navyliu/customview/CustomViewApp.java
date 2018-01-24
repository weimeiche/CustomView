package com.navyliu.customview;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.navyliu.customview.constants.IConstants;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018-01-24.
 *
 * @auther navyLiu
 * @Email navyliu666666@gmail.com
 */

public class CustomViewApp extends Application {

	private static CustomViewApp instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		Utils.init(this);
//		LogUtils.getConfig().setLogSwitch(false);

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(IConstants.NETWORK_TIME, TimeUnit.MICROSECONDS)
				.readTimeout(IConstants.NETWORK_TIME, TimeUnit.MICROSECONDS)
				// 其他设置
				.build();
		OkHttpUtils.initClient(okHttpClient);
	}
}
