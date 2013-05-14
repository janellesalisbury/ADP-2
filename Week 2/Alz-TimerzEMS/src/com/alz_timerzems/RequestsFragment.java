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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class RequestsFragment extends Fragment{
	
	//GLOBAL VARIABLES
	static View _view;
	Button  _delete;
	Button  _request;
	ListView _requests;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 _view = (RelativeLayout) inflater.inflate(R.layout.requests_tab, container, false);
		 

		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
			
		 
		 //SET BUTTONS (ADD AND DELETE) AND LISTVIEW
		 _request = (Button) _view.findViewById(R.id.request);
		 _delete = (Button) _view.findViewById(R.id.delete);
		 _requests = (ListView) _view.findViewById(R.id.requestList);
		 
		
		//BUTTON FUNCTIONALITY
		_request.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent requestForm = new Intent();
				requestForm.setClass(getActivity(), RequestForm.class);
				startActivity(requestForm);
				
			}
		});
		 
		ParseQuery query = new ParseQuery("ShiftRequest");
		   query.whereEqualTo("row", "value");
		   query.findInBackground(new FindCallback() {
			
			@Override
			public void done(List<ParseObject> requestsList, ParseException e) {
				if(e == null){
					initListView(requestsList);
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
		HashMap<String, String> shiftRequest;
		for(ParseObject hashMap: objects){
			shiftRequest = new HashMap<String, String>();
			shiftRequest.put("name", hashMap.getString("Name"));
			shiftRequest.put("date", hashMap.getString("Date"));
			
			list.add(shiftRequest);
		}
		
		//Info we want to pull from parse and view
		String[] deets2 = {"name", "date"};
		//assign values to textviews
		int[] view2 = {R.id.nameText, R.id.dateText};
		
		//create adapter for listview
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.requestslist_item, deets2, view2);
		_requests.setAdapter(adapter); 
		//go to detail view for more info
		_requests.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> detail = (HashMap<String, String>) _requests.getItemAtPosition(pos);
				Intent intent = new Intent(getActivity(), ShiftRequestDetails.class);
				intent.putExtra("Details", detail.toString());
				intent.putExtra("DetailsName", detail.get("name"));
				intent.putExtra("DetailsTime", detail.get("time"));
				intent.putExtra("DetailsDate", detail.get("date"));
				intent.putExtra("DetailsExcuse", detail.get("excuse"));
				startActivity(intent);
						
						
						
			}
		});
				
		
		
	}
}