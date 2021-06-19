package com.william.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Button btnlogout,getBtnstorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogout = findViewById(R.id.main_btn_logout);
        getBtnstorage = findViewById(R.id.main_btn_storage);
        getBtnstorage.setOnClickListener(v ->{
            //abrir StarageActivity
            Intent intent = new Intent(getApplicationContext(),StorageActivity.class);
            startActivity(intent);
        });
        btnlogout.setOnClickListener( v ->{
            // deslogar usuario
            auth.signOut();
            finish();
        });
       TextView textEmail =findViewById(R.id.main_text_email);
        textEmail.setText(auth.getCurrentUser().getEmail());


        TextView textNome =findViewById(R.id.main_text_user);
        textNome.setText(auth.getCurrentUser().getDisplayName());

    }
}
