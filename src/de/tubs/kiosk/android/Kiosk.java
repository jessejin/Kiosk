package de.tubs.kiosk.android;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Kiosk extends Activity {

	private Button mTest = null;

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
