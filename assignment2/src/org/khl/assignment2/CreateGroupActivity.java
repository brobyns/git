package org.khl.assignment2;

import java.util.ArrayList;
import java.util.List;

import org.khl.assignment2.adapter.AddedMemberAdapter;

import model.Member;
import model.Facade.Facade;
import model.Facade.FacadeImpl;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

public class CreateGroupActivity extends ListActivity implements OnItemSelectedListener{
	private LinearLayout groupsLayout;
	private Facade facade = new FacadeImpl();
	private EditText groupNameField, emailField;
	private Button createBtn, cancelBtn;
	private Spinner spinner;
	private List<Member> members;
	private List<Member> membersInvited = new ArrayList<Member>();
	private ListView addMembersList;
	private Member selectedMember, memberToDelete;
	private AddedMemberAdapter addedMemberAdapt;
	private ArrayAdapter<Member> memberAdapt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_group);
		getMembers();
		initializeComponents();
	}

	private void getMembers(){
		members = facade.getAllMembers();
	}

	public void delete(View v){
		membersInvited.remove(memberToDelete);
		addedMemberAdapt.notifyDataSetChanged();
	}

	private void initializeComponents(){
		createBtn = (Button)findViewById(R.id.createBtn);
		cancelBtn = (Button)findViewById(R.id.cancelBtn);
		groupsLayout = (LinearLayout)findViewById(R.id.groupsLayout);
		groupNameField = (EditText)findViewById(R.id.groupName);
		emailField = (EditText)findViewById(R.id.email);
		spinner = (Spinner) findViewById(R.id.spinner);
		memberAdapt = new ArrayAdapter<Member>(this,  android.R.layout.simple_spinner_item, members);
		memberAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(memberAdapt);
		spinner.setOnItemSelectedListener(this); 
		addedMemberAdapt = new AddedMemberAdapter(this,membersInvited);
		addMembersList = (ListView)findViewById(android.R.id.list);
		addMembersList.setAdapter(addedMemberAdapt);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		selectedMember = (Member)parent.getItemAtPosition(pos);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void createGroup(View v){
		String groupName = groupNameField.getText().toString();
		if(isValidGroupName(groupName)){
			facade.createGroup(groupName, membersInvited);
		}
	}

	public void invite(View v){
		if(selectedMember != null){
			membersInvited.add(selectedMember);
			members.remove(selectedMember);
			if(members.isEmpty()){
				selectedMember = null;
				spinner.setEnabled(false);
			}
			refreshData();
		}
		String email = emailField.getText().toString();
		if(isValidEmail(email)){
			//implement
		}
	}

	private void refreshData(){
		addedMemberAdapt.notifyDataSetChanged();
		memberAdapt.notifyDataSetChanged();
		spinner.setAdapter(memberAdapt);
	}

	public void cancel(View v){
		finish();
	}

	private boolean isValidEmail(String email){
		//implement
		return true;
	}

	private boolean isValidGroupName(String groupName){
		//implement
		return true;
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
			Intent intent = new Intent(CreateGroupActivity.this, MainActivity.class);
			startActivity(intent);
			return true;
		}else if(id == R.id.menu_create_group){
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
}
