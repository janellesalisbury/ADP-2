package com.alz_timerzems;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EmployeeAdd extends Activity{
	String _name;
	String _phone;
	String _address;
	String _mobile;
	String _email;
	Button saveInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_activity);
		
		//Passed imformation
		Intent employeeInfo = getIntent();
		_name = employeeInfo.getStringExtra("Name");
		_phone = employeeInfo.getStringExtra("Phone");
		_address = employeeInfo.getStringExtra("Address");
		_mobile = employeeInfo.getStringExtra("Mobile");
		_email = employeeInfo.getStringExtra("Email");
		
		saveInfo = (Button) findViewById(R.id.save);
		saveInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseObject savedEmployee = new ParseObject("SavedEmployee");
				savedEmployee.put("row", "value");
				savedEmployee.put("Name", _name);
				savedEmployee.put("Phone", _phone);
				savedEmployee.put("Address", _address);
				savedEmployee.put("Mobile", _mobile);
				savedEmployee.put("Email", _email);
				
				
			}
		});
	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}


