package com.william.firebaseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

public class StorageActivity extends AppCompatActivity {
    //refencia FirebaseStorage
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private Button btnUpload,btnGaleria;
    private ImageView imageView;
    private Uri imageUri;
    private EditText editNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        btnUpload = findViewById(R.id.storage_btn_upload);
        imageView = findViewById(R.id.storage_image_cel);
        btnGaleria = findViewById(R.id.storege_btn_galeria);
        editNome = findViewById((R.id.storage_edit_nome);
        if(editNome.getText().toString().isEmpty()) {
            Toast.makeText(this,"Digite seu nome para imagem", Toast.LENGTH_LONG).show();
            return;
        }
        btnUpload.setOnClickListener(v -> {
            if (imageUri != null) {
                uploadImagemUri();
            } else {
                uploadImagemByte();
            }
        });

        btnGaleria.setOnClickListener( v ->{
            Intent intent = new Intent();
            // intent implicita -> para um arquivo de celular
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,112);
        });

    }

    private void uploadImagemUri(){
            String tipo = getFileExtension(imageUri);
            Date d = new Date();
            String nome = editNome.getText().toString()+"-"+d.getTime();
            StorageReference imagemRef = storage.getReference().child("imagens/"+nome+tipo);

            imagemRef.putFile(imageUri)
            .addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(this, "Upload com sucesso", Toast.LENGTH_LONG).show();
            })
        }

    private String getFileExtension(Uri imageUri) {
    });









        }
        // retonar  o tipo (.png, jpg) da imagem
        private String = getFileExtension(Uri imageUri){
            ContenResolver cr = getContentResolver();
            return MimeTypeMap.getSingleton().getExtensionFromMimeType(cr.getType(imageUri));
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("RESULT", "requestCode: "+ requestCode +"resultCode:"+ resultCode);
        if(requestCode==112 && resultCode== Activity.RESULT_OK) {
            //caso o usuario seleciinar umaimagem da galeria

            //endreço da imagem selecionada
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    public byte[] converterImage2Byte(ImageView imageView){
        //converter imagemView -> byte{}
        Bitmap bitmap = ( (BitmapDrawable) imageView.getDrawable() ).getBitmap();
        //objeto baos -> amazenar a imagem convertida
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        return baos.toByteArray();
    }
    // fazer um upload de uma imagem convertida p/ bytes
    public  void uploadImagemByte(){
        byte [] data = converterImage2Byte(imageView);

        //criar uma referencia p/ imagem no storage
        StorageReference imagemRef = storage.getReference().child("imagens/01.jpeg");
        //Realiza a opload da imagem
        imagemRef.putBytes(data)
        .addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(this, "Upload feito com sucesso!", Toast.LENGTH_SHORT).show();
            Log.i("UPLOAD", "Sucesso");
        })
        .addOnFailureListener(e -> {
            e.printStackTrace();
        })
        ;

        //storage.getReference().putBytes();

    }
}
