package com.yudi.infoandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth;
    private Button login;
    private Button registrar;
    private Button cerrarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.iniciarSesionPantalla);
        registrar = findViewById(R.id.registrarPantalla);
        cerrarSesion = findViewById(R.id.cerrarSesion_btn);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            login.setVisibility(View.GONE);
            registrar.setVisibility(View.GONE);
            cerrarSesion.setVisibility(View.VISIBLE);
        }

    }

    public void irInciar(View view){
        Intent i = new Intent(this,IniciarSesionActivity.class);
        startActivity(i);
    }

    public void irRegistrarse(View view){
        Intent i = new Intent(this,RegistrarseActivity.class);
        startActivity(i);

    }

    public void cerrarSesion (View view){
        auth.signOut();
        if(auth.getCurrentUser()==null){
            login.setVisibility(View.VISIBLE);
            registrar.setVisibility(View.VISIBLE);
            cerrarSesion.setVisibility(View.GONE);

        }


    }


    public void irLibros(View view){
        if (auth.getCurrentUser()!=null){
            Intent i = new Intent(this,LibrosActivity2.class);
            startActivity(i);
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesión para acceder a los libros gratis");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "OK",
                    (dialog, id) -> { dialog.cancel();});
                   AlertDialog alert1 = builder1.create();
                   alert1.show();

        }

    }

    public void irRegistroIdeas(View view){
        if (auth.getCurrentUser()!=null){
            Intent i = new Intent(this,RegistroIdeas.class);
            startActivity(i);
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesión para registrar ideas");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "OK",
                    (dialog, id) -> { dialog.cancel();});
            AlertDialog alert1 = builder1.create();
            alert1.show();

        }

    }




    public void irIntroduccion(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://es.wikipedia.org/wiki/Android"));
        startActivity(i);

    }

    public void irNoticias(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.xatakandroid.com"));
        startActivity(i);

    }

    public void irTips(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.android.com/auto/#next-steps"));
        startActivity(i);

    }

    public void irCursos(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://elestudiantedigital.com/cursos-online-gratis-android/"));
        startActivity(i);

    }




}