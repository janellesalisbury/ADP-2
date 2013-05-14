package com.alz_timerzems;

import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.widget.TextView;

public class ShiftRequestDetails extends Activity{
	//GLOBAL VARIABLES
	String name;
	String date;
	String time;
	String excuse;
	TextView srName;
	TextView srDate;
	TextView srTime;
	TextView srExcuse;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requests_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		//GET PASSED INFO USING INTENT
		Intent intent = getIntent();
		name = intent.getStringExtra("DetailsName");
		date = intent.getStringExtra("DetailsDate");
		time = intent.getStringExtra("DetailsTime");
		excuse = intent.getStringExtra("DetailsExcuse");
		
		//FIND TEXTVIEWS
		srName = (TextView) findViewById(R.id.srNameTV);
		srDate = (TextView) findViewById(R.id.srDateTV);
		srTime = (TextView) findViewById(R.id.srTimeTV);
		srExcuse = (TextView) findViewById(R.id.srExcuseTV);
		
		//SET TEXT
		srName.setText(name);
		srDate.setText(date);
		srTime.setText(time);
		srExcuse.setText(excuse);
		
				
		
	
	}
}
