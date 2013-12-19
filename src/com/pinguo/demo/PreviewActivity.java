package com.pinguo.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import us.pinguo.androidsdk.PGGLSurfaceView;
import us.pinguo.androidsdk.PGImageSDK;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Bitmap.Config;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class PreviewActivity extends Activity implements SurfaceHolder.Callback, PreviewCallback, AutoFocusCallback, PictureCallback {
	private String key = "4F521E8A6AAB77CF386A10579F976115FAB1EBC3BB3A0E513D29F9BF65D87100225F7C8E717A1EF27ADF4EAC85E0544EBDCBE9A54B16C33D8858A204E76A6301E3393354FB58B9BB8CE028FABDAB13B9DF95179B463F5CC3C6EDB3BFC4D9E3501266D293F7989A00400AE86E9EEEB131B29A98256AFAA4D6A11C6EA29CC3B28678703303BD1E6FE8FA058232947AC3AF4BEFF84B4C04F9349C029274252158D4960EB16D16767E8C47C82A830C54F09CE1D7440D2584280F906BCF0277755231378C471ABF56CDE3FB30F06D1F316F983699A4796BB554881663A8C21C890A04ECFB8DA01251B1C130292C75ABB7F92DABA5AB51172DCD00C9EF5E55F0979F2B275B924DB8C3394C79F7029A34883118B30A1349387B4D5D20F100A5CBA97FFBF9AFD9FDF782FCC95AE8ECA0020A101B3006DB08CF6AD5767CFA97F5641CFA8182989776FDAFAA221F057BC073614575ED407FB82E35CF2E8AEE5D8C654A41BBF57B8C1368C8FD7E30D556AC9AC6E4D055219D8F755AC97F933E476189C654E415B402F9886FDC3027B096B2E443A27B025636E96E270E3158DD02F02F5FB994DE87F688E54A53BC07EA26355238DDC6F51B3AF4130FDDF5F048140009C27D71046DC90B4181C2EBEFF312ECF46A9C3EF36FCA2A2F396756583F032A1208E7E155219D8F755AC97FB807B724F8A9B08448A370DC6C76DF15B807B724F8A9B084B821E6DF7DA4917F53B27481DEA242BC06F72B1AE4E5639E1A74853BF4EDEFB4F4EB6BC45E2DE1C38DB242AC0AC94C98D3D00D9D2F5EF16544860653E9883471F4D56EEA71FD52971C5CC43C3725556EEE1973128F1CEDB7A8373A82FD32FD5FF0D152669800C70666A60B24603E6BE8FA2F927B275B6A00A9411D72C17851409AE056932273FA485C648650EF57175D6086578AF1993C586A8D29C81F7AFBD88D52B473CF94E4A7EC65E005628B6C70280F2CDCCC517B556520AFB56BE383771EC8B3140C5C10B31D6122035DD30B395AA4B95A2F6FE259A0E79F2CAF25D05AA47D26F2030A719A8D52B473CF94E4A7EC65E005628B6C708D429693944025B328535A27DA416A8EA47D26F2030A719A8D52B473CF94E4A7EC65E005628B6C7019EF80763C5C7FA59568FC1F78D96F0F7287F1AFF184EC9CECA6A2F66693E2C719CFC3EFC7A8D86D20354FAC42C8DB65EE5894FAEF9D99DF7CFA97F5641CFA81025E76F571E4977239D3302E3250CB983F5CF46A61F32D22559A24D8E09B46979E1D4344B630F999F87859DFD71A23F490EED700698413B5B3DEDD68ACF310F17CFA97F5641CFA81025E76F571E4977239D3302E3250CB98EE1F42A5FE30FFC3559A24D8E09B46979E1D4344B630F999F87859DFD71A23F4DCE0886A1ED8A48536B099045EEF25387CFA97F5641CFA81025E76F571E497721C71D7325D63FB4DCA3E3DEC0724A297A47D26F2030A719A8D52B473CF94E4A7DC95E60881DA32502D89C0F898EA590A6520AFB56BE383771EC8B3140C5C10B31D6122035DD30B395DA45B552EC4ADDE78982F428586E96C47017E1CCB45910B93E63FA8820959AE568352A9E960A8D208D2E76E30A0C4F05A84270E8FC60C44F0D152669800C70627BC63FB1E54FE880E236073AC2EBF3F318EA15F73BC767747017E1CCB45910B93E63FA8820959AE568352A9E960A8D2645470EE735BD71C70CEE92160E5322A005882D6D718A8A44E1B36E679EEC0E37E66ADD8B1D99816B7DF5FADD949ED08";
	
	private SurfaceView mPreviewSurfaceView;
	private int nScreenWidth;
	private int nScreenHeight;
	private SurfaceHolder mPreviewSurfaceHolder;
	private Camera mCamera;
	private PGGLSurfaceView mDisplaySurfaceView;

	private TextView mFramesCount;

	protected void onStart() {
		super.onStart();
	}

	protected void onStop() {
		super.onStop();
	}
	
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
	
	public void onDestroy() {
		super.onDestroy();
		
		unbindService(serviceConnection);
	}
	
	private boolean bEffect = false;
	private boolean bScene = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.preview_activity_layout);
		
		bindService(new Intent(iMakedService.class.getName()), serviceConnection, Context.BIND_AUTO_CREATE);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		nScreenWidth = dm.widthPixels;

		nScreenHeight = dm.heightPixels;

		mPreviewSurfaceView = (SurfaceView) findViewById(R.id.preview_surface);
		mPreviewSurfaceHolder = mPreviewSurfaceView.getHolder();
		mPreviewSurfaceHolder.addCallback(this);
		mPreviewSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		mDisplaySurfaceView = (PGGLSurfaceView) findViewById(R.id.gl_preview_surface);
		
		mFramesCount = (TextView) findViewById(R.id.preview_frame_count);
		
		Button mEffectButton = (Button) findViewById(R.id.change_effect_button);
		mEffectButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if (bEffect)
					mPreviewRenderer.setCurrentEffect("Effect=C360_LOMO_Film");
				else
					mPreviewRenderer.setCurrentEffect("Effect=C360_HDR_Soft");
				
				bEffect = !bEffect;
			}
		});
		
		Button mSceneButton = (Button) findViewById(R.id.scene_effect_button);
		mSceneButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (bScene) {
					
				}
				else {
					
				}
				
				bScene =!bScene;
			}
		});
		
		Button mTakeButton = (Button) findViewById(R.id.tabe_picture_button);
		mTakeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCamera.takePicture(null, null, PreviewActivity.this);
			}
		});
		
		Button mGoToActivity = (Button) findViewById(R.id.go_to_make_other_activity);
		mGoToActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PreviewActivity.this, MakeServiceActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	// /////////////////////////////////////////////////////////////////////////////
	private int getDisplayRotation() {
		int rotation = this.getWindowManager().getDefaultDisplay()
				.getRotation();

		switch (rotation) {
		case Surface.ROTATION_0:
			return 0;
		case Surface.ROTATION_90:
			return 90;
		case Surface.ROTATION_180:
			return 180;
		case Surface.ROTATION_270:
			return 270;
		}
		return 0;
	}

	private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
		final double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) w / h;
		if (sizes == null)
			return null;

		Size optimalSize = null;
		double minDiff = Double.MAX_VALUE;

		int targetHeight = h;
		// Try to find an size match aspect ratio and size
		for (Size size : sizes) {
			double ratio = (double) size.width / size.height;

			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
				continue;

			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}
		// Cannot find the one match the aspect ratio, ignore the requirement
		if (optimalSize == null) {
			minDiff = Double.MAX_VALUE;

			for (Size size : sizes) {
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}
		}

		return optimalSize;
	}

	private List<Size> mSupportedPreviewSizes;
	private Size mPreviewSize;
	private byte[] mPreviewBuffer;
	private PGImageSDK mImageSDK;
	private DemoRenderer mPreviewRenderer;

	public void setPreviewSize(int width, int height) throws IOException {
		if (mSupportedPreviewSizes != null) {
			mSupportedPreviewSizes.clear();
			mSupportedPreviewSizes = null;
		}

		mSupportedPreviewSizes = mCamera.getParameters()
				.getSupportedPreviewSizes();
		mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width,
				height);// 这里是竖屏设置

		Log.w("", "Camera Info : " + mPreviewSize.width + " x "
				+ mPreviewSize.height + " (" + nScreenWidth + " x "
				+ nScreenHeight + ")");
		
		mPreviewBuffer = new byte[mPreviewSize.width * mPreviewSize.height * 2];
		
		mPreviewRenderer.setDisplayRect(0, 0, nScreenWidth, nScreenHeight);
		
		Camera.Parameters params = mCamera.getParameters();
		params.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
		params.setPictureSize(1600, 1200);
		params.setPreviewFormat(ImageFormat.NV21);
		mCamera.setParameters(params);
	}
	////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.e("", "Surface Created");
		
		mCamera = Camera.open();

		if (mCamera != null) {
			try {
				mPreviewRenderer = new DemoRenderer();
				
				mPreviewRenderer.setCurrentEffect("Effect=C360_LOMO_Film");
				mPreviewRenderer.setRendererType(1);
				
				mImageSDK = new PGImageSDK(this, key, mDisplaySurfaceView);
				
				setPreviewSize(nScreenWidth, nScreenHeight);
				
				mCamera.addCallbackBuffer(mPreviewBuffer);
				mCamera.setPreviewCallbackWithBuffer(this);
				mCamera.setPreviewDisplay(mPreviewSurfaceHolder);
				
				mCamera.startPreview();
				
				Log.w("", "Preview Create");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (mCamera != null) {
			Log.w("", "Preview Destroy");
			
			mCamera.release();
			
			mImageSDK.destroySDK();
		}
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		if (!mImageSDK.getRendering()) {
			mPreviewRenderer.setYUVData(mPreviewBuffer, mPreviewSize.width, mPreviewSize.height);
//			
			 mImageSDK.renderAction(mPreviewRenderer);
//			 
			 mFramesCount.setText("Frames : " + mImageSDK.getFrameCount());
		}
		else {
			
		}
		
		 mCamera.addCallbackBuffer(mPreviewBuffer);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mCamera != null) {
			mCamera.autoFocus(this);
		}
		
		return false;
	}

	@Override
	public void onAutoFocus(boolean result, Camera camera) {
		// TODO Auto-generated method stub
		if (result) {
			
		}
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		try {
			method.makePicture(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		camera.startPreview();
	}
}