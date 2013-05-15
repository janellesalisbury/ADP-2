package com.alz_timerzems;

import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScheduleDetails extends Activity{
	//GLOBAL VARIABLES
	String name;
	String day;
	String time;
	String position;
	TextView nameTV;
	TextView dayTV;
	TextView timeTV;
	TextView positionTV;
	Button back;
	

	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedules_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		//GET PASSED INFO USING INTENT
		Intent schedules = new Intent();
		name = schedules.getStringExtra("DetailsName");
		day = schedules.getStringExtra("DetailsDay");
		time = schedules.getStringExtra("DetailsTime");
		position = schedules.getStringExtra("DetailsPosition");
		
		//FIND TEXTVIEWS
		nameTV = (TextView) findViewById(R.id.nametv);
		dayTV = (TextView) findViewById(R.id.daytv);
		timeTV = (TextView) findViewById(R.id.timetv);
		positionTV = (TextView) findViewById(R.id.postv);
		
		//SET TEXT
		nameTV.setText(name);
		dayTV.setText(day);
		timeTV.setText(time);
		positionTV.setText(position);
		
		back = (Button) findViewById(R.id.back2);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent back = new Intent(ScheduleDetails.this, TabActivity.class);
				startActivity(back);
				
			}
		});
		
				
		
	}

}
