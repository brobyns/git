package org.khl.assignment2.adapter;

import java.util.List;

import org.khl.assignment2.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import model.Expense;
import model.Facade.Facade;

public class MemberDetailAdapter extends BaseAdapter {

	private Activity activity;
	private List<Expense> expenses;
	private static LayoutInflater inflater=null;
	private Facade facade;
	private int memberid;

	public MemberDetailAdapter(Activity a, List<Expense> expenses,Facade facade, int memberid){
		activity = a;
		this.memberid = memberid;
		this.facade = facade;
		this.expenses = expenses;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//			imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	@Override
	public int getCount() {
		return expenses.size();
	}

	@Override
	public Object getItem(int position) {
		return expenses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return expenses.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null){
			view = inflater.inflate(R.layout.group_detail_row, null);
		}
		TextView paid = (TextView)view.findViewById(R.id.memberName);
		TextView amountText = (TextView)view.findViewById(R.id.amount);

		double amount = facade.getAmount(memberid);

		//set values in listview
		String amounts = null;
		if (amount < 0){
			amounts = "You owe" + amount;
			paid.setTextColor(Color.RED);
			amountText.setTextColor(Color.RED);
		}else if(amount > 0){
			amounts = "Owes you" + amount;
			paid.setTextColor(Color.GREEN);
			amountText.setTextColor(Color.GREEN);
		}else{
			amounts = "settled";
		}
		amountText.setText(amounts);
		return view;
	}

}
