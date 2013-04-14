package com.alz_timerzems;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;

public class TabActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//CREATE ACTION BAR 
		ActionBar actionBar = getActionBar();
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
		
		
		String label2 = getResources().getString(R.string.label2);
		Tab schedules = actionBar.newTab();
		schedules.setText(label2);
		TabListener<SchedulesFragment> tl2 = new TabListener<SchedulesFragment>(this,
				label2, SchedulesFragment.class);
		schedules.setTabListener(tl2);
		actionBar.addTab(schedules);
		
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

}
