package com.alz_timerzems;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RequestForm extends Activity{
	EditText name;
	EditText trade;
	EditText newShift;
	EditText requested;
	
	Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestform_activity);
		

		//INITIALIZE PARSE
		Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu"); 

		//PASSED INFO
		name = (EditText) findViewById(R.id.name_et);
		trade = (EditText) findViewById(R.id.trade);
		newShift = (EditText) findViewById(R.id.date_et);
		requested = (EditText) findViewById(R.id.shiftRequested);
		
		
		
		submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseObject shiftRequest = new ParseObject("ShiftRequest");
				shiftRequest.put("row", "value");
				shiftRequest.put("Name", name.getText().toString());
				shiftRequest.put("Trade", trade.getText().toString());
				shiftRequest.put("For", newShift.getText().toString());
				shiftRequest.put("Requested", requested.getText().toString());
				
				shiftRequest.saveInBackground();
				
				//INFORMATION THAT WILL BE SENT
				Intent home = new Intent(RequestForm.this, TabActivity.class);
				startActivity(home);
				
			}
		});
	}

}
