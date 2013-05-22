package com.alz_timerzems;

import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RequestsDetails extends Activity{
	//GLOBAL VARIABLES
	String name;
	String date;
	String time;
	String excuse;
	TextView srName;
	TextView srDate;
	TextView srTime;
	TextView srExcuse;
	Button back;
	
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
		
		//BACK BUTTON
		back = (Button) findViewById(R.id.srBack);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent back = new Intent(RequestsDetails.this, TabActivity.class);
				startActivity(back);
				
			}
		});
				
		
	
	}
}
