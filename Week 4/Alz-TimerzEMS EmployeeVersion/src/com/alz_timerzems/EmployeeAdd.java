package com.alz_timerzems;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EmployeeAdd extends Activity{
	EditText _name;
	EditText _phone;
	EditText _address;
	EditText _mobile;
	EditText _email;
	Button saveInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_activity);
		
		//INITIALIZE PARSE
		Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu"); 

		
		//Passed imformation
		_name = (EditText) findViewById(R.id.editText1);
		_phone = (EditText) findViewById(R.id.editText2);
		_address = (EditText) findViewById(R.id.editText3);
		_mobile = (EditText) findViewById(R.id.editText4);
		_email = (EditText) findViewById(R.id.editText5);
		
		saveInfo = (Button) findViewById(R.id.save);
		saveInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseObject savedEmployee = new ParseObject("SavedEmployee");
				savedEmployee.put("row", "value");
				savedEmployee.put("Name", _name.getText().toString());
				savedEmployee.put("Phone", _phone.getText().toString());
				savedEmployee.put("Address", _address.getText().toString());
				savedEmployee.put("Mobile", _mobile.getText().toString());
				savedEmployee.put("Email", _email.getText().toString());
				savedEmployee.saveInBackground();
				
				//INFORMATION THAT WILL BE SENT
				Intent home = new Intent(EmployeeAdd.this, TabActivity.class);
				startActivity(home);
				
				
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


