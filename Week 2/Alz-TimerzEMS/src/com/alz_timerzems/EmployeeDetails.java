package com.alz_timerzems;

import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeDetails extends Activity{
	//GLOBAL VARIABLES
	String _passedName;
	String _passedAddress;
	String _passedPhone;
	String _passedMobile;
	String _passedEmail;
	TextView name;
	TextView address;
	TextView phone;
	TextView mobile;
	TextView email;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		
		//GET THE PASSED INFO USING AN INTENT
		Intent i = getIntent();
		_passedName = i.getStringExtra("DetailName");
		_passedAddress = i.getStringExtra("DetailAddress");
		_passedPhone = i.getStringExtra("DetailPhone");
		_passedMobile = i.getStringExtra("DetailMobile");
		_passedEmail = i.getStringExtra("DetailEmail");
		
		//FIND THE TEXTVIEWS
		name = (TextView) findViewById(R.id.employeeDetailName);
		address = (TextView) findViewById(R.id.employeeDetailAddress);
		phone = (TextView) findViewById(R.id.employeeDetailsPhone);
		mobile = (TextView) findViewById(R.id.employeeDetailMobile);
		email = (TextView) findViewById(R.id.employeeDetailEmail);
		
		//SET THE TEXT FOR EACH TEXTVIEW USING PASSED INFO
		name.setText(_passedName);
		address.setText(_passedAddress);
		phone.setText(_passedPhone);
		mobile.setText(_passedMobile);
		email.setText(_passedEmail);
		
	}
	

}
