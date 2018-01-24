package com.navyliu.customview.Dialog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017-12-20.
 */

public class SpinnerBean implements Parcelable{
	private String itemStr = "", itemId = "", itemOther = "";
	private int styleType = 0; // 0:一般的列表， 1：radio列表


	public String getItemStr() {
		return itemStr;
	}

	public SpinnerBean setItemStr(String itemStr) {
		this.itemStr = itemStr;
		return this;
	}

	public String getItemId() {
		return itemId;
	}

	public SpinnerBean setItemId(String itemId) {
		this.itemId = itemId;
		return this;
	}

	public String getItemOther() {
		return itemOther;
	}

	public SpinnerBean setItemOther(String itemOther) {
		this.itemOther = itemOther;
		return this;
	}

	public int getStyleType() {
		return styleType;
	}

	public SpinnerBean setStyleType(int styleType) {
		this.styleType = styleType;
		return this;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(itemId);
		dest.writeString(itemStr);
		dest.writeString(itemOther);
	}

	public static final Creator<SpinnerBean> CREATOR = new Creator<SpinnerBean>() {
		@Override
		public SpinnerBean createFromParcel(Parcel source) {
			SpinnerBean spinnerBean = new SpinnerBean();
			spinnerBean.itemId = source.readString();
			spinnerBean.itemStr =source.readString();
			spinnerBean.itemOther = source.readString();
			return spinnerBean;
		}

		@Override
		public SpinnerBean[] newArray(int size) {
			return new SpinnerBean[size];
		}
	};
}
