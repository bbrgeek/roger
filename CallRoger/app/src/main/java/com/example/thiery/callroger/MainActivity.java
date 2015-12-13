package com.example.thiery.callroger;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button monButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monButton=(Button)findViewById(R.id.button);
        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 setContentView(R.layout.connexion_activity);
            }
        });

    /**
         * @kignelman
         *Recuperation et écoute des services d'appel du Téléphone
         *
         */
        TelephonyManager telephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyMgr.listen(new TeleListener(), PhoneStateListener.LISTEN_CALL_STATE);

    }

    /**
     * recuperation des infos du compte google
     * @kignelman
     */

    public String getUsername() {
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
            possibleEmails.add(account.name);
        }

        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
            String email = possibleEmails.get(0);
            String[] parts = email.split("@");
            if (parts.length > 0 && parts[0] != null) {
                EditText editText = (EditText) findViewById(R.id.editText);
                editText.setText(parts[0]);
                EditText editText1 = (EditText) findViewById(R.id.editText2);
                editText1.setText(email);
                return parts[0] + "   " + email;
            }
            else
                return null;
        } else
            return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Class pour ecouter les changer d'etat du telephone
     * concernant les appels
     */
    class TeleListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    String ok = getUsername();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // CALL_STATE_RINGING
                    showNotification();
                    break;
                default:
                    break;
            }
        }
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

    /**
     * Gestion des notifications
     */
    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_action_refresh)
                .setContentTitle(r.getString(R.string.notification_title))
                .setContentText("Voulez-vous décrocher avec RogerVoicer ?")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}