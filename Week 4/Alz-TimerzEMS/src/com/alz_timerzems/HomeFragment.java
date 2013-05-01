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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

public class HomeFragment extends Fragment{
	//GLOBAL VARIABLES
	Button _add_btn;
	Button _ok_btn;
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
		   
			//INITIALIZE PARSE
			Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
			
		
		 //SET BUTTONS (ADD AND OK) AND LISTVIEW
		   _add_btn = (Button) _view.findViewById(R.id.add);
		   _ok_btn = (Button) _view.findViewById(R.id.ok);
		   employees = (ListView) _view.findViewById(R.id.listView1);
		   
		   //ADD BUTTON FUNCTIONALITY
		   _add_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent addForm = new Intent();
				addForm.setClass(getActivity(), EmployeeAdd.class);
				startActivity(addForm);				
		   
				
			}
		});

		   ParseQuery query = new ParseQuery("SavedEmployee");
		   query.whereEqualTo("row", "value");
		   query.findInBackground(new FindCallback() {
			
			@Override
			public void done(List<ParseObject> contactList, ParseException e) {
				if(e==null){
					initListView(contactList);
				}else{
					objectRetrievalFailed();
				}
				
			}
		});
		   
		
		return _view;
	}
	
	   protected void objectRetrievalFailed(){
		   
	   }
	   
	   private void initListView(List<ParseObject> object){
		   ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(2);
		   HashMap<String, String> obj;
		   
		   for(ParseObject hashMap: object){
			   obj = new HashMap<String,String>();
			   obj.put("line1", hashMap.getString("Name"));
			   obj.put("line2", hashMap.getString("Mobile"));
			   list.add(obj);
		   }
		   
		   String[] line = {"line1", "line2"};
		   int[] display = {R.id.text1};
		   
		   //create array adapter for listview
		   SimpleAdapter listAdapter = new SimpleAdapter(HomeFragment.this, list, R.layout.list_item, line, display);
		   employees.setAdapter(listAdapter);
	   }

}