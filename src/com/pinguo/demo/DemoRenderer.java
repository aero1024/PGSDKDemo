package com.pinguo.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import us.pinguo.androidsdk.PGColorBuffer;
import us.pinguo.androidsdk.PGRendererMethod;

public class DemoRenderer extends PGRendererMethod {
	private int type = 0;// 0 为普通做图方式，1 为实时预览

	public void setRendererType(int value) {
		type = value;
	}

	private String strEffect;

	public void setCurrentEffect(String value) {
		strEffect = value;
	}

	private String strLoadPath;

	public void setLoadPath(String path) {
		strLoadPath = path;
	}

	private String strSavePath;

	public void setSavePath(String path) {
		strSavePath = path;
	}

	private byte[] mYUVBuffer;
	private int mYUVWidth;
	private int mYUVHeight;

	public void setYUVData(byte[] buffer, int width, int height) {
		mYUVBuffer = buffer;
		mYUVWidth = width;
		mYUVHeight = height;
	}

	private int left;
	private int top;
	private int right;
	private int bottom;

	public void setDisplayRect(int l, int t, int r, int b) {
		left = l;
		top = t;
		right = r;
		bottom = b;
	}

	@Override
	public void rendererAction() {
		// TODO Auto-generated method stub
		this.setBackground(0.0f, 0.0f, 0.0f, 0.0f);
		
		this.renderType(EM_MAKE_TYPE.RENDER_NORMAL);

		if (type == 0) {
			if (!this.setImageFromPath(0, strLoadPath)) {
				Log.e("", "setImageFromPath Failed");

				return;
			}
			
//			if (!this.adjustImage(0, true, 90, null, false, false, 0)) {
//				Log.e("", "adjust failed");
//				return;
//			}
			
			if (!this.setAutoClearShaderCache(false)) {
				Log.e("", "setAutoClearShaderCache Failed");
				
				return;
			}
			
//			Log.i("", "current Thread : " + Thread.currentThread().getId());

			if (!this.setEffect(strEffect)) {
				Log.e("", "setEffect Failed : " + Thread.currentThread().getId());

				return;
			}

			if (!this.make()) {
				Log.e("", "make Failed");

				return;
			}
			
			PGColorBuffer buffer = this.getMakedImagePreview(800);
			
			if (buffer != null) {
				File f = new File(strSavePath);
				
				try {
					if (f.exists()) {
						f.delete();
					}
					
					f.createNewFile();
					
					OutputStream output = new FileOutputStream(f);
					
					Bitmap bitmap = Bitmap.createBitmap(buffer.getColorBuffer(), buffer.getImageWidth(), buffer.getImageHeight(), Config.ARGB_8888);
					
					bitmap.compress(Bitmap.CompressFormat.JPEG, 95, output);
					
					output.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}

//			if (!this.getMakedImage2JpegFile(strSavePath, 95)) {
//				Log.e("", "getMakedImage2JpegFile Failed");
//
//				return;
//			}

			Log.w("", "rendering over");
		} else {
			// TODO Auto-generated method stub
			if (!this.setAutoClearShaderCache(false)) {
				Log.e("", "setAutoClearShaderCache Failed");
				
				return;
			}
			
			if (!this
					.setImageFromYUV420SP(0, mYUVBuffer, mYUVWidth, mYUVHeight)) {
				Log.e("", "setImageFromYUV failed");

				return;
			}

			if (!this.setEffect(strEffect)) {
				Log.e("", "setEffect failed");

				return;
			}
			
			if (!this.make()) {
				Log.e("", "make failed");
				
				return;
			}
			
			if (!this.getMakedImage2Screen(7, left, top, right, bottom)) {
				Log.e("", "getMakedImage2Screen failed");

				return;
			}
		}

	}
}
