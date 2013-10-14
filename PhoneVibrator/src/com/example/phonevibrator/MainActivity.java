
package com.example.phonevibrator;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phonevibrator.receiver.PhoneCallListener;

public class MainActivity extends Activity implements OnClickListener {

    private EditText et_second;

    public static int second;

    private Button ok;

    private Button cancle;

    private CheckBox cb_boot;

    private ComponentName mComponentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mComponentName = new ComponentName(this.getPackageName(),
                "com.example.phonevibrator.receiver.BootCompleteReceiver");
        initUI();
    }

    private void initUI() {
        et_second = (EditText)findViewById(R.id.second);
        ok = (Button)findViewById(R.id.ok);
        cancle = (Button)findViewById(R.id.cancle);
        cb_boot = (CheckBox)findViewById(R.id.rb_boot);
        int autoBoot = getPackageManager().getComponentEnabledSetting(mComponentName);
        if (autoBoot == PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            cb_boot.setChecked(false);
        } else {
            cb_boot.setChecked(true);
        }
        cb_boot.setOnClickListener(this);
        ok.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TelephonyManager telManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        switch (v.getId()) {
            case R.id.ok:
                String text = et_second.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(MainActivity.this, getString(R.string.plz_input_second), 0)
                            .show();
                    return;
                }
                second = Integer.parseInt(text);
                telManager.listen(new PhoneCallListener(MainActivity.this),
                        PhoneStateListener.LISTEN_CALL_STATE);
                break;
            case R.id.cancle:
                telManager.listen(new PhoneCallListener(MainActivity.this),
                        PhoneStateListener.LISTEN_NONE);
                break;
            case R.id.rb_boot:
                if (cb_boot.isChecked()) {
                    getPackageManager().setComponentEnabledSetting(mComponentName,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                } else {
                    getPackageManager().setComponentEnabledSetting(mComponentName,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                }
                break;
            default:
                break;
        }
    }
}
