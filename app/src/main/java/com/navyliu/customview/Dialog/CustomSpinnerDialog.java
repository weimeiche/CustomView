package com.navyliu.customview.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.navyliu.customview.R;
import com.navyliu.customview.constants.TimeConstants;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-12-20.
 */

public class CustomSpinnerDialog extends Dialog implements View.OnClickListener{

	private TextView titleTxt;
	private Button closeBtn;
	private RecyclerView spinnerRecycler;

	private CustomSpinnerDialog dialog;
	private Context mContext;
	private ArrayList<SpinnerBean> sList = new ArrayList<SpinnerBean>();
	private SpinnerAdapter spinnerAdapter;
	private String currItemId = "";
	private SpannableStringBuilder titleSpanStr = (new SpanUtils().create().append("请选择"));
	private SpinnerAdapter.OnRecyclerItemClickListener onRecyclerItemClickListener = null;

	public CustomSpinnerDialog(@NonNull Context context, ArrayList<SpinnerBean> list) {
		super(context, R.style.loading);
		this.setCanceledOnTouchOutside(false);
		this.getWindow().setBackgroundDrawableResource(R.color.transparent);
		this.mContext = context;
		this.sList = list;
		this.show();
		dialog = this;
	}


	public CustomSpinnerDialog setCurrItemId(String itemId) {
		this.currItemId = itemId;
		return this;
	}

	public CustomSpinnerDialog setDialogTitle(SpannableStringBuilder titleStr) {
		this.titleSpanStr = titleStr;
		return this;
	}

	public CustomSpinnerDialog setOnItemClick(SpinnerAdapter.OnRecyclerItemClickListener listener){
		if (listener != null){
			onRecyclerItemClickListener = listener;
		}
		return this;
	}


	public void execute() {
		LinearLayoutManager layoutManager =  new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
		spinnerRecycler.setLayoutManager(layoutManager);
		spinnerRecycler.setHasFixedSize(true);
		spinnerAdapter = new SpinnerAdapter(mContext, sList, currItemId);
		spinnerRecycler.setAdapter(spinnerAdapter);
		spinnerAdapter.notifyDataSetChanged();
		spinnerAdapter.setOnItemClickListener(onRecyclerItemClickListener);

		titleTxt.setText(titleSpanStr);
		closeBtn.setOnClickListener(this);
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawableResource(R.color.transparent);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_spinner);

		titleTxt = (TextView) this.findViewById(R.id.txt_title);
		closeBtn = (Button) this.findViewById(R.id.btn_close);
		spinnerRecycler = (RecyclerView) this.findViewById(R.id.recycler_spinner);
	}



	@Override
	public void onClick(View v) {
		if (TimeConstants.isFastDoubleClick()) {
			return;
		}
		switch (v.getId()) {
			case R.id.btn_close:
				this.dismiss();
				break;
		}
	}

}
