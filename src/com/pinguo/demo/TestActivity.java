package com.pinguo.demo;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

public class TestActivity extends Activity implements SurfaceHolder.Callback,
		PreviewCallback, AutoFocusCallback {
	private int nScreenWidth;
	private int nScreenHeight;
	private DemoSurfaceView mPreviewSurfaceView;
	private SurfaceHolder mPreviewSurfaceHolder;
	private Camera mCamera;

	private List<Size> mSupportedPreviewSizes;
	private Size mPreviewSize;
	private byte[] mPreviewBuffer;

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
		
		Camera.Parameters params = mCamera.getParameters();
		params.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
		params.setPreviewFormat(ImageFormat.NV21);
		mCamera.setParameters(params);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.test_activity_layout);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		nScreenWidth = dm.widthPixels;

		nScreenHeight = dm.heightPixels;

		mPreviewSurfaceView = (DemoSurfaceView) findViewById(R.id.test_preview_surface_view);
		mPreviewSurfaceHolder = mPreviewSurfaceView.getHolder();
		mPreviewSurfaceHolder.addCallback(this);
		mPreviewSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void onAutoFocus(boolean result, Camera camera) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mCamera = Camera.open();

		if (mCamera != null) {
			try {

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
		}
	}
}
