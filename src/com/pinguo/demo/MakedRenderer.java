package com.pinguo.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;

import us.pinguo.androidsdk.PGColorBuffer;
import us.pinguo.androidsdk.PGRendererMethod;
import us.pinguo.androidsdk.PGRendererMethod.EM_MAKE_TYPE;

@SuppressLint("SdCardPath")
public class MakedRenderer extends PGRendererMethod {
	private byte[] buffer;

	public void setBuffer(byte[] m) {
		buffer = m;
	}

	@Override
	public void rendererAction() {
		// TODO Auto-generated method stub
		// renderType(EM_MAKE_TYPE.RENDER_SCENE);
		// //1. 设置图片
		// boolean success =setImageFromPath(0, "/sdcard/gggg.jpg");
		//
		// //3.设置情景制图参数
		// loadTemplate("/sdcard/Camera360/scene/20130730172701.png",
		// "type=0;cutscale=650:321;cutdirect=5;pos=0,0,650,0,650,316,0,316;Effect=Normal;rotate=1");
		// success = loadTemplateSuccess();
		// if (!success) {
		// Log.e("", "loadTemplateSuccess Failed");
		// return;
		// }
		//
		// //4.开始制图
		// success = make();
		// if (!success) {
		// Log.e("", "Make Failed");
		// return;
		// }
		//
		// getMakedImage2JpegFile("/sdcard/dddddd.jpg", 95);
		//
		// clearImage(0);
		this.setBackground(0.0f, 0.0f, 0.0f, 0.0f);

		this.renderType(EM_MAKE_TYPE.RENDER_NORMAL);

		if (!this.setImageFromJPEG(0, buffer)) {
			Log.e("", "setImageFromPath Failed");

			return;
		}

		if (!this.setAutoClearShaderCache(false)) {
			Log.e("", "setAutoClearShaderCache Failed");

			return;
		}

		// Log.i("", "current Thread : " + Thread.currentThread().getId());

		if (!this.setEffect("Effect=C360_LOMO_Film")) {
			Log.e("", "setEffect Failed : " + Thread.currentThread().getId());

			return;
		}

		if (!this.make()) {
			Log.e("", "make Failed");

			return;
		}

		PGColorBuffer buffer = this.getMakedImagePreview(800);

		if (buffer != null) {
			File f = new File("/sdcard/demo/" + System.currentTimeMillis() + ".jpg");

			try {
				if (f.exists()) {
					f.delete();
				}

				f.createNewFile();

				OutputStream output = new FileOutputStream(f);

				Bitmap bitmap = Bitmap.createBitmap(buffer.getColorBuffer(),
						buffer.getImageWidth(), buffer.getImageHeight(),
						Config.ARGB_8888);

				bitmap.compress(Bitmap.CompressFormat.JPEG, 95, output);

				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Log.w("", "rendering over");
	}
}
