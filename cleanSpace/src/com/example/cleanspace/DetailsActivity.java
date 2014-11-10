package com.example.cleanspace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.net.MalformedURLException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;

import static com.example.cleanspace.EditActivity.EDITEDTITLE; //@string/first_sensor_details"
import static com.example.cleanspace.EditActivity.EDITEDSAMPLEAREA;

public class DetailsActivity extends Activity {
	
	public final String ARDUINO_IP_ADDRESS = "192.168.240.1";
	public String newString = "test";
	private Boolean mStop = false;
	public int value;
	
//Initialize the details activity page and create threa for Arduino communication
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		String newSensorTitle = "Furnace Filter Sensor";
		String newSampleArea = "123";
		
		Intent intent = getIntent();
		if(null != intent){
			newSensorTitle = intent.getStringExtra(EDITEDTITLE);
			
			newSampleArea = intent.getStringExtra(EDITEDSAMPLEAREA);
		}
		
		TextView sensorTitle = (TextView) findViewById(R.id.sensor_title);
		sensorTitle.setText(newSensorTitle);
		
		TextView sampleArea = (TextView) findViewById(R.id.sample_area);
		sampleArea.setText(newSampleArea);
	}
	
	@Override
	protected void onStart(){
		mStop = false;
		if(threadReceive == null){
			threadReceive = new Thread(networkRunnableReceive);
			threadReceive.start();
		}	
		super.onStart();
	}
	
	@Override
	protected void onStop(){
		mStop = true;
		if(threadReceive != null){
			threadReceive.interrupt();
		}
		super.onStop();
	}
	
//initiating the connection to the Arduino and calls readFROMURL function
	private static Thread threadReceive = null;
	private final Runnable networkRunnableReceive = new Runnable(){
		@Override
		public void run() {
			String url = "http://"+ARDUINO_IP_ADDRESS+"/arduino/analog/0"; //Read from analog 0
		
			while(mStop == false){
				try {
					String tempString = readFROMURL(url);
					try {
						value = Integer.parseInt(tempString);
					} 
					catch (Exception e){
						e.printStackTrace();
					}
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			threadReceive = null;
		}
	};
	
//updates the text of the testTCPtext to the int value
	public void getValue(View view) {
		setContentView(R.layout.activity_details);
		TextView myText = (TextView) findViewById(R.id.testTCPtext);
		myText.setText(String.valueOf(value));
	}

//reads the value of the Arduino and close the connection when end
	public String readFROMURL(String passURL){
		URL tempURL;
		StringBuilder builder = new StringBuilder();
		try {
			tempURL = new URL(passURL);
			URLConnection urlConnect = tempURL.openConnection();
			BufferedReader mcData = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
			String inputLine;
			while ((inputLine = mcData.readLine()) != null){ 
				builder.append(inputLine);
			}
			mcData.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	//link to Edit Activity page
	public void openEditActivity(View view){
			Intent editIntent = new Intent(DetailsActivity.this, EditActivity.class);
			DetailsActivity.this.startActivity(editIntent);
	}
	
	public void saveEditActivity(View view, String newText){
		setContentView(R.layout.activity_details);
		TextView myText = (TextView) findViewById(R.id.sensor_title);
		myText.setText(String.valueOf(newText));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
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





/*

public class DetailsActivity extends Activity {
	public final String ARDUINO_IP_ADDRESS = "192.168.43.126";
	private Boolean mStop = false;
	
	private static Thread threadReceive = null;

	private final Runnable networkRunnableReceive = new Runnable(){
		public void run() {
			String url = "http://"+ARDUINO_IP_ADDRESS+"/arduino/analog/0"; //Read from analog 0
		
			while(mStop == false){
				try {
					String tempString = readFromURL(url);
					String val = "";
					try{
						
					} catch(Exception e){
						e.printStackTrace();
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			
		}
	};
	*//*
	protected void threadStart(){
		mStop = false;
		if(threadReceive == null){
			threadReceive = new Thread(networkRunnableReceive);
			threadReceive.start();
		}
		
	}
	protected void threadStop(){
		mStop = true;
		if(threadReceive != null){
			threadReceive.interrupt();
		}
	}
	*/
/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
	}
	
	public void openEditActivity(View view){
		Intent editIntent = new Intent(DetailsActivity.this, EditActivity.class);
		DetailsActivity.this.startActivity(editIntent);
	}
	
	
	public void getTCPData(View view){
		//setContentView(R.layout.activity_details);
		TextView myText = (TextView) findViewById(R.id.testTCPtext);
		
		
		myText.setText("Button Clicked");
		//	Socket socket;
			try {
			//s	socket.connect("192.168.43.126");
				Socket socket = new Socket("192.168.43.126", 5555);
				
				
				InputStream in = socket.getInputStream();
				//TextView myText1 = (TextView) findViewById(R.id.testTCPtext);
				
				
				myText.setText(in.toString());
				
				socket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
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
*/


