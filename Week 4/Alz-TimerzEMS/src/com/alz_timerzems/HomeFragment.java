package com.alz_timerzems;

import com.parse.ParseUser;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class HomeFragment extends Fragment{
	//GLOBAL VARIABLES
	Button _add_btn;
	Button _ok_btn;
	Button logout;
	static View _view;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		   _view =  (RelativeLayout) inflater.inflate(R.layout.home_tab, container, false);

		 //SET BUTTONS (ADD AND OK)
		   _add_btn = (Button) _view.findViewById(R.id.add);
		   _ok_btn = (Button) _view.findViewById(R.id.ok);
		   
		   //ADD BUTTON FUNCTIONALITY
		   _add_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent addForm = new Intent();
				addForm.setClass(getActivity(), EmployeeAdd.class);
				startActivity(addForm);				
		   
				
			}
		});
		   
		 //LOG OUT
			logout = (Button) _view.findViewById(R.id.logout);
			logout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					@SuppressWarnings("unused")
					ParseUser currentUser = ParseUser.getCurrentUser();
					ParseUser.logOut();
						
					}
			});
		   
		   

		
		return _view;
	}

}