package com.alz_timerzems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import com.parse.Parse;
import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
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

public class TabActivity extends Activity{
	//GLOBAL VARIABLE
	int imageNum = 0;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//INITIALIZE PARSE
		Parse.initialize(this, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu"); 

		
		//CREATE ACTION BAR 
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//CREATE TABS FOR EACH SECTION
		//Home Tab
		String label1 = getResources().getString(R.string.label1);
		Tab home = actionBar.newTab();
		home.setText(label1);
		//add tab listener for click functionality
		TabListener<HomeFragment>t1 = new TabListener<HomeFragment>(this, label1, HomeFragment.class); 
		home.setTabListener(t1);
		//add tab to view
		actionBar.addTab(home);
		
		//SCHEDULES TAB
		String label2 = getResources().getString(R.string.label2);
		Tab schedules = actionBar.newTab();
		schedules.setText(label2);
		TabListener<SchedulesFragment> tl2 = new TabListener<SchedulesFragment>(this,
				label2, SchedulesFragment.class);
		schedules.setTabListener(tl2);
		actionBar.addTab(schedules);
		
		//SHIFT REQUESTS TAB
		String label3 = getResources().getString(R.string.label3);
		Tab requests = actionBar.newTab();
		requests.setText(label3);
		TabListener<RequestsFragment> tl3 = new TabListener<RequestsFragment>(this,
				label3, RequestsFragment.class);
		requests.setTabListener(tl3);
		actionBar.addTab(requests);
		
		//MAPS TAB
		
		String label4 = getResources().getString(R.string.label4);
		Tab maps = actionBar.newTab();
		maps.setText(label4);
		TabListener<MapsFragment> tl4 = new TabListener<MapsFragment>(this,
				label4, MapsFragment.class);
		maps.setTabListener(tl4);
		actionBar.addTab(maps);
		
	}
	
	//CREATE TABLISTENER CLASS
	private class TabListener<T extends Fragment> implements ActionBar.TabListener{
		
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;
		
		public TabListener(Activity activity, String tag, Class<T> cls){
			mActivity = activity;
			mTag = tag;
			mClass = cls;
			
		}
		
		public void onTabSelected(Tab tab, FragmentTransaction ft){
			//check if fragment is initialized
			if(mFragment == null){
				//instantiate
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			}else{
				//attach fragment
				ft.attach(mFragment);
			}
		}
		public void onTabUnselected(Tab tab, FragmentTransaction ft){
			if(mFragment !=null){
				ft.detach(mFragment);
			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			//do nothing since user selected already selected tab
			
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
			Intent devInfo = new Intent(TabActivity.this, DeveloperInfo.class);
			startActivity(devInfo);
			break;
		case R.id.menu_settings:
			Intent settings = new Intent(Settings.ACTION_SETTINGS);
			settings.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(settings);
			break;
		case R.id.logout:
			ParseUser.logOut();
			ParseUser currentUser = ParseUser.getCurrentUser();
			Intent logoff = new Intent(TabActivity.this, MainActivity.class);
			startActivity(logoff);
			break;
		}
		return true;
		
	}


}
