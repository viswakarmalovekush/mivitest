package mivi.com.mivitestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends Activity {

    final int splashTimeOut = 3 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginAct=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(loginAct);
            }
        }, splashTimeOut);
    }
}
