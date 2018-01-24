package com.navyliu.customview.Dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.navyliu.customview.R;
import com.navyliu.customview.glide.GlideApp;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-12-20.
 */

public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerAdapter.ViewHolder> {
	private ArrayList<SpinnerBean> mList = new ArrayList<SpinnerBean>();
	private OnRecyclerItemClickListener mOnRecyclerItemClickListener = null;
	private Context mContext;
	private String currItemId;

	public SpinnerAdapter(Context context, ArrayList<SpinnerBean> mList) {
		this.mList = mList;
		this.mContext = context;
	}

	public SpinnerAdapter(Context context, ArrayList<SpinnerBean> mList, String itemId) {
		this.mList = mList;
		this.mContext = context;
		this.currItemId = itemId;
	}


	// 创建新的view， 被LayoutManager所调用
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, null);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}


	// 将数据与界面进行绑定操作
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		if (mList.get(position).getStyleType() == 0) {
			holder.spinnerImg.setVisibility(View.GONE);
		} else if (mList.get(position).getStyleType() == 1) {
			holder.spinnerImg.setVisibility(View.VISIBLE);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
			holder.spinnerTxt.setText(Html.fromHtml(mList.get(position).getItemStr(), Html.FROM_HTML_MODE_LEGACY));
		}else{
			holder.spinnerTxt.setText(Html.fromHtml(mList.get(position).getItemStr()));
		}
		if (currItemId.equals(mList.get(position).getItemId())) {
			holder.spinnerTxt.setTextColor(Color.RED);
			GlideApp.with(mContext).load(R.mipmap.icon_radio_hover).into(holder.spinnerImg);
		} else {
			holder.spinnerTxt.setTextColor(Color.BLACK);
			GlideApp.with(mContext).load(R.mipmap.icon_radio).into(holder.spinnerImg);
		}
		holder.spinnerRlayout.setTag(R.id.rlayout_spinner, mList.get(position));
		holder.spinnerRlayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnRecyclerItemClickListener.onItemClick(holder.spinnerRlayout, position);
			}
		});
	}


	// 获取数据的数量
	@Override
	public int getItemCount() {
		return mList.size();
	}


	public void addItem(SpinnerBean content, int position) {
		mList.add(position, content);
		notifyItemInserted(position); // attention
	}

	public void removeItem(SpinnerBean model) {
		int position = mList.indexOf(model);
		mList.remove(position);
		notifyItemRemoved(position);
	}

	public void refesh() {
		notifyDataSetChanged();
	}

	// 自定义的viewholder，持有每个item的所有界面元素
	public static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView spinnerTxt;
		private ImageView spinnerImg;
		private RelativeLayout spinnerRlayout;

		public ViewHolder(View view) {
			super(view);
			spinnerTxt = (TextView) view.findViewById(R.id.txt_spinner);
			spinnerImg = (ImageView) view.findViewById(R.id.img_spinner);
			spinnerRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_spinner);
		}
	}

	public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
		this.mOnRecyclerItemClickListener = listener;
	}

	public interface OnRecyclerItemClickListener {
		void onItemClick(View view, int position);
	}
}
