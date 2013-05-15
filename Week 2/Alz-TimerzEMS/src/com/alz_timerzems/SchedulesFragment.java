
package com.alz_timerzems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SchedulesFragment extends Fragment{
	
	//GLOBAL VARIABLES
		static View _view;
		Button  _add;
		Button  _edit;
		ListView _schedules;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			 _view = (RelativeLayout) inflater.inflate(R.layout.schedules_tab, container, false);
			 

			//INITIALIZE PARSE
			Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
				
			 
			 //SET BUTTONS (ADD AND DELETE) AND LISTVIEW
			 _add = (Button) _view.findViewById(R.id.add);
			 _edit = (Button) _view.findViewById(R.id.edit);
			 _schedules = (ListView) _view.findViewById(R.id.schedules);
			 
			
			//BUTTON FUNCTIONALITY
			_add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent requestForm = new Intent();
					requestForm.setClass(getActivity(), ScheduleAdd.class);
					startActivity(requestForm);
					
				}
			});
			 
			ParseQuery query = new ParseQuery("SavedEmployeeShift");
			   query.whereEqualTo("row", "value");
			   query.findInBackground(new FindCallback() {
				
				@Override
				public void done(List<ParseObject> schedulesList, ParseException e) {
					if(e == null){
						initListView(schedulesList);
					}else{
						objectRetrievalFailed();
					}
					
				}
			});
			   return _view;
		}
		

		protected void objectRetrievalFailed(){
			
		}
		private void initListView(List<ParseObject> objects){
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(2);
			HashMap<String, String> schedules;
			for(ParseObject hashMap: objects){
				schedules = new HashMap<String, String>();
				schedules.put("name", hashMap.getString("Name"));
				schedules.put("position", hashMap.getString("Position"));
				
				list.add(schedules);
			}
			
			//Info we want to pull from parse and view
			String[] deets3 = {"name", "position"};
			//assign values to textviews
			int[] view3 = {R.id.scheduleName, R.id.scheduleInfo};
			
			//create adapter for listview
			SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.schedules_listitem, deets3, view3);
			_schedules.setAdapter(adapter); 
			//go to detail view for more info
			_schedules.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View v, int pos,
						long id) {
					@SuppressWarnings("unchecked")
					HashMap<String, String> detail = (HashMap<String, String>) _schedules.getItemAtPosition(pos);
					Intent intent = new Intent(getActivity(), ScheduleDetails.class);
					intent.putExtra("Details", detail.toString());
					intent.putExtra("DetailsName", detail.get("name"));
					intent.putExtra("DetailsTime", detail.get("time"));
					intent.putExtra("DetailsDay", detail.get("day"));
					intent.putExtra("DetailsPosition", detail.get("position"));
					startActivity(intent);
							
							
							
				}
			});
			
			
		}
}