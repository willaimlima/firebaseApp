package com.william.firebaseapp.fragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.clemilton.firebaseappsala.R;
import com.william.firebaseapp.NavigationActivity;
import com.william.firebaseapp.util.NotificationReceiver;

import static com.william.firebaseapp.util.App.CHANNEL_1;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    private NotificationManagerCompat notificationManager;

    public NotificationFragment() {
        // Construtor público vazio obrigatório
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar o layout para este fragmento
        View layout =  inflater.inflate(R.layout.fragment_notification, container, false);
        notificationManager = NotificationManagerCompat.from(getContext());

        EditText editTitle = layout.findViewById(R.id.frag_notification_title);
        EditText editMsg = layout.findViewById(R.id.frag_notification_msg);
        Button  btnSend = layout.findViewById(R.id.frag_notificaion_btn_send);
        btnSend.setOnClickListener( v -> {
            String title = editTitle.getText().toString();
            String msg = editMsg.getText().toString();

            Intent intent = new Intent(getContext(), NavigationActivity.class);
            /*val pendingIntent = NavDeepLinkBuilder(context)
                    .setComponentName(YourActivity::class.java)
                     .setGraph(R.navigation.your_nav_graph)
                    .setDestination(R.id.your_destination)
                    .setArguments(bundle)
                    .createPendingIntent()*/
            PendingIntent contentIntent = new NavDeepLinkBuilder(getContext())
                    .setComponentName(NavigationActivity.class)
                    .setGraph(R.navigation.nav_graph)
                    .setDestination(R.id.nav_menu_lista_imagens)
                    .createPendingIntent();

            /*
            PendingIntent contentIntent = PendingIntent.getActivity(
                                            getContext(),
                                            0,
                                            intent,
                                            0
            );*/
            //criar um broadcast receiver ->
            // - ele deve ser ativado EXPLICITAMENTE!

            // - não deve durar mas de 10sg
            Intent broadcastIntent = new Intent(getContext(), NotificationReceiver.class);
            broadcastIntent.putExtra("toast",msg);

            PendingIntent actionIntent = PendingIntent.getBroadcast(
                    getContext(),0,broadcastIntent,PendingIntent.FLAG_CANCEL_CURRENT
            );



            //Criar a notificação
            Notification notification = new NotificationCompat
                    .Builder(getContext(),CHANNEL_1)
                    .setSmallIcon(R.drawable.ic_account_circle_black_24dp)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(contentIntent)
                    .addAction(R.drawable.ic_account_circle_black_24dp,
                            "Toast", actionIntent)
                    .build();
            notificationManager.notify(1,notification);
        });




        return layout;
    }
}