package com.example.cleanspace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

public class EditActivity extends Activity {

	public static final String EDITEDTITLE = "temp";
	public static final String EDITEDSAMPLEAREA = "123";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
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
	

	
	public void saveEditActivity(View view) {
		EditText newSensorTitle = (EditText) findViewById(R.id.sensor_title);
		String newTitle = newSensorTitle.getText().toString();
		
		EditText sampleArea = (EditText) findViewById(R.id.sample_area);
		String newSampleArea = sampleArea.getText().toString();
		
		Intent detailsIntent = new Intent(EditActivity.this,
				DetailsActivity.class);
		// For future when we need to pass data to DetailsActivity:
		// detailsIntent.putExtra(name, value);
		detailsIntent.putExtra(EDITEDTITLE, newTitle);
		detailsIntent.putExtra(EDITEDSAMPLEAREA, newSampleArea);
		
		EditActivity.this.startActivity(detailsIntent);
	}
	
}
