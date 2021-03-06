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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RequestForm extends Activity{
	EditText name;
	EditText trade;
	EditText newShift;
	EditText requested;
	int imageNum;
	Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestform_activity);
		

		//INITIALIZE PARSE
		Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu"); 
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

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
			Intent back = new Intent(RequestForm.this, TabActivity.class);
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
			Intent devInfo = new Intent(RequestForm.this, DeveloperInfo.class);
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
			Intent logoff = new Intent(RequestForm.this, MainActivity.class);
			startActivity(logoff);
			break;
		
		}
		return true;
	}	

}
