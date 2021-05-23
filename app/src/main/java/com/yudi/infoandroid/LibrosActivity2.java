package com.yudi.infoandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class LibrosActivity2 extends AppCompatActivity {

    private ListView listView;

    private ArrayList <String> libros;

    private StorageReference mStorageRef;

   // private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros2);








        listView = findViewById(R.id.lisviewLibros);
        libros = new ArrayList<>();

        //inicializar objeto de Firebase Storage
        mStorageRef = FirebaseStorage.getInstance().getReference();
        //Traigo la referencia el bucke donde tengo guardados los libros pdf en Firebase

        StorageReference ref =mStorageRef.child("libros");
        // traer todos los titulos de los libros

        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                Log.e("libros", "Entrando a recuperar libros");
                for (StorageReference item: listResult.getItems()) {
                    libros.add(item.getName()+"");


                }
                    //configuración adaptador de la lista
                    ArrayAdapter <String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, libros);
                    //se muestra adaptador en la vista
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener((parent, view, position, id) -> {

                        final String titulo_libro_seleccionado = listView.getItemAtPosition(position).toString();
                        StorageReference islanRef = mStorageRef.child(titulo_libro_seleccionado);
                        Intent i = new Intent(getApplicationContext(), VisorPDFActivity.class);
                        i.putExtra("TITULO_LIBRO", titulo_libro_seleccionado);
                        startActivity(i);
                    });

            }
        }).addOnFailureListener(
                (e) -> {
                  AlertDialog.Builder builder1 = new AlertDialog.Builder(LibrosActivity2.this);
                  builder1.setMessage("Ha ocurrido un error al cargar los libros, verifique la conexión");
                  builder1.setCancelable(true);
                  builder1.setPositiveButton(
                          "OK",
                          new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int id) {
                                  dialog.cancel();

                                  }
                          });
                  AlertDialog alert11 = builder1.create();
                  alert11.show();

        });

    }
}