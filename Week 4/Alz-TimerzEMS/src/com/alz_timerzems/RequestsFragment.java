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
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class RequestsFragment extends Fragment{
	
	//GLOBAL VARIABLES
	static View _view;
	ListView _requests;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 _view = (RelativeLayout) inflater.inflate(R.layout.requests_tab, container, false);
		 

		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
			
		 
		 //SET BUTTONS (ADD AND DELETE) AND LISTVIEW
		
		 _requests = (ListView) _view.findViewById(R.id.requestList);
		 
		
		
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
		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(2);
		HashMap<String, String> shiftRequest;
		for(ParseObject hashMap: objects){
			shiftRequest = new HashMap<String, String>();
			shiftRequest.put("name", hashMap.getString("Name"));
			shiftRequest.put("trade", hashMap.getString("Trade"));
			shiftRequest.put("new", hashMap.getString("For"));
			shiftRequest.put("requested", hashMap.getString("Requested"));
			
			list.add(shiftRequest);
		}
		
		//Info we want to pull from parse and view
		String[] deets2 = {"name"};
		//assign values to textviews
		int[] view2 = {R.id.nameText};
		
		//create adapter for listview
		final SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.requestslist_item, deets2, view2);
		_requests.setAdapter(adapter); 
		//go to detail view for more info
		_requests.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> detail = (HashMap<String, String>) _requests.getItemAtPosition(pos);
				Intent intent = new Intent(getActivity(), RequestsDetails.class);
				intent.putExtra("Details", detail.toString());
				intent.putExtra("DetailsName", detail.get("name"));
				intent.putExtra("DetailsTrade", detail.get("trade"));
				intent.putExtra("DetailsNewShift", detail.get("new"));
				intent.putExtra("DetailsRequested", detail.get("requested"));
				startActivity(intent);
						
						
						
			}
		});
				
		_requests.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int pos, long id) {
					list.remove(pos);
					adapter.notifyDataSetChanged();
				return false;
			}
		});
		
	}
}