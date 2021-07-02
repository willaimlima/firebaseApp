package com.william.firebaseapp.util;

import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.nio.channels.Channel;


    public  class App extends Application{
        private static final String  CHANNEL_1="ch_1";
    }
    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationsChannels();

    }

    private void createNotificationsChannels(){
        // verificando se  o celular tem API >= 26
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //criar canais de notificações
            NotificationChannel channel = new NotificationChannel(
                    App.CHANNEL_1, "Canal 1", NotificationManager.IMPORTANCE_HIGH

            );
            Channel.setDescription("Este é o canal 1");

            // registrar channal
            NotificationManager manager = getSystemService(NotificationManager.class)
                    manager.createNotificationChannel(channel);

        }
    }
}
