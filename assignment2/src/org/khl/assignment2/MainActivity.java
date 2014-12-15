package org.khl.assignment2;

import java.util.List;
import java.util.Properties;

import org.khl.assignment2.adapter.GroupOverviewAdapter;

import service.AssetsPropertyReader;
import service.FetchData;

import model.Group;
import model.Member;
import model.Settings;
import model.Facade.Facade;
import model.Facade.FacadeImpl;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
	private FetchData fetchData;
	public static final String GROUP_ID = "groupid";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadSettings();
		fetchNewData();
		String dbWriterType = (fetchData.isConnected()? "OnlineDBWriter": "OfflineDBWriter");
		facade = new FacadeImpl(dbWriterType);
		initializeComponents();
	}
	
	private void loadSettings(){
		AssetsPropertyReader assetsPropertyReader;
	    assetsPropertyReader = new AssetsPropertyReader(this);
	    Properties p = assetsPropertyReader.getProperties("settings.properties");
	    p.setProperty("id", "1");
	    String idString = p.getProperty("id");
	    int id = Integer.parseInt(idString);
	    Member currentMember = new Member(id,p.getProperty("firstname"), p.getProperty("lastname"), p.getProperty("email"));
	    Settings settings = Settings.getInstance();
	    settings.setCurrentMember(currentMember);
	    settings.setCurrency(p.getProperty(p.getProperty("currency")));
	}


	private void fetchNewData(){
		fetchData = new FetchData(this.getApplicationContext());
		fetchData.execute();
	}

	private void initializeComponents() {
		groupsLayout = (LinearLayout)findViewById(R.id.groupsLayout);
		createBtn = (Button)findViewById(R.id.createBtn);
		listView = (ListView)findViewById(android.R.id.list);
		overviewAdapt=new GroupOverviewAdapter(this, facade.getAllGroups(), facade);
		listView.setAdapter(overviewAdapt);
		listView.setOnItemClickListener(this);
	}

	public void createGroup(View v){
			Intent intent = new Intent(MainActivity.this, CreateGroupActivity.class);
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
			Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(intent);
			return true;
		} 
		return super.onOptionsItemSelected(item);
	}

	public void finish(){
		super.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Group group = (Group)parent.getItemAtPosition(pos);
		Intent intent = new Intent(MainActivity.this, GroupDetailActivity.class);
		intent.putExtra(GROUP_ID, group.getId());
		startActivity(intent);
	}
}
