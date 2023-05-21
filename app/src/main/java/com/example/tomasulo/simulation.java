package com.example.tomasulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class simulation extends AppCompatActivity {
    List<Instruction> ListOfInstructions;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);
        getSupportActionBar().hide();
        TableLayout tableLayout = findViewById(R.id.tableLayout);
         // Default row count (can be customized)
        List<String> stringList = new ArrayList<>();
        Intent intent = getIntent();
        textView = findViewById(R.id.textView2);
        button = findViewById(R.id.button2);
        String [] myList = intent.getStringArrayExtra("objectList");
        for (String line : myList) {
            stringList.add(line);
            if(verifyInstruction(line)==1){textView.setText("Invalid Instruction Format");}
            else {textView.setText("Format is correct");}


        }
        ListOfInstructions =  createInstructions(stringList);
        // Create the header row
        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(Color.parseColor("#FFC42E")); // Secondary color

        String[] columnTitles = {"Instructions", "Issue", "Execute", "Writing Back"};

        for (String title : columnTitles) {
            TextView textView = createTextView(title, true);
            headerRow.addView(textView);
        }
        tableLayout.addView(headerRow);
        // Create additional rows
        int rowCount = ListOfInstructions.size();
        for (int i = 0; i < rowCount; i++) {
            TableRow row = new TableRow(this);

            for (String title : columnTitles) {
                TextView textView = createTextView("hi", false);
                if (title == "Instructions"){textView = createTextView(ListOfInstructions.get(i).getOp(), false);}
                else if (title == "Issue"){textView = createTextView(Integer.toString(ListOfInstructions.get(i).getIssue()), false);}
                else if (title == "Execute"){textView = createTextView(Integer.toString(ListOfInstructions.get(i).getExecute()), false);}
                else if (title == "Writing Back"){textView = createTextView(Integer.toString(ListOfInstructions.get(i).getWriteBack()), false);}
                row.addView(textView);
            }
            tableLayout.addView(row);
        }
    }

    private List<Instruction> createInstructions(List<String> Instructions) {

        List<Instruction> ListOfInstructions= new ArrayList<>();
        for(int i=0;i<Instructions.size();i++) {
            String op ="";
            int index = 0;
            while (index < Instructions.get(i).length()) {
                char currentChar = Instructions.get(i).charAt(index);
                if (currentChar == ' ') {
                    break; // Found a space, exit the loop
                }
                op += currentChar; // Append the current character to the op string

                index++;
            }
            Log.e("Ahmed Amr", op);
            Instruction Inst = new Instruction(op);
            ListOfInstructions.add(Inst);

        }

        return  ListOfInstructions;
    }
    private TextView createTextView(String text, boolean isBold) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(Color.WHITE); // Text color
        textView.setPadding(16, 16, 16, 16); // Padding
        textView.setTextSize(16); // Text size

        if (isBold) {
            textView.setTypeface(null, Typeface.BOLD);
            textView.setBackgroundColor(Color.parseColor("#FFC42E")); // Secondary color
        }

        return textView;
    }
    public int verifyInstruction(String instruction) {
        String[] parts = instruction.split(" ");
        String opcode = parts[0];
        String format = parts[1];

        // Check the first part before the space
        if (!opcode.matches("LOAD|STORE|BNE|JAL|RET|ADD|ADDI|NEG|NAND|SLL")) {
            return 1;
        }

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
            case "RET":
                if (!format.equals("RET")) {
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

        // All conditions passed, return 0
        return 0;
    }
}