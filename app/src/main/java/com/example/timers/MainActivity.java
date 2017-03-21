package com.example.timers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements OnClickListener {

	MyView gameView;
	//The time used for the pacman
	private Timer myTimer;
	private int counter = 0;
	private boolean running = false;
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.startButton);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.stopButton);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.resetButton);
		button.setOnClickListener(this);
		textView = (TextView) findViewById(R.id.textView);
		gameView = (MyView) findViewById(R.id.gameView);

		//make a new timer
		myTimer = new Timer();
		running = true; //should the game be running?
		//We will call the timer 5 times each second
	    myTimer.schedule(new TimerTask() {          
	        @Override
	        public void run() {
	            TimerMethod();
	        }

	    }, 0, 200); //0 indicates we start now, 200
	    //is the number of miliseconds between each call
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//just to make sure if the app is killed, that we stop the timer.
		myTimer.cancel();
	}
	
	private void TimerMethod()
	{
	    //This method is called directly by the timer
	    //and runs in the same thread as the timer.

	    //We call the method that will work with the UI
	    //through the runOnUiThread method.
	    this.runOnUiThread(Timer_Tick);
	}


	private Runnable Timer_Tick = new Runnable() {
	    public void run() {

	    //This method runs in the same thread as the UI.
			// so we can draw
	    	if (running)
	    	{
		    	counter++;
				//update the counter - notice this is NOT seconds in this example
				//you need TWO counters - one for the time and one for the pacman
		    	textView.setText("Timer value: "+counter); 
		    	gameView.move(20); //move the pacman.
	    	}

	    }
	};
	
	

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

	//if anything is pressed - we do the checks here
	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.startButton)
		{
			running = true;
		}
		else if (v.getId()==R.id.stopButton)
		{
			running = false;
		}
		else if (v.getId()==R.id.resetButton)
		{
			counter = 0;
			gameView.reset();
			running = false;
	    	textView.setText("Timer value: "+counter); 

		}
	}
}
