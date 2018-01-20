package com.navyliu.customview.Animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.navyliu.customview.R;


/**
 * Created by Administrator on 2017-10-25.
 */

public class TweenAnimationActivity extends Activity implements View.OnClickListener {

	private Button alphaBtn;
	private Button scaleBtn;
	private Button setBtn;
	private Button translateBtn;
	private Button rotateBtn;
	private ImageView animationImg;

	private Button frameBtn;
	private ImageView frameAnimImg;

	private Button propertyBtn;
	private ImageView propertyImg;

	private Button valueBtn;
	private TextView valueTxt;

//	private GridImageViewGroup groupVG;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		init();
	}

	private void init() {
		findViewId();
		setListener();
	}

	private void findViewId() {
		alphaBtn = (Button)this.findViewById(R.id.btn_alpha);
		scaleBtn = (Button)this.findViewById(R.id.btn_scale);
		setBtn = (Button)this.findViewById(R.id.btn_set_anima);
		translateBtn = (Button)this.findViewById(R.id.btn_translate);
		rotateBtn = (Button)this.findViewById(R.id.btn_rotate);
		animationImg = (ImageView)this.findViewById(R.id.img_animation);

		frameBtn = (Button)this.findViewById(R.id.btn_frame_anim);
		frameAnimImg = (ImageView)this.findViewById(R.id.img_frame_anim);

		propertyBtn = (Button)this.findViewById(R.id.btn_property_anim);
		propertyImg = (ImageView)this.findViewById(R.id.img_property);

		valueBtn = (Button)this.findViewById(R.id.btn_value);
		valueTxt = (TextView)this.findViewById(R.id.txt_value);

//		groupVG = (GridImageViewGroup)this.findViewById(R.id.vg_anim);
		initViews();
	}

	private void setListener() {
		alphaBtn.setOnClickListener(this);
		scaleBtn.setOnClickListener(this);
		setBtn.setOnClickListener(this);
		translateBtn.setOnClickListener(this);
		rotateBtn.setOnClickListener(this);

		frameBtn.setOnClickListener(this);

		propertyBtn.setOnClickListener(this);

		valueBtn.setOnClickListener(this);

//		groupVG.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_alpha:
				alphaAnimation();
				break;
			case R.id.btn_rotate:
				rotateAnimation();
				break;
			case R.id.btn_set_anima:
				setAnimation();
				break;
			case R.id.btn_translate:
				translateAnimation();
				break;
			case R.id.btn_scale:
				scaleAnimation();
				break;

			case R.id.btn_frame_anim:
				frameAnimation();
				break;

			case R.id.btn_property_anim:
				propertyAnimation();
				break;

			case R.id.btn_value:
				valueAnimation();
				break;

//			case R.id.vg_anim:
//				initViews();
//				break;
		}

	}



	private void initViews(){
		ImageView img = new ImageView(this);
		img.setImageResource(R.drawable.loading_9);
		img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addImageView();
			}
		});
	}


	private void addImageView() {
		final ImageView img = new ImageView(this);
		img.setImageResource(R.drawable.loading_1);
	}




	private void valueAnimation() {
		ValueAnimator valueAnimator = ValueAnimator.ofFloat( 0f, 102410.24f);
		valueAnimator.setDuration(50000);
		valueAnimator.setInterpolator(new LinearInterpolator());
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float money= (float) animation.getAnimatedValue();
				valueTxt.setText(String.format("%.2f", money));
			}
		});
		valueAnimator.start();
