package de.tubs.kiosk.android;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Kiosk extends Activity implements OnClickListener {

	private Button mTest = null;
	private Button mBtnOne = null;
	private Button mBtnTwo = null;
	private Button mBtnThree = null;
	private Button mBtnFour = null;
	private Button mBtnFive = null;
	private Button mBtnSix = null;
	private Button mBtnSeven = null;
	private Button mBtnEight = null;
	private Button mBtnNine = null;
	SharedPreferences mPrefs = null;
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.root_menu, menu);
//        return true;
		return super.onCreateOptionsMenu(menu);
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.configure:
	    	startActivityForResult(new Intent(this,Configure.class), 1);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);

		mTest = (Button) findViewById(R.id.btnTest);
		
		mBtnOne = (Button) findViewById(R.id.btnOne);
		mBtnTwo = (Button) findViewById(R.id.btnTwo);
		mBtnThree = (Button) findViewById(R.id.btnThree);
		mBtnFour = (Button) findViewById(R.id.btnFour);
		mBtnFive = (Button) findViewById(R.id.btnFive);
		mBtnSix = (Button) findViewById(R.id.btnSix);
		mBtnSeven = (Button) findViewById(R.id.btnSeven);
		mBtnEight = (Button) findViewById(R.id.btnEight);
		mBtnNine = (Button) findViewById(R.id.btnNine);
		
		mBtnOne.setOnClickListener(this);
		mBtnTwo.setOnClickListener(this);
		mBtnThree.setOnClickListener(this);
		mBtnFour.setOnClickListener(this);
		mBtnFive.setOnClickListener(this);
		mBtnSix.setOnClickListener(this);
		mBtnSeven.setOnClickListener(this);
		mBtnEight.setOnClickListener(this);
		mBtnNine.setOnClickListener(this);
		

		
		// Anonymous clicklistener for the test button
		if ( mTest != null)
			mTest.setOnClickListener( new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					//Log.w("test", "test22");

					//Intent googlemaps = null;

					final PackageManager pm = getPackageManager();
					if (pm == null)
						return;
					List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

					for (ApplicationInfo packageInfo : packages) {

						Log.d("nu", "Installed package :" + packageInfo.packageName);
						Log.d("nu",	"Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));

						/*
						if ( packageInfo.packageName.contains("android.apps.maps")) {
							Log.w("willich", packageInfo.packageName.toString());
							googlemaps = pm.getLaunchIntentForPackage(packageInfo.packageName);
						}
						*/

					}

					//startActivity(googlemaps);

				}					
			});
	}


	@Override
	public void onClick(View v) {
		Intent activityToStart = null;
		
		String prefActivity = null;
		
		final PackageManager pm = getPackageManager();
		if (pm == null)
			return;
		
		
		// com.google.android.apps.maps
		switch (v.getId()) {
		case R.id.btnOne:
			prefActivity = mPrefs.getString("one", null); break;
		case R.id.btnTwo:
			prefActivity = mPrefs.getString("two", null); break;
		case R.id.btnThree:
			prefActivity = mPrefs.getString("three", null); break;
		case R.id.btnFour:
			prefActivity = mPrefs.getString("four", null); break;
		case R.id.btnFive:
			prefActivity = mPrefs.getString("five", null); break;
		case R.id.btnSix:
			prefActivity = mPrefs.getString("six", null); break;
		case R.id.btnSeven:
			prefActivity = mPrefs.getString("seven", null); break;
		case R.id.btnEight:
			prefActivity = mPrefs.getString("eight", null); break;
		case R.id.btnNine:
			prefActivity = mPrefs.getString("nine", null); break;
		default:
			Log.d("Error:", "Shouln't happen (:");
	}

		
		
		if ( prefActivity == null) {
			Toast.makeText(this, "Please configure this button", Toast.LENGTH_SHORT).show();
			//Log.w("test", "blalal");
			return;
		} else /*{
			Log.i("blaa", prefActivity);
		}*/
			
		activityToStart = pm.getLaunchIntentForPackage(prefActivity);
		
		startActivity(activityToStart);
		
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if ( requestCode == 1 && resultCode == 42)
			this.finish();
	}
}
