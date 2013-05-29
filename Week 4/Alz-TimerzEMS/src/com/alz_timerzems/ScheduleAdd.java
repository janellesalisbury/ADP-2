package com.alz_timerzems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import com.parse.Parse;
import com.parse.ParseObject;
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
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ScheduleAdd extends Activity{
	//GLOBAL VARIABLES
	EditText _name;
	EditText _day;
	EditText _time;
	EditText _position;
	Button _saveInfo;
	Button _cancel;
	int imageNum = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedules_form);
		
		//INITIALIZE PARSE
				Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu"); 
				
				ActionBar actionBar = getActionBar();
				actionBar.setDisplayHomeAsUpEnabled(true);

				
				//Passed imformation
				_name = (EditText) findViewById(R.id.et_name);
				_day = (EditText) findViewById(R.id.et_day);
				_time = (EditText) findViewById(R.id.et_time);
				_position = (EditText) findViewById(R.id.et_pos);
				
				
				_saveInfo = (Button) findViewById(R.id.ok);
				_saveInfo.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						ParseObject savedEmployeeShift = new ParseObject("SavedEmployeeShift");
						savedEmployeeShift.put("row", "value");
						savedEmployeeShift.put("Name", _name.getText().toString());
						savedEmployeeShift.put("Day", _day.getText().toString());
						savedEmployeeShift.put("Time", _time.getText().toString());
						savedEmployeeShift.put("Position", _position.getText().toString());
						savedEmployeeShift.saveInBackground();
						
						//INFORMATION THAT WILL BE SENT
						Intent home = new Intent(ScheduleAdd.this, TabActivity.class);
						startActivity(home);
						
						
					}
				});
				
				_cancel = (Button) findViewById(R.id.nevermind);
				_cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent back = new Intent(ScheduleAdd.this, TabActivity.class);
						startActivity(back);
						
					}
				});
			
			}
			
			
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.activity_main, menu);
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
					Intent devInfo = new Intent(ScheduleAdd.this, DeveloperInfo.class);
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
					Intent logoff = new Intent(ScheduleAdd.this, MainActivity.class);
					startActivity(logoff);
					break;
				
				}
				return true;
			}		

	}


