/*
 * Copyright (C) 2018 Yahia H. El-Tayeb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package free.elmasry.death;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListenDoaaActivity extends Activity implements OnClickListener {
	
	private final String TAG = ListenDoaaActivity.class.getSimpleName();
	private MediaPlayer player;
	private Button startBtn;
	private TextView timeTxt;
	
	/** run at first time activity created */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// set the root view
		setContentView(R.layout.listen_doaa);
		
		// get the views which reside in the root view
		startBtn = (Button) findViewById(R.id.startBtn);
		LinearLayout layout = (LinearLayout) findViewById(R.id.listenDoaaLayout);
		timeTxt = (TextView) findViewById(R.id.timeTxt);
		
		// set event handler for the views
		layout.setOnClickListener(this);
		startBtn.setOnClickListener(this);
		
		// prevent screen from locking automatically
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		
		// create media player for the listen_doaa
		if (player == null) { 
			Log.i(TAG, "player is null");
			player = MediaPlayer.create(this, R.raw.doaa);
			player.setLooping(false);
		}
		
		if (savedInstanceState != null) {
			// retrieve the player state
			int position = savedInstanceState.getInt("position");
			boolean isPlaying = savedInstanceState.getBoolean("playingState");
			player.seekTo(position);
			if (isPlaying) { player.start(); timeTxt.setVisibility(View.INVISIBLE); }
			else           setTimeTxt();
			startBtn.setText(getString(R.string.replay_str));
		}
		
		// log cat information to trace the application
		Log.i(TAG, "onCreate() is called");
		
		
	}

	@Override
	protected void onDestroy() {
		
		// release player object if it is not null to reclaim the memory
		if (player != null) {
			player.release();
			player = null;
			Log.i(TAG, "player object is released");
		}
		
		super.onDestroy();
	}
	
	/* event handler for the views */
	@Override
	public void onClick(View v) {
		
		// change start button text whatever is clicked
		startBtn.setText(getString(R.string.replay_str));
		
		switch (v.getId()) {
		case R.id.startBtn:
			Log.i(TAG, "start button is clicked");
			player.seekTo(0);
			player.start();
			timeTxt.setVisibility(View.INVISIBLE);
			break;

		case R.id.listenDoaaLayout:
		default:
			if (player.isPlaying()) { 
				player.pause(); 
				timeTxt.setVisibility(View.VISIBLE);
				setTimeTxt();
			} else {
				player.start(); 
				timeTxt.setVisibility(View.INVISIBLE); 
			}
			Log.i(TAG, "listen_doaa layout is clicked");
			break;
		}
		
	}
	
	/* set remaining time to time text view */
	private void setTimeTxt() {
		// subtract elapse time in milliseconds from the listen_doaa duration
		//int remainingTime = player.getDuration();
		double remainingTime = 985626 - player.getCurrentPosition();
		
		// convert remaining time from milliseconds to minutes
		remainingTime /= 1000 * 60;
		
		// adjust time string
		String timeString;
		switch ((int) remainingTime) {
		case 10:
		case 9:
		case 8:
		case 7:
		case 6:
		case 5:
		case 4:
		case 3:
			timeString = (int) remainingTime + " " + getString(R.string.minutes_str);
			break;
		case 2:
			timeString = getString(R.string.two_minutes_str);
			break;
		case 1:
			timeString = getString(R.string.one_minute_str);
			break;
		case 0:
			timeString = getString(R.string.less_than_one_minute_str);
			break;
		default:
			timeString = (int) remainingTime + " " + getString(R.string.minute_str);
			break;
		}
		
		// set time text
		timeTxt.setText(getString(R.string.remaining_time_str) 
				+ ": " + timeString);
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		// save instance state of the player
		int position = player.getCurrentPosition();
		boolean playingState = player.isPlaying();
		outState.putInt("position", position);
		outState.putBoolean("playingState", playingState);
		
	}

}
