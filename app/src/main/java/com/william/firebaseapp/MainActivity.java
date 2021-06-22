package com.william.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.william.model.Upload;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Button btnlogout,getBtnstorage;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("uploads");
    private ArrayList<Upload> listaUploads = new ArrayList<>();

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

    @Override
    protected void onStart() {
        //onStart :
        /* - faz parte do cliclo de vida da Activity, depois do onCreate()
        * - É executado quando app inicia,
        *  - e quando volta da bacground
        * */
        super.onStart();
        getData();
    }
    public void getData(){
        //Listener p/ o nó uploads
        // - caso ocorra alguma alteração -> retorna TODOS od dadod!!
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for ( DataSnapshot no_filho :  snapshot.getChildren()){
                   Upload upload = no_filho.getValue(Upload.class);
                   listaUploads.add(upload);
                   Log.i("DATABASE","id: " + upload.getId() + ",nome: " + upload.getNomeImagem() );

               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
