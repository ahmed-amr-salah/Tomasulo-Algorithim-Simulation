package com.example.tomasulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InputPage extends AppCompatActivity {
    TextView InstructionsTextView;
    Button StartSimulation;
    String[] lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);
        getSupportActionBar().hide();
        InstructionsTextView =findViewById(R.id.Instructions);
        StartSimulation = findViewById(R.id.button2);


        StartSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String text = InstructionsTextView.getText().toString();
                lines = text.split("\\n");

                Intent intent = new Intent(InputPage.this,simulation.class);
                intent.putExtra("objectList",lines);
                startActivity(intent);


            }
        });
    }


}


