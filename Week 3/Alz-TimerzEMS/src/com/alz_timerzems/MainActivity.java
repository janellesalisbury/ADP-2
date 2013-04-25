package com.alz_timerzems;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//GLOBAL VARIABLES
	Button login;
	Button create;

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
				
		//LOG IN BUTTON
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 //try{
				//VERIFY USER ACCOUNT
//				AccountManager am = AccountManager.get(getApplicationContext());
//				Account[] accounts = am.getAccountsByType("com.google");
//				String email = accounts[0].name;
//				String password = accounts[0].type;
//				FileStuff.storeObjectFile(getBaseContext(), "username", email, false);
//				FileStuff.storeObjectFile(getBaseContext(), "password", password, false);

				Intent launch = new Intent(MainActivity.this, TabActivity.class);
				startActivity(launch);
//				 }catch(Exception e){
//					Toast error =  Toast.makeText(getApplicationContext(), "Please Create an Account", Toast.LENGTH_SHORT);
//					error.show();
//				 }
			}	
		});
		
		//CREATE ACCOUNT BUTTON
		create = (Button) findViewById(R.id.createid);
		create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent create = new Intent(MainActivity.this, CreateAccount.class);
				startActivity(create);
				
			}
		});
	}
	

}
