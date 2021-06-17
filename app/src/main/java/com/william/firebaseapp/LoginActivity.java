package com.william.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnlogin;
    private EditText editEmail,editSenha;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnCadastrar = findViewById(R.id.login_btn_Cadastrar);
        btnlogin = findViewById(R.id.login_btn_logar);
        editEmail = findViewById(R.id.login_edit_email);
        editSenha = findViewById(R.id.login_edit_senha);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener( View ->{
            logar();
        });
    }
    public void logar(){
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        if(email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Preencha os campos",Toast.LENGTH_SHORT).show();
            return;
        }
        //t -> é uma tarefa para logar
        Task<AuthResult> = auth.signInWithEmailAndPassword(email,senha);
        // listener de sucesso
        t.addOnSuccessListener(authResult ->{
           Toast.makeText(this, "bem vindo", Toast.LENGTH_LONG).show();
           Intent intent = new Intent(getApplicationContext(),MainActivity.class);
           startActivity(intent);
        });
        // listener de falha
        t.addOnFailureListener( e -> {
            // parametro e -> exception
            Toast.makeText(this, "Erro!", Toast.LENGTH_LONG).show();
        });
    }
}
