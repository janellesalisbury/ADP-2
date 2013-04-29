package com.alz_timerzems;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends Activity{
	
	Button done;
	Button cancel;
	EditText username;
	EditText password;
	EditText email;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account);
		
		//INITIALIZE PARSE
		Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		//BUTTONS/EDITTEXT FIELDS
		done = (Button) findViewById(R.id.done);
		cancel = (Button) findViewById(R.id.cancel);
		username = (EditText) findViewById(R.id.usernamenew);
		password = (EditText) findViewById(R.id.pswrdnew);
		email = (EditText) findViewById(R.id.editText1);
		
		//SIGN UP FOR NEW ACCOUNT
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseUser user = new ParseUser();
				user.setUsername(username.getText().toString());
				user.setPassword(password.getText().toString());
				user.setEmail(email.getText().toString());
				
				user.signUpInBackground(new SignUpCallback() {
					
					@Override
					public void done(ParseException e) {
						if(e == null){
							Intent launch = new Intent(CreateAccount.this, TabActivity.class);
							startActivity(launch);
						}else{
							//Failure to register
						}
						
					}
				});
				
			}
		});

		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent login = new Intent(CreateAccount.this, MainActivity.class);
				startActivity(login);
				
			}
		});
 
	}

}
