package com.william.firebaseapp.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.Navigation;

import com.clemilton.firebaseappsala.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.william.firebaseapp.NavigationActivity;
import com.william.firebaseapp.model.User;

import static com.william.firebaseapp.util.App.CHANNEL_1;

public class NotificationService extends Service {
    private ValueEventListener listener;

    @Nullable
    public void  onCreate(){
        super.onCreate();

        public void showNotify(User user){
            // criando a notificação
            Navigation notification = new NavigationCompat.Builder(getApplicationContext(), CHANNEL_1)
                    .setSmallicon(R.drawable.ic_account_circle_black_24dp)
                    .setContentTitle("Alteração!")
                    .setContentText(user.getNome())
                    .setPriority(Notification.PRIORITY_HIGH)
                    .build();
            //enviando para channel
            NotificationManager nm = (NotificationManager) getApplicationContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(1, notification);
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
