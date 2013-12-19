package com.pinguo.demo;

import us.pinguo.androidsdk.PGImageSDK;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MakedActivity extends Activity implements OnClickListener {
    private String key = "4F521E8A6AAB77CF386A10579F976115FAB1EBC3BB3A0E513D29F9BF65D87100225F7C8E717A1EF27ADF4EAC85E0544EBDCBE9A54B16C33D8858A204E76A6301E3393354FB58B9BB8CE028FABDAB13B9DF95179B463F5CC3C6EDB3BFC4D9E3501266D293F7989A00400AE86E9EEEB131B29A98256AFAA4D63C0BE324B96172C2C87497538D90211863D79E5949F2C1D8ABA5AB51172DCD001AABACD390F2654DC5167E79051D0EAD2CE218E8A6C246696DF6B776690AF23487720DFC8CA109590A4ED35D8DBCBA295C648650EF57175DB6C8525029DCF4EEB9DC6C74BCA12395EA8E562A7450EDDBB958BA78C70C70447463186E74B9B3D856773721710BFFE96652A00673133F94015FB35B3A6697ED17C8DB63AFEEEC8FFB30F06D1F316F9833C29012DAD8F6210C85596CE4EF1E8C5D9B650197B51CB91EC8B3140C5C10B36FBAA079B9ABAE31353215035DC8C1BDD563037AAF83C5BA80693AA8BCD8755B4DB9700B398A981CF3A1D8ED93909CFE484C16F8D0AD259450B1FD124A022A1DC316FB9571BD1244F0D152669800C7062489BAF26C1BF4113D50C6D03B05F623F0D152669800C70605D1BD9D5DF711BAB5BB54C1B1D4BD683BDA42AC4B7EE811F26BB9ECEEFA6F4C10DF49DA9D2F372E80693AA8BCD8755BDA89F023A8F72B7E912CFB43698223AFC5AC327F6B39B8A8E2577E8643446EDEC5AC327F6B39B8A864A1AA063847884C7648443AAB5C93099A188E3EFCB4B4B3EA909C90EE33284D27B096B2E443A27B04EBBA72030EAC0D99B8E1C13372949968A8CC2C675BD6D8314CF0D8EA56612F994C7A8CAA8BBD0FEBD8FFAC5FC1093E33417B502BD3F25E7CFA97F5641CFA819A56D82B918EC9AE4395E8E921F1348E2DCF2F9762105A0F299FC3461BCB6F69BB702A14E4E7A1FF374D1381064C68EB314CF0D8EA56612FECA6A2F66693E2C719CFC3EFC7A8D86D8B4AE31CC961BDE770CEE92160E5322A55219D8F755AC97F5CA5AB02BD7BCAF1D489A54A680CF0532305680BBD8702C46520AFB56BE383771EC8B3140C5C10B31D6122035DD30B39544E391B6F16DCF19C626F7F387FE671559A24D8E09B46979E1D4344B630F999F87859DFD71A23F497D11C2147BEBE2C92C83F463DEF1240A47D26F2030A719A8D52B473CF94E4A7C3FB578F4337F2ED664E4973B1FBF62FE8F9D3A2324950C87287F1AFF184EC9CECA6A2F66693E2C7DC140787AA6D061AC4C6446CA494E6076520AFB56BE383771EC8B3140C5C10B31D6122035DD30B39544E391B6F16DCF1BD620AAE21DA23BE559A24D8E09B46979E1D4344B630F999F87859DFD71A23F4EE7C3150EBB225400FA14F915135A701A47D26F2030A719A8D52B473CF94E4A7C3FB578F4337F2ED4A0D4557F6454564323EB1905B85C3B5559A24D8E09B46979E1D4344B630F999F87859DFD71A23F480CE81007641A26AA12827133BBB2158559A24D8E09B46973B76875ACCE89EA8DD3194531CAEF0BB57C825A7A820B54FEE430477CD4DF529";
    private Button mDemoButton;
    private Button mDemo2Button;
    private boolean running = false;
    
    protected void onResume() {
        super.onResume();
        running = true;
    }

    protected void onPause() {
        super.onPause();
        running = false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.maked_activity_layout);

        mDemoButton = (Button) findViewById(R.id.maked_demo_button);
        mDemoButton.setOnClickListener(this);

        mDemo2Button = (Button) findViewById(R.id.maked_demo_2_button);
        mDemo2Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.maked_demo_button:
            if (mDemoButton.isClickable()) {
                mDemoButton.setClickable(false);
                new Thread() {
                    public void run() {
                        PGImageSDK otherSDK = new PGImageSDK(MakedActivity.this, key, null);

                        DemoRenderer otherRenderer = new DemoRenderer();

                        otherRenderer.setRendererType(0);

//                        for (int i = 0; i < 10 && running; i++) {
                            otherRenderer.setLoadPath("/sdcard/gggg.jpg");

                            otherRenderer.setCurrentEffect("Effect=C360_Retro_Hazy");

                            otherRenderer.setSavePath("/sdcard/result_other.jpg");

                            otherSDK.renderActionWithWait(otherRenderer);
//                        }
                            
                        otherSDK.destroySDK();

                        mDemoButton.setClickable(true);
                    };
                }.start();
            }
            break;
        case R.id.maked_demo_2_button:
            if (mDemo2Button.isClickable()) {
                mDemo2Button.setClickable(false);
                new Thread() {
                    public void run() {
                        PGImageSDK otherSDK = new PGImageSDK(MakedActivity.this, key, null);

                        DemoRenderer otherRenderer = new DemoRenderer();

                        otherRenderer.setRendererType(0);

                        for (int i = 0; i < 50; i++) {
                            otherRenderer.setLoadPath("/sdcard/gggg.jpg");

                            otherRenderer.setCurrentEffect("Effect=C360_HDR_Soft");

                            otherRenderer.setSavePath("/sdcard/result_other_2_" + i + ".jpg");

                            otherSDK.renderActionWithWait(otherRenderer);
                        }
                        otherSDK.destroySDK();

                        mDemo2Button.setClickable(true);
                    };
                }.start();
            }
            break;
        default:
            break;
        }
    }
}
