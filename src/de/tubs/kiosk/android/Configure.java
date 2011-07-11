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

	private SharedPreferences mPrefs = null;
	private Button mBtnOK = null;
	private TextView mtextPassword = null;

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
			startActivityForResult(new Intent(this,Preferences.class), 1);
		} else
			Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if ( requestCode == 1 && resultCode == 42) {
			setResult( 42);
			this.finish();
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();

		// getDefaultSharedPreferences is much better see:
		// http://www.google.com/codesearch/p?hl=en#uX1GffpyOZk/core/java/android/preference/PreferenceManager.java&d=3
		
		// geht nicht, weil man sich dann um alles selber kuemmern muss und nicht das Framework nutzen kann
//		mPrefs = getPreferences(MODE_PRIVATE);

//		mPrefs = getSharedPreferences("de.tubs.kiosk.android_preferences", MODE_PRIVATE);
		// Dieses ist ein wrapper fuer die Zeile drueber, also portabler
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		TextView name = (TextView) findViewById(R.id.textView1);
		name.setText("[DEBUG] current pw: <<" + mPrefs.getString("passwd","uppsala") + ">>");
	}

}
