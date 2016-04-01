package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var06SecondaryActivity extends Activity {

	
	TextView internetView;
	TextView resultView;
	Button ok;
	Button cancel;
	
	
	private ButtonListener buttonListener = new ButtonListener();
	private class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			switch(view.getId()) {
				case R.id.ok_button:
					setResult(RESULT_OK, null);
					break;
				case R.id.cancel_button:
					setResult(RESULT_CANCELED, null);
					break;
			}
			finish();
		}
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var06_secondary);
		
		internetView = (TextView) findViewById(R.id.internet);
		resultView = (TextView) findViewById(R.id.result);
		
		ok = (Button) findViewById(R.id.ok_button);
		cancel = (Button) findViewById(R.id.cancel_button);
		ok.setOnClickListener(buttonListener);
		cancel.setOnClickListener(buttonListener);
		
		
	
		Intent intent = getIntent();
		if (intent != null && intent.getExtras().containsKey("internetAddress")) {
			String internetString = intent.getStringExtra("internetAddress");
			internetView.setText(internetString);
		}
		
		if (intent != null && intent.getExtras().containsKey("stateButton")) {
			String state = intent.getStringExtra("stateButton");
			resultView.setText(state);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.practical_test01_var06_secondary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
