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

public class GroupOverviewAdapter extends BaseAdapter {

		private Activity activity;
		private ArrayList<HashMap<String, String>> data;
		private static LayoutInflater inflater=null;
//		public ImageLoader imageLoader; 

		public GroupOverviewAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
			activity = a;
			data=d;
			inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			imageLoader=new ImageLoader(activity.getApplicationContext());
		}

		public int getCount() {
			return data.size();
		}

		public Object getItem(int position) {
			return data.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(convertView==null){
				view = inflater.inflate(R.layout.list_row, null);
			}
			TextView groupName = (TextView)view.findViewById(R.id.groupName); // title
			TextView adminName = (TextView)view.findViewById(R.id.adminName); // artist name
//			ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image); // thumb image

			HashMap<String, String> group = new HashMap<String, String>();
			group = data.get(position);

			// Setting all values in listview
			groupName.setText(group.get("groupName"));
			adminName.setText(group.get("admin"));//song.get(CustomizedListView.KEY_ARTIST));
//			members.setText(song.get(CustomizedListView.KEY_DURATION));
//			imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
			return view;
		}
	}