//		这里通过ofFloat()方法构造一个ValueAnimator实例，除此之外还提供了其他函数ofInt()、ofObject()、ofPropertyValuesHolder()函数，api 21之后又提供了ofArgb()，每个函数都是可以传入多个改变值。
	}

	private void propertyAnimation() {
//		Animator anim = AnimatorInflater.loadAnimator(this, R.animator.animator_alpha);
//		anim.setTarget(propertyImg);
//		anim.start();

//		ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(propertyImg, "alpha", 0f, 1f);
//		alphaAnimation.setDuration(500);
//		alphaAnimation.setRepeatCount(0);
//		alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
//		alphaAnimation.setStartDelay(200);
//		alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//		alphaAnimation.start();


		/**
		 * PropertyValuesHolder
		 */
		PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f);
		PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.5f);
		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(propertyImg, scaleXValuesHolder, scaleYValuesHolder);
		objectAnimator.setDuration(500);
		objectAnimator.setRepeatCount(10);
		objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
		objectAnimator.start();

		/**
		 * ViewPropertyAnimator
		 */
//		ViewPropertyAnimator viewPropertyAnimator=propertyImg.animate();
//		viewPropertyAnimator.scaleXBy(1.0f).scaleX(1.5f).scaleYBy(1.0f).scaleY(1.5f).setDuration(500).start();
	}

	private void frameAnimation() {
		frameAnimImg.setImageResource(R.drawable.animlist_logo);
		AnimationDrawable animationDrawable = (AnimationDrawable) animationImg.getDrawable();
		animationDrawable.start();
//		animationDrawable.stop();

//		AnimationDrawable anim = new AnimationDrawable();
//		for (int i = 1; i <= 12; i++){
//			int id = getResources().getIdentifier("loading_"+i, "mipmap", getPackageName());
//			Drawable drawable = getResources().getDrawable(id);
//			anim.addFrame(drawable, 200);
//		}
//		anim.setOneShot(false);
//		frameAnimImg.setImageDrawable(anim);
//		anim.start();

//		void start() - 开始播放动画
//		void stop() - 停止播放动画
//		addFrame(Drawable frame, int duration) - 添加一帧，并设置该帧显示的持续时间
//		void setOneShoe(boolean flag) - false为循环播放，true为仅播放一次
//		boolean isRunning() - 是否正在播放
	}




	private void setAnimation() {
		AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.anim_set);
		animationImg.startAnimation(animationSet);

//		AnimationSet animationSet = new AnimationSet(true);
//
//		Animation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
//		alphaAnimation.setDuration(500);//设置动画持续时间为500毫秒
//
//		Animation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//		scaleAnimation.setDuration(500);//设置动画持续时间为500毫秒
//		scaleAnimation.setRepeatMode(Animation.REVERSE);
//		scaleAnimation.setStartOffset(0);
//		scaleAnimation.setInterpolator(this, android.R.anim.decelerate_interpolator);//设置动画插入器
//
//		animationSet.addAnimation(alphaAnimation);
//		animationSet.addAnimation(scaleAnimation);
//
//		animationImg.startAnimation(animationSet);
	}

	private void translateAnimation() {
		Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
		animationImg.startAnimation(translateAnimation);
//		Animation translateAnimation = new TranslateAnimation(0, 100, 0, 0);
//		translateAnimation.setDuration(500);
//		translateAnimation.setInterpolator(this, android.R.anim.cycle_interpolator);//设置动画插入器
//		translateAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
//		animationImg.startAnimation(translateAnimation);
	}

	private void rotateAnimation() {
		Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anima_rotate);
		animationImg.startAnimation(rotateAnimation);
//		Animation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//		rotateAnimation.setDuration(500);
//		rotateAnimation.setFillAfter(true);
//		rotateAnimation.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);//设置动画插入器
//		animationImg.startAnimation(rotateAnimation);
	}

	private void scaleAnimation() {
		Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
		animationImg.startAnimation(scaleAnimation);
//		Animation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//		scaleAnimation.setDuration(500);//设置动画持续时间为500毫秒
//		scaleAnimation.setFillAfter(true);//如果fillAfter的值为true,则动画执行后，控件将停留在执行结束的状态
//		scaleAnimation.setFillBefore(false);//如果fillBefore的值为true，则动画执行后，控件将回到动画执行之前的状态
//		scaleAnimation.setRepeatCount(3);//设置动画循环次数
//		scaleAnimation.setRepeatMode(Animation.REVERSE);
//		scaleAnimation.setStartOffset(0);
//		scaleAnimation.setInterpolator(this, android.R.anim.decelerate_interpolator);//设置动画插入器
//		animationImg.startAnimation(scaleAnimation);
	}

	private void alphaAnimation() {
		Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		animationImg.startAnimation(alphaAnimation);
//				Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
//				alphaAnimation.setDuration(500);//设置动画持续时间为500毫秒
//				alphaAnimation.setFillAfter(false);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
//				animationImg.startAnimation(alphaAnimation);
	}



