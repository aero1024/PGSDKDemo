package com.pinguo.demo;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainLayoutActivity extends Activity implements OnClickListener {
	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub

		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
		}
	};

	private Button mPreviewButton;
	private Button mMakedButton;
	private Button mTestButton;
	private Button mServiceButton;
	
	protected void onDestroy() {
		super.onDestroy();
		
		unbindService(serviceConnection);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		mPreviewButton = (Button) findViewById(R.id.preview_button);
		mPreviewButton.setOnClickListener(this);

		mMakedButton = (Button) findViewById(R.id.maked_button);
		mMakedButton.setOnClickListener(this);
		
		mServiceButton = (Button) findViewById(R.id.service_button);
		mServiceButton.setOnClickListener(this);

		mTestButton = (Button) findViewById(R.id.test_button);
		mTestButton.setOnClickListener(this);
		
		bindService(new Intent(iMakedService.class.getName()), serviceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.preview_button: {
			Intent intent = new Intent(this, PreviewActivity.class);
			startActivity(intent);
		}
			break;

		case R.id.maked_button: {
			Intent intent = new Intent(this, MakedActivity.class);
			startActivity(intent);
		}
			break;
			
		case R.id.service_button: {
			Intent intent = new Intent(this, MakeServiceActivity.class);
			startActivity(intent);
		}
			break;
			
		case R.id.test_button: {
			Intent intent = new Intent(this, TestActivity.class);
			startActivity(intent);
		}
			break;
			
		default:
			break;
		}
	}
}
