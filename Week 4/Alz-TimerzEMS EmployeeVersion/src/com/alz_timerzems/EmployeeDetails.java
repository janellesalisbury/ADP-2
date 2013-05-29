package com.alz_timerzems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EmployeeDetails extends Activity{
	//GLOBAL VARIABLES
	String _passedName;
	String _passedAddress;
	String _passedPhone;
	String _passedMobile;
	String _passedEmail;
	TextView name;
	TextView address;
	TextView phone;
	TextView mobile;
	TextView email;
	Button call;
	Button message;
	int imageNum;
	
	final Context context = this;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		//GET THE PASSED INFO USING AN INTENT
		Intent i = getIntent();
		_passedName = i.getStringExtra("DetailName");
		_passedAddress = i.getStringExtra("DetailAddress");
		_passedPhone = i.getStringExtra("DetailPhone");
		_passedMobile = i.getStringExtra("DetailMobile");
		_passedEmail = i.getStringExtra("DetailEmail");
		
		//FIND THE TEXTVIEWS
		name = (TextView) findViewById(R.id.employeeDetailName);
		address = (TextView) findViewById(R.id.employeeDetailAddress);
		phone = (TextView) findViewById(R.id.employeeDetailsPhone);
		mobile = (TextView) findViewById(R.id.employeeDetailMobile);
		email = (TextView) findViewById(R.id.employeeDetailEmail);
		
		//SET THE TEXT FOR EACH TEXTVIEW USING PASSED INFO
		name.setText(_passedName);
		address.setText(_passedAddress);
		phone.setText(_passedPhone);
		mobile.setText(_passedMobile);
		email.setText(_passedEmail);
		
		//PHONE INTENT TO PLACE CALL TO EMPLOYEE
		call = (Button) findViewById(R.id.call);
		call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				call();
				
			}
		});
		
	
		//EMAIL INTENT TO EMAIL EMPLOYEE
		message = (Button) findViewById(R.id.mail);
		message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent email = new Intent(android.content.Intent.ACTION_SEND);
				email.setType("text/plain");
				email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Your shift");
				final PackageManager pm = getPackageManager();
				final List<ResolveInfo> matches = pm.queryIntentActivities(email, pm.MATCH_DEFAULT_ONLY);
				ResolveInfo best = null;
				for(final ResolveInfo info: matches){
					if(info.activityInfo.name.toLowerCase().contains("mail")) best = info;
				}
				if(best !=null){
					email.setClassName(best.activityInfo.packageName, best.activityInfo.name);
					startActivity(email);				}
				
			}
		});
		
	}	
			
		
	private void call(){
		try{
			Intent call = new Intent(Intent.ACTION_CALL);
			call.setData(Uri.parse("tel:_passedMobile"));
			startActivity(call);
		}catch(ActivityNotFoundException e){
			Log.e("Call Failed", "Hanging Up");
		}
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
			Intent back = new Intent(EmployeeDetails.this, TabActivity.class);
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
			Intent devInfo = new Intent(EmployeeDetails.this, DeveloperInfo.class);
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
			Intent logoff = new Intent(EmployeeDetails.this, MainActivity.class);
			startActivity(logoff);
			break;
		
		}
		return true;
		
	}
	
}
	
		

