package com.tomato.fitvibe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSearch=findViewById(R.id.btnSearch);
        EditText t= findViewById(R.id.txtTime);
        EditText m= findViewById(R.id.txtMuscle);
        EditText l= findViewById(R.id.txtLocation);
        EditText e= findViewById(R.id.txtEquipment);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String time = t.getText().toString();
                String muscle = m.getText().toString();
                String location = t.getText().toString();
                String equipment = t.getText().toString();
                Intent intent=new Intent(MainActivity.this,Detailpage.class);
                startActivity(intent);
                intent.putExtra("Time",time);
                intent.putExtra("Muscle",muscle);
                intent.putExtra("Location",location);
                intent.putExtra("Equipment",equipment);
            }
        });
    }
}