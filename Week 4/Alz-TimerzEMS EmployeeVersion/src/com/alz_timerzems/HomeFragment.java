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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

public class HomeFragment extends Fragment{
	//GLOBAL VARIABLES

	ListView employees;
	static View _view;
	
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		   _view =  (RelativeLayout) inflater.inflate(R.layout.home_tab, container, false);
		   employees = (ListView) _view.findViewById(R.id.listView1);
		   
		   
			//INITIALIZE PARSE
			Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
			
		
		 //SET LISTVIEW
		  employees = (ListView) _view.findViewById(R.id.listView1);
		   
		  

		   ParseQuery query = new ParseQuery("SavedEmployee");
		   query.whereEqualTo("row", "value");
		   query.findInBackground(new FindCallback() {
			
			@Override
			public void done(List<ParseObject> employeeList, ParseException e) {
				if(e == null){
					initListView(employeeList);
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
		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(2);
		HashMap<String, String> names;
		for(ParseObject hashMap: objects){
			names = new HashMap<String, String>();
			names.put("name", hashMap.getString("Name"));
			names.put("mobile", hashMap.getString("Mobile"));
			names.put("phone", hashMap.getString("Phone"));
			names.put("address", hashMap.getString("Address"));
			names.put("email", hashMap.getString("Email"));
			
			list.add(names);
			
		}
		
		//Info we want to pull from parse and view
		String[] deets = {"name", "mobile"};
		//assign values to textviews
		int[] view = {R.id.textName, R.id.textMobile};
		
		//create adapter for listview
	   final SimpleAdapter adapt = new SimpleAdapter(getActivity(), list, R.layout.employeelist_item, deets, view);
		employees.setAdapter(adapt);
		
		//go to detail view for more info
		employees.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> detail = (HashMap<String, String>) employees.getItemAtPosition(pos);
				Intent intent = new Intent(getActivity(), EmployeeDetails.class);
				intent.putExtra("Details", detail.toString());
				intent.putExtra("DetailName", detail.get("name"));
				intent.putExtra("DetailAddress", detail.get("address"));
				intent.putExtra("DetailPhone", detail.get("phone"));
				intent.putExtra("DetailMobile", detail.get("mobile"));
				intent.putExtra("DetailEmail", detail.get("email"));
				startActivity(intent);
				
				
				
			}
		});
		
		
	}
	
}
