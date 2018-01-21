package com.navyliu.customview.CustomView;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

/**
 * @Title:  CheckCode.java 
 * @Copyright:  重庆三加六商贸有限公司 Copyright 2016-2026,  All rights reserved 
 * @Description:  TODO<验证码的生成> 
 * @author:  navyLiu 
 * @data:  2016-6-18 下午11:14:12 
 * @version:  V1.0
 */
public class CheckCodeUtil {

	private static final String TAG = "CheckCode";
	
	private static final char[] CHARS = {
		'2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
	};
	
	private static CheckCodeUtil bmpCode;
	
	public CheckCodeUtil getInstance(){
		if (bmpCode == null) {
			bmpCode = new CheckCodeUtil();
		}
		return bmpCode;
	}
	
	// default settings
	private static final int DEFAULT_CODE_LENGTH = 4;
	private static final int DEFAULT_FONT_SIZE = 60;
	private static final int DEFAULT_LINE_NUMBER = 30;
	private static final int DEFAULT_POINT_NUMBER = 100;
	private static final int BASE_PADDING_LEFT = 20,
			RANGE_PADDING_LEFT = 20,
			BASE_PADDING_TOP = 50,
			RANGE_PADDING_TOP = 20;
	private static final int DEFAULT_WIDTH = 250,
			DEFAULT_HEIGHT = 80;
	private static final int DEFAULT_COLOR = 0xDF;
	
	// settings decided by the layout xml
	//canvas width and height
	private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;
	
	//random word space and padding_top
	private int base_padding_left = BASE_PADDING_LEFT,
			range_padding_left = RANGE_PADDING_LEFT,
			base_padding_top = BASE_PADDING_TOP,
			range_padding_top = RANGE_PADDING_TOP;
	
	// number of chars, lines; font size
	private int codeLength = DEFAULT_CODE_LENGTH,
			line_number = DEFAULT_LINE_NUMBER,
			point_number = DEFAULT_POINT_NUMBER,
			font_size = DEFAULT_FONT_SIZE;
	
	// variables
	private String code;
	private int padding_left, padding_top;
	private Random random = new Random();
	
	// 验证码图片
	public Bitmap createBitmap(){
		padding_left = 0;
		
		Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas c = new Canvas(bp);
		
		code = createCheckCode();
		
		c.drawColor(Color.rgb(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR));
		Paint paint = new Paint();
		paint.setTextSize(font_size);
		
		for (int i = 0; i < code.length(); i++) {
			randomTextStyle(paint);
			randomPadding();
			c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
		}
		
		for (int i = 0; i < line_number; i++) {
			drawLine(c, paint);
		}
		
		for (int i = 0; i < point_number; i++) {
			drawPoint(c, paint);
		}
		
		c.save(Canvas.ALL_SAVE_FLAG);
		c.restore(); 
		return bp;
	}
	
	
	public String getCheckCode(){
		return code;
	}


	// 验证码
	private String createCheckCode() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < codeLength; i++) {
			builder.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return builder.toString();
	}
	
	private void drawLine(Canvas canvas, Paint paint){
		int color = randomColor();
		int startX = random.nextInt(width);
		int startY = random.nextInt(height);
		int stopX = random.nextInt(width);
		int stopY = random.nextInt(height);
		paint.setStrokeWidth(1);
		paint.setColor(color);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}
	
	private void drawPoint(Canvas canvas, Paint paint){
		int[] point = getPoint(width, height);
		canvas.drawCircle(point[0], point[1], 1, paint);
	}
	
	private int randomColor(){
		return randomColor(1);
	}
	
	private int randomColor(int rate){
		int red = random.nextInt(256) / rate;
		int green = random.nextInt(256) / rate;
		int blue = random.nextInt(256) / rate;
		return Color.rgb(red, green, blue);
	}
	

	private void randomTextStyle(Paint paint) {
		// TODO Auto-generated method stub
		int color = randomColor();
		paint.setColor(color);
		paint.setFakeBoldText(random.nextBoolean()); // true为粗体，false为非粗体
		float skewX = random.nextInt(19) / 10;
		skewX = random.nextBoolean() ? skewX : -skewX;
		Log.d(TAG, "skewX:==" + skewX);
//		paint.setTextScaleX(skewX); // floatl类型参数，负数表示右斜，正数左斜
//		paint.setUnderlineText(true); // true为下划线，flase为非下划线
//		paint.setStrikeThruText(true); // true为删除线，false为非删除线
	}
	
	private int[] getPoint(int width, int height){
		int[] tempCheckNum = {0, 0, 0, 0};
		tempCheckNum[0] = (int)(Math.random() * width);
		tempCheckNum[1] = (int)(Math.random() * height);
		return tempCheckNum;
	}
	
	private void randomPadding(){
		padding_left += base_padding_left + random.nextInt(range_padding_left);
		padding_top = base_padding_top + random.nextInt(range_padding_top);
	}
}
