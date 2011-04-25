package de.tubs.kiosk.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Configure extends Activity implements OnClickListener {
	
	SharedPreferences mPrefs = null;
	Button mBtnOK = null;
	TextView mtextPassword = null;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.configure);
		
		mBtnOK = (Button) findViewById(R.id.checkrootpwok);
		mtextPassword = (TextView) findViewById(R.id.rootpw);

		if ( mBtnOK != null)
			mBtnOK.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		String textUserPW = mtextPassword.getText().toString();
		String prefsPW = mPrefs.getString("passwd", "uppsala");
		
		if ( textUserPW.equals( prefsPW) ) {
			startActivity(new Intent(this,Preferences.class));			
		} else
			Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		
//		mPrefs = getSharedPreferences("de.tubs.kiosk.android_preferences", MODE_PRIVATE);
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		TextView name = (TextView) findViewById(R.id.textView1);
		name.setText("[DEBUG] current pw: <<" + mPrefs.getString("passwd","uppsala") + ">>");
	}

}
