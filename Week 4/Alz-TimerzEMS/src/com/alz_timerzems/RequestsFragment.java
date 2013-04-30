package com.alz_timerzems;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class RequestsFragment extends ListFragment{
	
	//GLOBAL VARIABLES
	static View _view;
	Button  _delete;
	Button  _request;
	ListView _requests;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 _view = (RelativeLayout) inflater.inflate(R.layout.requests_tab, container, false);
		 
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
		 
		 
		 return _view;
	}

}


