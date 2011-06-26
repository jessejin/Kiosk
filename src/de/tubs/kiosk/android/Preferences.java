package de.tubs.kiosk.android;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Preferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.set_menu, menu);
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
	    case R.id.exit:
	    	setResult(42);
	    	this.finish();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}
