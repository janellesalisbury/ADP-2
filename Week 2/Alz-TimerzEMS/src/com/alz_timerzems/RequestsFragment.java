package com.alz_timerzems;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RequestsFragment extends Fragment{
	
	//GLOBAL VARIABLES
	static View _view;
	Button  _delete;
	Button  _request;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 _view = (RelativeLayout) inflater.inflate(R.layout.requests_tab, container, false);
		 
		 //SET BUTTONS (ADD AND DELETE)
		 _request = (Button) _view.findViewById(R.id.request);
		 _delete = (Button) _view.findViewById(R.id.delete);
		 
		
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


