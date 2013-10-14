
package com.example.phonevibrator;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonevibrator.receiver.PhoneCallListener;

public class MainActivity extends Activity implements OnClickListener {

    private EditText et_second;

    public static int second;

    private Button ok;

    private Button cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        et_second = (EditText)findViewById(R.id.second);
        ok = (Button)findViewById(R.id.ok);
        cancle = (Button)findViewById(R.id.cancle);
        ok.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TelephonyManager telManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        switch (v.getId()) {
            case R.id.ok:
                second = Integer.parseInt(et_second.getText().toString());
                telManager.listen(new PhoneCallListener(MainActivity.this),
                        PhoneStateListener.LISTEN_CALL_STATE);
                break;
            case R.id.cancle:
                telManager.listen(new PhoneCallListener(MainActivity.this),
                        PhoneStateListener.LISTEN_NONE);
                break;
            default:
                break;
        }
    }
}
