package com.example.cleanspace;

import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * This activity will display the general details of our one sensor Details
 * include: Name, dust condition (good/bad)
 * 
 * FUTURE: Display details on more than one sensor
 * 
 * @author echiang
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		String FileName = "HAM_test_storage";
		int titleStop = 0;
		String SensorTitle = "";
		if(isExternalStorageReadable()){
			FileInputStream fis;
			
			try {
				fis = openFileInput(FileName);
				int t = 0;
			//	fis.
				while((t = fis.read()) != -1 && titleStop != 1){
										
					
						if (t == 10){
							titleStop = 1;
						} else{
							SensorTitle = SensorTitle + Character.toString((char)t);
						}
							
					
				}
				
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			 SensorTitle = "Furnace Sensor";
			
		}
		
		Button SensorButton = (Button) findViewById(R.id.button1);
		SensorButton.setText(SensorTitle);
		
	}

	// Click details button to open new activity
	public void openDetailsActivity(View view) {
		Intent detailsIntent = new Intent(MainActivity.this,
				DetailsActivity.class);
		// For future when we need to pass data to DetailsActivity:
		// detailsIntent.putExtra(name, value);
		MainActivity.this.startActivity(detailsIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public boolean isExternalStorageReadable(){
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state) ||
				Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
			return true;
		}
		return false;
	}
}
