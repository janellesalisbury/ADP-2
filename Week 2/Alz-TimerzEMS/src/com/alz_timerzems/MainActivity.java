package com.alz_timerzems;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//GLOBAL VARIABLES
	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//CHECK FOR NETWORK CONNECTIVITY
		ConnectivityManager conmgr = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if(conmgr !=null && (conmgr.getNetworkInfo(1).isAvailable() == true) || (conmgr.getNetworkInfo(0).isAvailable() == true)){
			//do something
		}else{
			Toast toast = Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT);
			toast.show();
		}
		
		//FOR TESTING PURPOSES TO MAKE SURE THE VIEW IS SET UP CORRECTLY WITH THE TABS
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent launch = new Intent(MainActivity.this, TabActivity.class);
				startActivity(launch);
				
			}
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
