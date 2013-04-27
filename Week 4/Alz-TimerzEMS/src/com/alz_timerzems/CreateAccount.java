package com.alz_timerzems;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class CreateAccount extends Activity{
	
	private WebView acct;
	
	
	
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account);
 
		acct = (WebView) findViewById(R.id.account);
		acct.getSettings().setJavaScriptEnabled(true);
		acct.loadUrl("https://accounts.google.com/SignUp");
 
	}

}
