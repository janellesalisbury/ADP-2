package com.alz_timerzems;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import lib.FileStuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//GLOBAL VARIABLES
	Button login;
	Button create;
	Button logout;
	EditText username;
	EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//CHECK FOR NETWORK CONNECTIVITY
		ConnectivityManager conmgr = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if(conmgr !=null && (conmgr.getNetworkInfo(1).isAvailable() == true) || (conmgr.getNetworkInfo(0).isAvailable() == true)){
			//do something
			Log.i("Network", "connection");
		}else{
			Toast toast = Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT);
			toast.show();
		}
		

		
		//INITIALIZE PARSE
		Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu"); 

			
				
		//SIGN UP
		login = (Button) findViewById(R.id.login);
		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseUser user = new ParseUser();
				user.setUsername(username.getText().toString());
				user.setPassword(password.getText().toString());
				
				user.signUpInBackground(new SignUpCallback() {
					
					@Override
					public void done(ParseException e) {
						if(e == null){
							Intent launch = new Intent(MainActivity.this, TabActivity.class);
							startActivity(launch);
						}else{
							Intent create = new Intent(MainActivity.this, CreateAccount.class);
							startActivity(create);
						}
						
					}
				});
			
			}
				
		});
		
		//LOG IN
		ParseUser.logInInBackground("my name", "my password", new LogInCallback() {
			
			@Override
			public void done(ParseUser user, ParseException e) {
				if(user !=null){
				Intent open = new Intent(MainActivity.this, TabActivity.class);
				startActivity(open);
				
			}else{
				Toast error = Toast.makeText(getApplicationContext(), "Account Not Found",Toast.LENGTH_SHORT);
				error.show();
			}
		}
	});
		
		
	
	}
}

	


