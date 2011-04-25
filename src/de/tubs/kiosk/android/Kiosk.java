package de.tubs.kiosk.android;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Kiosk extends Activity {

	private Button mTest = null;
	
	
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
	    	startActivity(new Intent(this,Configure.class));
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

		if ( mTest != null)
			mTest.setOnClickListener( new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Log.w("test", "test22");

					Intent  googlemaps = null;

					final PackageManager pm = getPackageManager();
					if (pm == null)
						return;
					List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

					for (ApplicationInfo packageInfo : packages) {

						Log.d("nu", "Installed package :" + packageInfo.packageName);
						Log.d("nu",	"Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));

						if ( packageInfo.packageName.contains("android.apps.maps"))
							googlemaps = pm.getLaunchIntentForPackage(packageInfo.packageName);

					}

					startActivity(googlemaps);

				}					
			});
	}
}
