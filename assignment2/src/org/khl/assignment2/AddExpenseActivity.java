package org.khl.assignment2;

import model.Facade.Facade;
import model.Facade.FacadeImpl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddExpenseActivity extends Activity {
	private Facade facade = new FacadeImpl();
	private EditText expenseType, description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		initializeComponents();
	}

	private void initializeComponents(){
		expenseType = (EditText)findViewById(R.id.type);
		description = (EditText)findViewById(R.id.description);
	}

	public void addExpense(View v){

	}

	public void cancel(View v){
		finish();
	}
}