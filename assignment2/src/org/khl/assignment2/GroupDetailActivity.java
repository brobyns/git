package org.khl.assignment2;

import java.util.ArrayList;
import java.util.HashMap;

import org.khl.assignment2.adapter.GroupDetailAdapter;

import model.Facade.Facade;
import model.Facade.FacadeImpl;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GroupDetailActivity extends ListActivity implements OnItemClickListener{
	private TextView memberName;
	private Facade facade = new FacadeImpl();
	private ListView listView;
	private GroupDetailAdapter detailAdapt;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        initializeComponents();
    }
	
	private void initializeComponents(){
		memberName = (TextView)findViewById(R.id.memberName);
		listView = (ListView)findViewById(android.R.id.list);
		ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map  = new HashMap<String,String>();
		map.put("memberName", "bram");
		map.put("amount", "22.90");
		HashMap<String,String> map2  = new HashMap<String,String>();
		map2.put("memberName", "user2");
		map2.put("amount", "10.00");
		data.add(map);
		data.add(map2);
		detailAdapt=new GroupDetailAdapter(this, data);
		listView.setAdapter(detailAdapt);
	}
	
	public void settlePayments(View v){
		facade.settlePayments();
	}
	
	public void manageGroup(View v){
		
	}
	
	public void addExpense(View v){
		Intent intent = new Intent(GroupDetailActivity.this, AddExpenseActivity.class);
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
			Intent intent = new Intent(GroupDetailActivity.this, MainActivity.class);
			startActivity(intent);
			return true;
		}else if(id == R.id.menu_create_group){
			Intent intent = new Intent(GroupDetailActivity.this, CreateGroupActivity.class);
			startActivity(intent);
			return true;
		}else if(id == R.id.menu_invitations){
			return true;
		}else if (id == R.id.action_settings) {
			return true;
		} 
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	
}