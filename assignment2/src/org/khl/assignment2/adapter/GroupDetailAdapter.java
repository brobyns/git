package org.khl.assignment2.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import org.khl.assignment2.R;
import org.khl.assignment2.R.id;
import org.khl.assignment2.R.layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupDetailAdapter extends BaseAdapter {

		private Activity activity;
		private ArrayList<HashMap<String, String>> data;
		private static LayoutInflater inflater=null;
//		public ImageLoader imageLoader; 

		public GroupDetailAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
			activity = a;
			data=d;
			inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			imageLoader=new ImageLoader(activity.getApplicationContext());
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

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(convertView==null){
				view = inflater.inflate(R.layout.group_detail_row, null);
			}
			TextView memberName = (TextView)view.findViewById(R.id.memberName); // member
			TextView amount = (TextView)view.findViewById(R.id.amount); // who owes who how much

			HashMap<String, String> member = data.get(position);

			// Setting all values in listview
			memberName.setText(member.get("memberName"));
			amount.setText(member.get("amount"));//song.get(CustomizedListView.KEY_ARTIST));
//			members.setText(song.get(CustomizedListView.KEY_DURATION));
//			imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
			return view;
		}
	}