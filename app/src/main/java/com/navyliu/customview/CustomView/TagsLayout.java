package com.navyliu.customview.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.navyliu.customview.R;

/**
 * Created by Administrator on 2017-10-18.
 */

public class TagsLayout extends ViewGroup {

	private int childHorizontalSpace;
	private int childVerticalSpace;

	public TagsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.TagsLayout);
		if (attrArray != null){
			childHorizontalSpace = attrArray.getDimensionPixelOffset(R.styleable.TagsLayout_tagHorizontalSpace, 0);
			childVerticalSpace = attrArray.getDimensionPixelOffset(R.styleable.TagsLayout_tagVerticalSpace, 0);
			attrArray.recycle();
		}
	}


	/**
	 * 负责设置子控件的测量模式和大小  根据所有子控件设置自己的宽和高
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获取他的父容器为他设置的测量模式和大小
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		// 如果是warp_content情况下， 记录宽和高
		int width = 0;
		int height = 0;
		/**
		 * 记录每一行的宽度  width不断取最大宽度
		 */
		int lineWidth = 0;
		// 每一行的高度 累计到height
		int lineHeight = 0;

		int count = getChildCount();
		int left = getPaddingLeft();
		int top = getPaddingTop();
		// 遍历每个子元素
		for(int i = 0; i < count; i++){
			View child = getChildAt(i);
			if (child.getVisibility() == GONE){
				continue;
			}
			// 测量每一个child的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			/**
			 * 得到child的lp
 			 */
//			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			ViewGroup.LayoutParams params = child.getLayoutParams();
			ViewGroup.MarginLayoutParams lp = null;
			//获取view的margin设置参数
			if (params instanceof ViewGroup.MarginLayoutParams) {
				lp = (ViewGroup.MarginLayoutParams) params;
			} else {
				//不存在时创建一个新的参数
				//基于View本身原有的布局参数对象
				lp = new ViewGroup.MarginLayoutParams(params);
			}

			// 得到子控件实际占据的宽度
			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + childHorizontalSpace;
			// 当前子控件实际占据的高度
			int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + childVerticalSpace;

			// 如果加入当前的child，超出最大宽度，则将当前最大宽度给width，累加height，开启新行
			if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()){
				width = Math.max(lineWidth, childWidth); // 取最大值
				lineWidth = childWidth; // 重新开启新行，开始记录
				// 叠加当前高度
				height += lineHeight;
				// 开启记录下一行的高度
				lineHeight = childHeight;
				child.setTag(new Location(left, top + height, childWidth + left - childHorizontalSpace, height + child.getMeasuredHeight() + top));
			}else {
				// 否则累加值到lineWidth， lineHeight取最大高度
				child.setTag(new Location(lineWidth + left, top + height, lineWidth + childWidth - childHorizontalSpace + left, height + child.getMeasuredHeight() + top));
				lineWidth += childWidth;
				lineHeight = Math.max(lineHeight, childHeight);
			}
		}

		width = Math.max(width, lineWidth) + getPaddingLeft() + getPaddingRight();
		height += lineHeight;
		sizeHeight += getPaddingTop() + getPaddingBottom();
		height += getPaddingTop() + getPaddingBottom();
		setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		for (int i = 0; i < count; i++){
			View child = getChildAt(i);
			if (child.getVisibility() == GONE){
				continue;
			}
			Location location = (Location) child.getTag();
			child.layout(location.left, location.top, location.right, location.bottom);
		}
	}


	/**
	 * 记录子控件的坐标
	 */
	public class Location{
		public int left;
		public int right;
		public int top;
		public int bottom;
		public Location(int left, int top, int right, int bottom){
			this.left = left;
			this.right = right;
			this.top = top;
			this.bottom = bottom;
		}
	}
}
