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
	String time;
	String day;
	String position;
	TextView nameTV;
	TextView timeTV;
	TextView dayTV;
	TextView positionTV;
	Button back;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedules_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		//GET PASSED INFO
		Intent i = new Intent();
		name = i.getStringExtra("DetailsName2");
		time = i.getStringExtra("DetailsTime2");
		day = i.getStringExtra("DetailsDay2");
		position = i.getStringExtra("DetailsPosition2");
		
		//FIND TEXTVIEWS
		nameTV = (TextView) findViewById(R.id.schedTV);
		timeTV = (TextView) findViewById(R.id.timeTV);
		dayTV = (TextView) findViewById(R.id.shiftTV);
		positionTV = (TextView) findViewById(R.id.posTV);
		
		//SET TEXT
		nameTV.setText(name);
		timeTV.setText(time);
		dayTV.setText(day);
		positionTV.setText(position);
		
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent back = new Intent(ScheduleDetails.this, TabActivity.class);
				startActivity(back);
				
			}
		});
				
		
		
		
	}

}
