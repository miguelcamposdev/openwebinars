package com.salesianostriana.dam.di.apptalkme.notifications;


import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GCMIntentService extends IntentService {
    public GCMIntentService() {
        super("GCMIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if (extras != null && !extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Logger.getLogger("GCM_RECEIVED").log(Level.INFO, extras.toString());

                Log.i("JSON TALKME", "JSON: " + extras.getString("notificacion"));
                JSONObject jsonTiempo;
                try {
                    jsonTiempo = new JSONObject(extras.getString("notificacion"));
                    String mensajeRecibido = jsonTiempo.getString("mensaje");
                    String nickname = jsonTiempo.getString("nickname");
                    showNotificacion(mensajeRecibido, nickname);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }


    protected  void showNotificacion(final String tiempo, final String nickname) {
        NotificationCompat.Builder mBuilder;

        int mNotificationId = 1;
        NotificationManager mNotifyMgr = null;

        mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        Log.i("SERVICIO", "NOTIFICACION: " + tiempo);


        mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("De: "+ nickname)
                        .setContentText(tiempo);

        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}