package com.alz_timerzems;

import java.util.List;

import com.parse.Parse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EmployeeDetails extends Activity{
	//GLOBAL VARIABLES
	String _passedName;
	String _passedAddress;
	String _passedPhone;
	String _passedMobile;
	String _passedEmail;
	TextView name;
	TextView address;
	TextView phone;
	TextView mobile;
	TextView email;
	Button back;
	Button call;
	Button message;
	final Context context = this;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_details);
		
		//INITIALIZE PARSE
		Parse.initialize(null, "9A7rNDmuRqbEQAkuK8CsvTKqXwJ8neVE6ZYPpJOz", "MQOtWDlSgfllhr1L91oy8VfrPWuBEbNePtCVFgzu");
		
		
		//GET THE PASSED INFO USING AN INTENT
		Intent i = getIntent();
		_passedName = i.getStringExtra("DetailName");
		_passedAddress = i.getStringExtra("DetailAddress");
		_passedPhone = i.getStringExtra("DetailPhone");
		_passedMobile = i.getStringExtra("DetailMobile");
		_passedEmail = i.getStringExtra("DetailEmail");
		
		//FIND THE TEXTVIEWS
		name = (TextView) findViewById(R.id.employeeDetailName);
		address = (TextView) findViewById(R.id.employeeDetailAddress);
		phone = (TextView) findViewById(R.id.employeeDetailsPhone);
		mobile = (TextView) findViewById(R.id.employeeDetailMobile);
		email = (TextView) findViewById(R.id.employeeDetailEmail);
		
		//SET THE TEXT FOR EACH TEXTVIEW USING PASSED INFO
		name.setText(_passedName);
		address.setText(_passedAddress);
		phone.setText(_passedPhone);
		mobile.setText(_passedMobile);
		email.setText(_passedEmail);
		
		//PHONE INTENT TO PLACE CALL TO EMPLOYEE
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		call = (Button) findViewById(R.id.call);
		call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent call = new Intent(Intent.ACTION_CALL);
				call.setData(Uri.parse("_passedMobile"));
				startActivity(call);
				
			}
		});
		
		//EMAIL INTENT TO EMAIL EMPLOYEE
		message = (Button) findViewById(R.id.mail);
		message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent email = new Intent(android.content.Intent.ACTION_SEND);
				email.setType("text/plain");
				email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Your shift");
				final PackageManager pm = getPackageManager();
				final List<ResolveInfo> matches = pm.queryIntentActivities(email, pm.MATCH_DEFAULT_ONLY);
				ResolveInfo best = null;
				for(final ResolveInfo info: matches){
					if(info.activityInfo.name.toLowerCase().contains("mail")) best = info;
				}
				if(best !=null){
					email.setClassName(best.activityInfo.packageName, best.activityInfo.name);
					startActivity(email);				}
				
			}
		});
		
		//BACK BUTTON
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goBack = new Intent(EmployeeDetails.this, TabActivity.class);
				startActivity(goBack);
				
			}
		});
		
	
	}
	//CREATE LISTENER FOR PHONE STATE (CLASS)
	private class PhoneCallListener extends PhoneStateListener{
		private boolean isPhoneCalling = false;
		
		String LOG_TAG = "Log 123";
		@Override
		public void onCallStateChanged(int state, String incomingNumber){
			if(TelephonyManager.CALL_STATE_RINGING == state){
				//pick up your phone its ringing fool
				Log.i(LOG_TAG, "Call From, number:" + incomingNumber);
			}
			if(TelephonyManager.CALL_STATE_OFFHOOK == state){
				//hang it up silly
				Log.i(LOG_TAG, "Hang it up");
				isPhoneCalling = true;
			}
			if(TelephonyManager.CALL_STATE_IDLE == state){
				Log.i(LOG_TAG, "Idle");
				if(isPhoneCalling){
					Log.i(LOG_TAG, "restart application");
					
					Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
					i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					isPhoneCalling = false;
				}
			}
		}
		
	}
	

}
