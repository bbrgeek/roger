package com.example.thiery.callnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends BroadcastReceiver {


    Context mContext;


    @Override
    public void onReceive(Context mContext, Intent intent)
    {
        try
        {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);



            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
            {

                Toast.makeText(mContext, "Phone Is Ringing", Toast.LENGTH_LONG).show();
                // Your Code
            }
            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                Toast.makeText(mContext, "Call Recieved", Toast.LENGTH_LONG).show();
                // Your Code
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
            {
                Toast.makeText(mContext, "Phone Is Idle", Toast.LENGTH_LONG).show();
                // Your Code

            }
        }
        catch(Exception e)
        {
            //your custom message
        }

    }
}
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */
//}
