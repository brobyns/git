package org.khl.assignment2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.khl.assignment2.adapter.GroupOverviewAdapter;

import model.Group;
import model.Facade.Facade;
import model.Facade.FacadeImpl;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


public class MainActivity extends ListActivity implements OnItemClickListener{

	private Facade facade;
	private LinearLayout groupsLayout;
	private ListView listView;
	private Button createBtn;
	private List<Group> groups;
	private String groupSelected;
	private GroupOverviewAdapter overviewAdapt;
	public static final String GROUP_KEY = "group";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeComponents();
		facade = new FacadeImpl();
		//        readDB();
	}

	private void readDB(){
		groups = facade.getCurrentMember().getGroups();
	}

	private void initializeComponents(){
		groupsLayout = (LinearLayout)findViewById(R.id.groupsLayout);
		createBtn = (Button)findViewById(R.id.createBtn);
		listView = (ListView)findViewById(android.R.id.list);
		ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map  = new HashMap<String,String>();
		map.put("groupName", "group1");
		map.put("admin", "Bram");
		HashMap<String,String> map2  = new HashMap<String,String>();
		map2.put("groupName", "group2");
		map2.put("admin", "user2");
		data.add(map);
		data.add(map2);
		overviewAdapt=new GroupOverviewAdapter(this, data);
		listView.setAdapter(overviewAdapt);
		listView.setOnItemClickListener(this);
	}

	public void createGroup(View v){
			Intent intent = new Intent(MainActivity.this, CreateGroupActivity.class);
			//	intent.putExtra(name, value)
			startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.menu_groups){
			return true;
		}else if(id == R.id.menu_create_group){
			Intent intent = new Intent(MainActivity.this, CreateGroupActivity.class);
			startActivity(intent);
			return true;
		}else if(id == R.id.menu_invitations){
			return true;
		}else if (id == R.id.action_settings) {
			return true;
		} 
		return super.onOptionsItemSelected(item);
	}

	public void finish(){
		super.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		//Group group = parent.getItemAtPosition(pos);
		Intent intent = new Intent(MainActivity.this, GroupDetailActivity.class);
		startActivity(intent);
	}
}
