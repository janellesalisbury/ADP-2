package com.alz_timerzems;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class DeveloperInfo extends Activity{
	
	//GLOBAL VARIABLES
		private WebView wv;
		
		
		
		@SuppressLint("SetJavaScriptEnabled")
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.dev_info);
	 
			wv = (WebView) findViewById(R.id.webView1);
			wv.getSettings().setJavaScriptEnabled(true);
			wv.loadUrl("http://www.linkedin.com/profile/view?id=216001416");
	 
		}
	 
	}