//	几种自带的动画插入器//
//	AccelerateInterpolator 加速，开始时慢中间加速//
//	DecelerateInterpolator 减速，开始时快然后减速//
//	AccelerateDecelerateInterolator 先加速后减速，开始结束时慢，中间加速//
//	AnticipateInterpolator 反向，先向相反方向改变一段再加速播放//
//	AnticipateOvershootInterpolator 反向加超越，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值//
//	BounceInterpolator 跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100//
//	CycleIinterpolator 循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)//
//	LinearInterpolator 线性，线性均匀改变//
//	OvershootInterpolator超越，最后超出目的值然后缓慢改变到目的值

	/**
	 * Interpolator（插值器）
	 */
//	Interpolator插值器用于控制动画的变化速率，也可以简单的理解成用于控制动画的快慢，插值器目前都只是对动画执行过程的时间进行修饰，并没有对轨迹进行修饰。系统提供的插值器有以下几种：
//	插值器名字 	解说 	对应的xml
//	AccelerateInterpolator 	 加速，开始时慢中间加速  	@android:anim/accelerate_interpolator
//	DecelerateInterpolator 	减速，开始时快然后减速 	@android:anim/decelerate_interpolator
//	AccelerateDecelerateInterolator　  	先加速后减速，开始结束时慢，中间加速  	@android:anim/accelerate_decelerate_interpolator
//	AnticipateInterpolator 	反向 ，先向相反方向改变一段再加速播放  	@android:anim/anticipate_interpolator
//	AnticipateOvershootInterpolator 	反向加超越，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值 	@android:anim/anticipate_overshoot_interpolator
//	BounceInterpolator  	跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100 	@android:anim/bounce_interpolator
//	CycleIinterpolator  	循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input) 	@android:anim/cycle_interpolator
//	LinearInterpolator  	线性，线性均匀改变 	@android:anim/linear_interpolator
//	OvershootInterpolator 	超越，最后超出目的值然后缓慢改变到目的值 	@android:anim/overshoot_interpolator
//
//	通过上面的名字大家是不是很眼熟，是的和补间动画的插值器是一致的。Android的动画插值器采用策略设计模式，都是实现了Interpolator这个接口，而Interpolator又是继承自一个叫做TimeInterpolator的接口（从3.0开始，增加了TimeInterpolator这个接口，并把原先的Interpolator接口的抽象方法移到了其中，3.0后的Interpolator接口也就什么也没做，只是对父类改了个名字，达到向下兼容）。

	/**
	 * TypeEvaluator（估值器）
	 */
//	TypeEvaluator用于根据当前属性改变的百分比来计算改变后的属性值，系统提供了如下几种估值器
//	IntEvaluator 针对整型属性
//	IntArrayEvaluator 针对整型属性集合
//	FloatEvaluator 针对浮点型属性
//	FloatArrayEvaluator 针对浮点型属性集合
//	ArgbEvaluator  针对Color属性
//	RectEvaluator 针对Rect属性
//	PointFEvaluator 针对PointF属性
//	TypeEvaluator设计也是采用策略设计模式，
//
//	public interface TypeEvaluator<T> {
//		public T evaluate(float fraction, T startValue, T endValue);
//	}
//
//	public class FloatEvaluator implements TypeEvaluator<Number> {
//		public Float evaluate(float fraction, Number startValue, Number endValue) {
//			float startFloat = startValue.floatValue();
//			return startFloat + fraction * (endValue.floatValue() - startFloat);
//		}
//	}
}
