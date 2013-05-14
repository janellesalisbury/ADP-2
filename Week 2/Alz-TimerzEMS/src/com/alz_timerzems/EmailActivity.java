package com.alz_timerzems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends Activity{
	//GLOBAL VARIABLES
	Button send;
	EditText to;
	EditText subject;
	EditText message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		
		//Buttons/EditText setup
		send = (Button) findViewById(R.id.buttonSend);
		to = (EditText) findViewById(R.id.editTextTo);
		subject = (EditText) findViewById(R.id.editTextSubject);
		message = (EditText) findViewById(R.id.editTextMessage);
		
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String toWho = to.getText().toString();
				String what = subject.getText().toString();
				String body = message.getText().toString();
				
				Intent emailIntent = new Intent(Intent.ACTION_SEND);

				/* Fill it with Data */
				emailIntent.setType("message/rfc822");
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ toWho});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, new String[]{ what});
				emailIntent.putExtra(Intent.EXTRA_TEXT, new String[]{ body});
				
				//need this to prompts email client only
				emailIntent.setType("message/rfc822");
	 
				/* Send it off to the Activity-Chooser */
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
				
			}
		});
		
	}

}
