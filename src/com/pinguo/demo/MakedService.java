package com.pinguo.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import us.pinguo.androidsdk.PGImageSDK;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MakedService extends Service {
	private String key = "4F521E8A6AAB77CF386A10579F976115FAB1EBC3BB3A0E513D29F9BF65D87100225F7C8E717A1EF27ADF4EAC85E0544EBDCBE9A54B16C33D8858A204E76A6301E3393354FB58B9BB8CE028FABDAB13B9DF95179B463F5CC3C6EDB3BFC4D9E3501266D293F7989A00400AE86E9EEEB131B29A98256AFAA4D6A11C6EA29CC3B28678703303BD1E6FE8FA058232947AC3AF4BEFF84B4C04F9349C029274252158D4960EB16D16767E8C47C82A830C54F09CE1D7440D2584280F906BCF0277755231378C471ABF56CDE3FB30F06D1F316F983699A4796BB554881663A8C21C890A04ECFB8DA01251B1C130292C75ABB7F92DABA5AB51172DCD00C9EF5E55F0979F2B275B924DB8C3394C79F7029A34883118B30A1349387B4D5D20F100A5CBA97FFBF9AFD9FDF782FCC95AE8ECA0020A101B3006DB08CF6AD5767CFA97F5641CFA8182989776FDAFAA221F057BC073614575ED407FB82E35CF2E8AEE5D8C654A41BBF57B8C1368C8FD7E30D556AC9AC6E4D055219D8F755AC97F933E476189C654E415B402F9886FDC3027B096B2E443A27B025636E96E270E3158DD02F02F5FB994DE87F688E54A53BC07EA26355238DDC6F51B3AF4130FDDF5F048140009C27D71046DC90B4181C2EBEFF312ECF46A9C3EF36FCA2A2F396756583F032A1208E7E155219D8F755AC97FB807B724F8A9B08448A370DC6C76DF15B807B724F8A9B084B821E6DF7DA4917F53B27481DEA242BC06F72B1AE4E5639E1A74853BF4EDEFB4F4EB6BC45E2DE1C38DB242AC0AC94C98D3D00D9D2F5EF16544860653E9883471F4D56EEA71FD52971C5CC43C3725556EEE1973128F1CEDB7A8373A82FD32FD5FF0D152669800C70666A60B24603E6BE8FA2F927B275B6A00A9411D72C17851409AE056932273FA485C648650EF57175D6086578AF1993C586A8D29C81F7AFBD88D52B473CF94E4A7EC65E005628B6C70280F2CDCCC517B556520AFB56BE383771EC8B3140C5C10B31D6122035DD30B395AA4B95A2F6FE259A0E79F2CAF25D05AA47D26F2030A719A8D52B473CF94E4A7EC65E005628B6C708D429693944025B328535A27DA416A8EA47D26F2030A719A8D52B473CF94E4A7EC65E005628B6C7019EF80763C5C7FA59568FC1F78D96F0F7287F1AFF184EC9CECA6A2F66693E2C719CFC3EFC7A8D86D20354FAC42C8DB65EE5894FAEF9D99DF7CFA97F5641CFA81025E76F571E4977239D3302E3250CB983F5CF46A61F32D22559A24D8E09B46979E1D4344B630F999F87859DFD71A23F490EED700698413B5B3DEDD68ACF310F17CFA97F5641CFA81025E76F571E4977239D3302E3250CB98EE1F42A5FE30FFC3559A24D8E09B46979E1D4344B630F999F87859DFD71A23F4DCE0886A1ED8A48536B099045EEF25387CFA97F5641CFA81025E76F571E497721C71D7325D63FB4DCA3E3DEC0724A297A47D26F2030A719A8D52B473CF94E4A7DC95E60881DA32502D89C0F898EA590A6520AFB56BE383771EC8B3140C5C10B31D6122035DD30B395DA45B552EC4ADDE78982F428586E96C47017E1CCB45910B93E63FA8820959AE568352A9E960A8D208D2E76E30A0C4F05A84270E8FC60C44F0D152669800C70627BC63FB1E54FE880E236073AC2EBF3F318EA15F73BC767747017E1CCB45910B93E63FA8820959AE568352A9E960A8D2645470EE735BD71C70CEE92160E5322A005882D6D718A8A44E1B36E679EEC0E37E66ADD8B1D99816B7DF5FADD949ED08";

	private boolean bMakeing = false;
	private byte[] buffer;
	
	public class IMakedServiceMethod extends iMakedService.Stub {
		@Override
		public void demoFunction() throws RemoteException {
			// TODO Auto-generated method stub
			new Thread() {
				public void run() {
					Log.i("", "make start");
					
					PGImageSDK otherSDK = new PGImageSDK(getApplicationContext(), key, null);

					DemoRenderer otherRenderer = new DemoRenderer();

					otherRenderer.setRendererType(0);

					otherRenderer.setLoadPath("/sdcard/gggg.jpg");

					otherRenderer.setCurrentEffect("Effect=C360_LOMO_Warm");

					otherRenderer.setSavePath("/sdcard/result_other.jpg");

					otherSDK.renderActionWithWait(otherRenderer);

					otherSDK.destroySDK();
					
					Log.i("", "make stop");
				};
			}.start();
		}

		@Override
		public void demoFunction100() throws RemoteException {
			// TODO Auto-generated method stub
//			if (bMakeing) {
//				return;
//			}
			
			new Thread() {
				public void run() {
					bMakeing = true;
					
					for (int i = 0; i < 100; ++ i) {
						Log.w("", Thread.currentThread().getId() + " make picture : " + i);
						
						PGImageSDK otherSDK = new PGImageSDK(getApplicationContext(), key, null);
//						File file = new File("/sdcard/load_background.jpg");
//						try {
//							InputStream input = new FileInputStream(file);
//
//							byte[] buffer = new byte[(int)file.length()];
//							
//							input.read(buffer);
//							
//							input.close();
//							
//							otherSDK.reloadResource(buffer);
//						} catch (FileNotFoundException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						
						DemoRenderer otherRenderer = new DemoRenderer();
	
						otherRenderer.setRendererType(0);
	
						otherRenderer.setLoadPath("/sdcard/gggg.jpg");
	
						otherRenderer.setCurrentEffect("Effect=C360_LOMO_Warm");
	
						otherRenderer.setSavePath("/sdcard/demo/result_new_" + i + ".jpg");
	
						otherSDK.renderActionWithWait(otherRenderer);
	
						otherSDK.destroySDK();
						
						Log.i("", Thread.currentThread().getId() + " make picture : " + i + " over");
					}
					bMakeing = false;
				};
			}.start();
		}

		@Override
		public void makePicture(final byte[] jpeg) throws RemoteException {
			// TODO Auto-generated method stub
			new Thread() {
				public void run() {
					PGImageSDK sdk = new PGImageSDK(getApplicationContext(), key, null);
					
					MakedRenderer make = new MakedRenderer();
					
					make.setBuffer(jpeg);
					
					sdk.renderActionWithWait(make);
					
					sdk.destroySDK();
				};
			}.start();
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.w("", "onBind");
		
		return new IMakedServiceMethod();
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.w("", "onStartCommand");
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void onDestroy() {
		Log.w("", "onDestroy");
		
		super.onDestroy();
	}

	public void onCreate() {
		Log.w("", "onCreate");
		
		super.onCreate();
	}
}
