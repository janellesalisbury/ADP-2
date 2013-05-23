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
	String trade;
	String newShift;
	TextView srName;
	TextView srTrade;
	TextView srNew;
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
		trade = intent.getStringExtra("DetailsTrade");
		newShift = intent.getStringExtra("DetailsNewShift");
		
		
		//FIND TEXTVIEWS
		srName = (TextView) findViewById(R.id.srNameTV);
		srTrade = (TextView) findViewById(R.id.srTradeTV);
		srNew = (TextView) findViewById(R.id.newShiftTV);
		
		
		//SET TEXT
		srName.setText(name);
		srTrade.setText(trade);
		srNew.setText(newShift);
	
		
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
