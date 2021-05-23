package com.yudi.infoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yudi.infoandroid.modelo.RegistroIdeasDiarias;

public class RegistroIdeas extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView ideas_diarias;
    private EditText editex_ideas_diarias;
    private TextView fecha;
    private Button guardar;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ideas);

        calendarView = findViewById(R.id.calendarView);
        ideas_diarias = findViewById(R.id.ideas_diarias);
        fecha = findViewById(R.id.text_fecha);
        editex_ideas_diarias = findViewById(R.id.editTextTextMultiLine_idea);
        //editex_ideas_diarias = findViewById(R.id.editTextNumberDecimal);
        guardar = findViewById(R.id.button_agregar);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Registro Ideas");


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String fecha_seleccionada = dayOfMonth+"-"+month+"-"+year;


                myRef.child(auth.getCurrentUser().getUid()).child(fecha_seleccionada).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        RegistroIdeasDiarias registroIdeasDiariasTemporal = snapshot.getValue(RegistroIdeasDiarias.class);
                        if (registroIdeasDiariasTemporal!=null){
                            ideas_diarias.setText(registroIdeasDiariasTemporal.getIdeaProducida()+"");
                            fecha.setText(registroIdeasDiariasTemporal.getFechaDía());
                        }else{
                            fecha.setText(fecha_seleccionada);
                            ideas_diarias.setText(0+"");

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardarIdeasDiarias(fecha.getText().toString(),(editex_ideas_diarias.getText().toString()+"") );


            }
        });
    }


    public void guardarIdeasDiarias (String fecha, String ideas){
        RegistroIdeasDiarias registroIdeasDiarias = new RegistroIdeasDiarias(fecha, ideas);
        if (auth.getCurrentUser()!=null){
            myRef.child(auth.getCurrentUser().getUid()).child(fecha).setValue(registroIdeasDiarias).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(RegistroIdeas.this, "Se ha guardado correctamente", Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegistroIdeas.this, "No se ha guardado correctamente", Toast.LENGTH_LONG).show();

                }
            });

        }



    }
}