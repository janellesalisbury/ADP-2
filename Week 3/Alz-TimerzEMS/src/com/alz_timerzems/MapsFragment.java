package com.alz_timerzems;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MapsFragment extends Fragment{
	//GLOBAL VARIABLE
	Button _search_btn;
	static View _view;
	

	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		 _view = (RelativeLayout)inflater.inflate(R.layout.maps_tab, container, false);
		 
		 final EditText addressfield = (EditText) _view.findViewById(R.id.location);  // Reference edit field
	     Button launchmapbtn = (Button) _view.findViewById(R.id.launch);  // Reference search button
	        launchmapbtn.setOnClickListener(new OnClickListener(){	
					public void onClick(View v) { 
						
							String address = addressfield.getText().toString(); // Get address
							address = address.replace(" ", "+");
							Intent geoIntent = new Intent (android.content.Intent.ACTION_VIEW, Uri.parse ("geo:0,0?q=" + address)); // Prepare intent
							startActivity(geoIntent);	// Initiate lookup
						
				}
			});
	   	return _view;
	}

}
