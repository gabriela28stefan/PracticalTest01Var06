package ro.pub.cs.systems.eim.practicaltest01var06;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class PracticalTest01Var06MainActivity extends Activity {

	
	LinearLayout container;
	int serviceStatus = 0; //oprit
	Button moreLessButton;
	Button passFailButton;
	EditText first;
	EditText uriEditText;
	Button navigateButton;
	
	private ButtonOnClickListener buttonListener = new ButtonOnClickListener();
	private class ButtonOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {

			switch (arg0.getId()) {
			case R.id.more_less_details:
				if(container.getVisibility() == View.INVISIBLE){
					container.setVisibility(View.VISIBLE);
					moreLessButton.setText("Less Details");
				} else {
					container.setVisibility(View.INVISIBLE);
					moreLessButton.setText("More Details");
				}
				break;

			case R.id.navigate_to_secondary_activity:
				Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
				String internetAddress = uriEditText.getText().toString();
				String state = passFailButton.getText().toString();
				
				intent.putExtra("internetAddress", internetAddress);
				intent.putExtra("stateButton", state);
				
				startActivityForResult(intent, 2016);
				break;
			default:
				break;
			}
		}
		
	}
	
	
	
	private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
	private class MessageBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("[Message]", intent.getStringExtra("message"));
		}
	}
	
	
	
	private IntentFilter  filter = new IntentFilter();
	private EditTextListener editTextListener = new EditTextListener();
	private class EditTextListener implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
			if (uriEditText.getText().toString() != null 
					&& uriEditText.getText().toString().equals("http")){
				passFailButton.setText("PASS");
				passFailButton.setBackground(getResources().getDrawable(R.color.green));
				
				
				

				if (passFailButton.getText().toString().equals("PASS") &&
						serviceStatus == 0) {
					Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
					intent.putExtra("InternetAddressService", uriEditText.getText().toString());
					getApplicationContext().startService(intent);
					serviceStatus = 1;
				}
			} else {
				passFailButton.setText("FAIL");
				passFailButton.setBackground(getResources().getDrawable(R.color.red));
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);
        
        container = (LinearLayout) findViewById(R.id.additional_details_container);
        container.setVisibility(View.INVISIBLE);
        
        uriEditText = (EditText)findViewById(R.id.uri_edit_text);
        uriEditText.addTextChangedListener(editTextListener);
        
        first = (EditText) findViewById(R.id.name_edit_text);
        
        passFailButton = (Button)findViewById(R.id.pass_fail_button);
        passFailButton.setOnClickListener(buttonListener);
        
        moreLessButton = (Button) findViewById(R.id.more_less_details);
        moreLessButton.setOnClickListener(buttonListener);
        
        navigateButton = (Button)findViewById(R.id.navigate_to_secondary_activity);
        navigateButton.setOnClickListener(buttonListener);
        
        first.setText(String.valueOf(0));
        uriEditText.setText(String.valueOf(0));
        
        
    }

    
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
      savedInstanceState.putString("firstEditText",  first.getText().toString());
      savedInstanceState.putString("uriEditText", uriEditText.getText().toString());
    }
   
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
      if (savedInstanceState.containsKey("firstEditText")) {
        first.setText(savedInstanceState.getString("firstEditText"));
      } else {
        first.setText(String.valueOf(0));
      }
      if (savedInstanceState.containsKey("uriEditText")) {
        uriEditText.setText(savedInstanceState.getString("uriEditText"));
      } else {
        uriEditText.setText(String.valueOf(0));
      }
    }

    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 2016) {
			Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
		}
	}
    
    
    @Override
    protected void onResume() {
    	super.onResume();
		Toast.makeText(this, "Saved 1" + uriEditText.getText().toString(), Toast.LENGTH_LONG).show();
		Toast.makeText(this, "Saved 2" + first.getText().toString(), Toast.LENGTH_LONG).show();
		registerReceiver(messageBroadcastReceiver, filter);

    }
    
    protected void onPause() {
    	unregisterReceiver(messageBroadcastReceiver);
    	super.onPause();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test01_var06_main, menu);
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
