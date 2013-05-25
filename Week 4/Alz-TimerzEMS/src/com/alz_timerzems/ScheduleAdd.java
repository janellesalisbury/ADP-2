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

public class ScheduleAdd extends Activity{
	//GLOBAL VARIABLES
	EditText _name;
	EditText _day;
	EditText _time;
	EditText _position;
	Button _saveInfo;
	Button _cancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedules_form);
		
		//INITIALIZE PARSE
				Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu"); 

				
				//Passed imformation
				_name = (EditText) findViewById(R.id.et_name);
				_day = (EditText) findViewById(R.id.et_day);
				_time = (EditText) findViewById(R.id.et_time);
				_position = (EditText) findViewById(R.id.et_pos);
				
				
				_saveInfo = (Button) findViewById(R.id.ok);
				_saveInfo.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						ParseObject savedEmployeeShift = new ParseObject("SavedEmployeeShift");
						savedEmployeeShift.put("row", "value");
						savedEmployeeShift.put("Name", _name.getText().toString());
						savedEmployeeShift.put("Day", _day.getText().toString());
						savedEmployeeShift.put("Time", _time.getText().toString());
						savedEmployeeShift.put("Position", _position.getText().toString());
						savedEmployeeShift.saveInBackground();
						
						//INFORMATION THAT WILL BE SENT
						Intent home = new Intent(ScheduleAdd.this, TabActivity.class);
						startActivity(home);
						
						
					}
				});
				
				_cancel = (Button) findViewById(R.id.nevermind);
				_cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent back = new Intent(ScheduleAdd.this, TabActivity.class);
						startActivity(back);
						
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


