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

public class ScheduleDetails extends Activity{
	//GLOBAL VARIABLES
	String name;
	String time;
	String day;
	String position;
	TextView nameTV;
	TextView timeTV;
	TextView dayTV;
	TextView positionTV;
	Button back;
	int imageNum = 0;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedules_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//GET PASSED INFO
		Intent i = getIntent();
		name = i.getStringExtra("DetailsName2");
		time = i.getStringExtra("DetailsTime2");
		day = i.getStringExtra("DetailsDay2");
		position = i.getStringExtra("DetailsPosition2");
		
		//FIND TEXTVIEWS
		nameTV = (TextView) findViewById(R.id.schedTV);
		timeTV = (TextView) findViewById(R.id.timeTV);
		dayTV = (TextView) findViewById(R.id.shiftTV);
		positionTV = (TextView) findViewById(R.id.posTV);
		
		//SET TEXT
		nameTV.setText(name);
		timeTV.setText(time);
		dayTV.setText(day);
		positionTV.setText(position);
		
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent back = new Intent(ScheduleDetails.this, TabActivity.class);
				startActivity(back);
				
			}
		});
	
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
				Intent devInfo = new Intent(ScheduleDetails.this, DeveloperInfo.class);
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
				Intent logoff = new Intent(ScheduleDetails.this, MainActivity.class);
				startActivity(logoff);
				break;
			
			}
			return true;
			
		}

}
