package com.alz_timerzems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import com.parse.Parse;
import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RequestsDetails extends Activity{
	//GLOBAL VARIABLES
	String name;
	String trade;
	String newShift;
	String requestedShift;
	TextView srName;
	TextView srTrade;
	TextView srNewShift;
	TextView srRequested;
	int imageNum;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requests_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//GET PASSED INFO USING INTENT
		Intent intent = getIntent();
		name = intent.getStringExtra("DetailsName");
		trade = intent.getStringExtra("DetailsTrade");
		newShift = intent.getStringExtra("DetailsNewShift");
		requestedShift = intent.getStringExtra("DetailsRequested");
		
		
		//FIND TEXTVIEWS
		srName = (TextView) findViewById(R.id.srNameTV);
		srTrade = (TextView) findViewById(R.id.srTradeTV);
		srNewShift = (TextView) findViewById(R.id.srNewShiftTV);
		srRequested = (TextView) findViewById(R.id.srRequested);
		
		//SET TEXT
		srName.setText(name);
		srTrade.setText(trade);
		srNewShift.setText(newShift);
		srRequested.setText(requestedShift);
		
			
	}
	

	//ACTION BAR ITEMS
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
		
	}
	
	//ACTION BAR FUNCTIONALITY
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case android.R.id.home:
			Intent back = new Intent(RequestsDetails.this, TabActivity.class);
			startActivity(back);
			break;
		case R.id.menu_camera:
			Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			//SAVE THE IMAGE TO STORAGE
			File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
			if (!imagesFolder.exists()) {
				imagesFolder.mkdirs();
				}
				else {
	        String fileName = "image_" + String.valueOf(imageNum) + ".jpg";
	        File output = new File(imagesFolder, fileName);
	        while (output.exists()){
	            imageNum++;
	            fileName = "image_" + String.valueOf(imageNum) + ".jpg";
	            output = new File(imagesFolder, fileName);
	        }
	        Uri uriSavedImage = Uri.fromFile(output);
	        //PASS THE IMAGE TO STORAGE
	        camera.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

	        OutputStream imageFileOS;
	        try {
	            imageFileOS = getContentResolver().openOutputStream(uriSavedImage);
	            imageFileOS.write(0);
	            imageFileOS.flush();
	            imageFileOS.close();

	         

	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			startActivityForResult(camera, 0);
		}
			break;
		case R.id.menu_info:
			Intent devInfo = new Intent(RequestsDetails.this, DeveloperInfo.class);
			startActivity(devInfo);
			break;
		case R.id.menu_settings:
			Intent settings = new Intent(Settings.ACTION_SETTINGS);
			settings.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(settings);
			break;
		case R.id.logout:
			ParseUser.logOut();
			@SuppressWarnings("unused")
			ParseUser currentUser = ParseUser.getCurrentUser();
			Intent logoff = new Intent(RequestsDetails.this, MainActivity.class);
			startActivity(logoff);
			break;
		
		}
		return true;
	}	
}
