package org.khl.assignment2.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.khl.assignment2.R;
import org.khl.assignment2.R.id;
import org.khl.assignment2.R.layout;

import model.Member;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddedMemberAdapter extends BaseAdapter {

	private Activity activity;
	private List<Member> data;
	private static LayoutInflater inflater=null;
//	public ImageLoader imageLoader; 

	public AddedMemberAdapter(Activity a, List<Member> membersInvited) {
		activity = a;
		data=membersInvited;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		View view = convertView;
		if(convertView==null){
			view = inflater.inflate(R.layout.added_member_row, null);
		}
		TextView memberName = (TextView)view.findViewById(R.id.memberName); // member
		initializeDeleteBtn(position, view);
		Member member = data.get(position);
		memberName.setText(member.getFirstName());
		return view;
	}
	
	private void initializeDeleteBtn(int position, View view){
		ImageButton deleteBtn = (ImageButton)view.findViewById(R.id.deleteBtn);
		deleteBtn.setTag(position);
		deleteBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Integer index = (Integer) v.getTag();
	            //items.remove(index.intValue());  
	            data.remove(index.intValue());
	            notifyDataSetChanged();
	        }
	    });
	}

}
