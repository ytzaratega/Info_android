package com.yudi.infoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VisorPDFActivity extends AppCompatActivity {

    public final static long ONE_MEGABYTE = 1024 * 1024*20;

    private String libroNombre;
    private PDFView pdfView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_p_d_f);

        libroNombre = getIntent().getStringExtra("TITULO_LIBRO");

        pdfView = findViewById(R.id.pdfView);

        FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
        StorageReference mmFirebaseStorageRef = mFirebaseStorage.getReference().child("libros");


        mmFirebaseStorageRef.child(libroNombre).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                pdfView.fromBytes(bytes).load();
            }
        }).addOnFailureListener((e) ->{
            Toast.makeText(VisorPDFActivity.this, "download unsuccessful", Toast.LENGTH_LONG).show();

        });
    }
}