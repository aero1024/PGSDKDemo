package com.pinguo.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MakeServiceActivity extends Activity {
	private iMakedService method = null;
	
	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub

		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			method = iMakedService.Stub.asInterface(arg1);
		}
	};

	private Button mMakeButton;
	
	public void onDestroy() {
		super.onDestroy();
		
		unbindService(serviceConnection);
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.make_service_activity_layout);
		
//		startService(new Intent(iMakedService.class.getName()));//可以防止Destroy
		bindService(new Intent(iMakedService.class.getName()), serviceConnection, Context.BIND_AUTO_CREATE);
		
		mMakeButton = (Button) findViewById(R.id.make_service_button);
		
		mMakeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				try {
//					method.demoFunction();
					method.demoFunction100();
					
//					finish();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
