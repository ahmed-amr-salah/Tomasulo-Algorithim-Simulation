package com.example.tomasulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

                int error =0;
                String text = InstructionsTextView.getText().toString();
                lines = text.split("\\n");
                for(String x :lines)
                {
                    if (verifyInstruction(x) == 1)
                    {
                        Log.e("AHMED","ERROR");
                        error=1;
                        break;
                    }
                    else
                        error=0;
                }
                if(error==1)
                {
                    Toast.makeText(InputPage.this,"Invalid Instruction Format", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(InputPage.this, simulation.class);
                    intent.putExtra("objectList", lines);
                    startActivity(intent);
                }


            }
        });
    }

    public int verifyInstruction(String instruction) {

        String instructions = instruction;
        Log.e("AHMED",instructions);
        String[] parts = instruction.split(" ");
        String opcode="";
        String format="";
        if (!(parts.length==0))
            opcode = parts[0];
        if(parts.length >= 2) {
            format = parts[1];
        }
        if (instructions.equals("RET"))
            return 0;
        // Check the first part before the space
        if (!opcode.matches("LOAD|STORE|BNE|JAL|ADD|ADDI|NEG|NAND|SLL")) {
            return 1;
        }
        else
        {
            Log.e("AHMED","SWITCH");
            // Check the format based on the opcode
            switch (opcode) {
                case "LOAD":
                case "STORE":
                    if (!format.matches("[a-zA-Z0-9]+,[a-zA-Z0-9]+\\([a-zA-Z0-9]+\\)")) {
                        return 1;
                    }
                    break;
                case "BNE":
                    if (!format.matches("[a-zA-Z0-9]+,[a-zA-Z0-9]+,[a-zA-Z0-9]+")) {
                        return 1;
                    }
                    break;
                case "JAL":
                    if (!format.matches("[a-zA-Z0-9]+")) {
                        return 1;
                    }
                    break;
                case "ADD":
                    if (!format.matches("[a-zA-Z0-9]+,[a-zA-Z0-9]+,[a-zA-Z0-9]+")) {
                        return 1;
                    }
                    break;
                case "ADDI":
                    if (!format.matches("[a-zA-Z0-9]+,[a-zA-Z0-9]+,[a-zA-Z0-9]+")) {
                        return 1;
                    }
                    break;
            }
        }

        // All conditions passed, return 0
        return 0;
    }
}