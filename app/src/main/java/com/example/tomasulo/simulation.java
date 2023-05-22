package com.example.tomasulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    Button NextCycle;

    List<Register> ListOfRegisters;
    List<ReservationStation> ListOfRS;
    int [] memory = new int[128];
    int cycle = 0;
    int counter = 0;
    int numberOfBranches = 0;
    int numberOfBranchesTaken = 0;
    boolean needJump = false;
    boolean foundBranch = false;
    int CDBSource = -1;
    int CDBValue = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);
        getSupportActionBar().hide();
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        ListOfRegisters = initializeRegisters(8);
        ListOfRS = initializeRS(12);
         // Default row count (can be customized)
        List<String> stringList = new ArrayList<>();
        Intent intent = getIntent();
        textView = findViewById(R.id.textView2);
        NextCycle = findViewById(R.id.button2);
        String [] myList = intent.getStringArrayExtra("objectList");
        for (String line : myList) {
            stringList.add(line);

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
                //TextView textView = createTextView("hi", false);
                if (title.equals("Instructions")){textView = createTextView(ListOfInstructions.get(i).getOp(), false);}
                else if (title.equals("Issue")){textView = createTextView(Integer.toString(ListOfInstructions.get(i).getIssue()), false);}
                else if (title.equals("Execute")){textView = createTextView(Integer.toString(ListOfInstructions.get(i).getExecute()), false);}
                else if (title.equals("Writing Back")){textView = createTextView(Integer.toString(ListOfInstructions.get(i).getWriteBack()), false);}
                row.addView(textView);
            }
            tableLayout.addView(row);
        }

       // aktby el hay7sl lma ados 3la el button gwa onClick
        NextCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issue();
                Log.e("Ahmed ","Ins 1 issue :" + Integer.toString(ListOfInstructions.get(0).getIssue()));
                Log.e("Ahmed ","Ins 2 issue :" + Integer.toString(ListOfInstructions.get(1).getIssue()));
                Log.e("Ahmed ","Ins 3 issue :" + Integer.toString(ListOfInstructions.get(2).getIssue()));
                Log.e("Ahmed ","Ins 4 issue :" + Integer.toString(ListOfInstructions.get(3).getIssue()));
                Log.e("Ahmed ","Ins 5 issue :" + Integer.toString(ListOfInstructions.get(4).getIssue()));

                //Log.e("Ahmed ",Integer.toString(ListOfInstructions.get(2).getIssue()));
                execute();
                Log.e("Ahmed ","Ins 1 Execute :" + Integer.toString(ListOfInstructions.get(0).getExecute()));
                Log.e("Ahmed ","Ins 2 Execute :" +Integer.toString(ListOfInstructions.get(1).getExecute()));
                Log.e("Ahmed ","Ins 3 Execute :" +Integer.toString(ListOfInstructions.get(2).getExecute()));
                Log.e("Ahmed ","Ins 4 Execute :" +Integer.toString(ListOfInstructions.get(3).getExecute()));
                Log.e("Ahmed ","Ins 5 Execute :" +Integer.toString(ListOfInstructions.get(4).getExecute()));


                // Log.e("Ahmed ",Integer.toString(ListOfInstructions.get(2).getExecute()));
                WB();
                Log.e("Ahmed ","Ins 1 WB :" +Integer.toString(ListOfInstructions.get(0).getWriteBack()));
                Log.e("Ahmed ","Ins 2 WB :" +Integer.toString(ListOfInstructions.get(1).getWriteBack()));
                Log.e("Ahmed ","Ins 3 WB :" +Integer.toString(ListOfInstructions.get(2).getWriteBack()));
                Log.e("Ahmed ","Ins 4 WB :" +Integer.toString(ListOfInstructions.get(3).getWriteBack()));
                Log.e("Ahmed ","Ins 5 WB :" +Integer.toString(ListOfInstructions.get(4).getWriteBack()));

                // Log.e("Ahmed ",Integer.toString(ListOfInstructions.get(2).getWriteBack()));
                cycle = cycle + 1;
            }
        });
    }
    public static int extractRegisterNumber(String register) {

        return Integer.parseInt(register.substring(1));
    }
    private List<Instruction> createInstructions(List<String> Instructions) {

        List<Instruction> ListOfInstructions= new ArrayList<>();
        for(int i=0;i<Instructions.size();i++) {

            //Log.e("op",Instructions.get(i));

            String[] tokens = Instructions.get(i).split(" ");
            //
            String op = tokens[0];
            int rd = 0, rs1 = 0, rs2 = 0,imm =0;

            if (op.equals("STORE") || op.equals("LOAD"))
            {
                String[] registers = tokens[1].split(",");
                String[] registers2 = registers[1].split("\\(");
                rd = extractRegisterNumber(registers[0]);
                imm = Integer.parseInt(registers2[0]);
                registers2[1] = registers2[1].substring(0, registers2[1].length() - 1);
                rs1 = extractRegisterNumber(registers2[1]);

            } else if (op.equals("ADDI") || op.equals("ADD") || op.equals("NAND") || op.equals("SLL") || op.equals("BNE")) {
                String[] registers = tokens[1].split(",");
                rd = extractRegisterNumber(registers[0]);
                rs1 = extractRegisterNumber(registers[1]);
                rs2 = 0;
                if (op.equals("ADDI"))
                    imm = Integer.parseInt(registers[2]);
                else if (op.equals("BNE"))
                    rs2 =Integer.parseInt(registers[2]);
                else
                    rs2 = extractRegisterNumber(registers[2]);
            } else if (op.equals("NEG")) {
                rd = extractRegisterNumber(tokens[1]);
                rs1 = -1;
                rs2 = -1;
            }else
            {
                op = "RET";
                rs1 = -1;
                rs2 =-1;
                rd =-1;
            }

            Instruction Inst = new Instruction(op, rs1, rs2, rd,imm);
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

    private List<Register> initializeRegisters(int RegistersSize)
    {
        List<Register> Registers = new ArrayList<>();
        Log.e("AHMED", "I AM HERE 1 ");
     for(int i = 0; i < RegistersSize; i++) {
//         boolean added;
         Register R = new Register();
         R.setIndex(i);
         R.setData(0);
         R.setQi(-1);
         Log.e("AHMED", "I AM HERE in the for loop  ");
         Registers.add(R);
//         System.out.print(added);
         Log.e("AHMED", "I AM HERE in the after add ");
     }
     return Registers;
    }

    private List<ReservationStation> initializeRS(int RSSize)
    {
        List<ReservationStation> ReservationStations = new ArrayList<>();
        for(int i = 0; i < RSSize; i++)
        {

            ReservationStation rs = new ReservationStation();
            if(i < 2) //Reservation station for LOAD at 0->1
            {
                rs.setNeededCycles(2);
                rs.setOperation("LOAD");
            }
            else if(i >= 2 && i < 4){ //Reservation station for STORE at 2->3
                rs.setNeededCycles(2);
                rs.setOperation("STORE");
            }
            else if(i >=4 && i < 7) { //Reservation station for ADD/ADDI at 4->6
                rs.setNeededCycles(2);
                rs.setOperation("ADD");
            }
            else if(i == 7){ //Reservation station for NEG at 7
                rs.setNeededCycles(2);
                rs.setOperation("NEG");
            }
            else if(i == 8){ //Reservation station for NAND at 8
                rs.setNeededCycles(1);
                rs.setOperation("NAND");
            }
            else if(i == 9){ //Reservation station for SLL at 9
                rs.setNeededCycles(8);
                rs.setOperation("SLL");
            }
            else if(i == 10){ //Reservation station BNE at 10
                rs.setNeededCycles(1);
                rs.setOperation("BNE");
            }
            else if(i == 11){ //Reservation station for JAL/RET at 11
                rs.setNeededCycles(1);
                rs.setOperation("JAL");
            }


            rs.setAddress(-1);
            rs.setBusy(false);
            rs.setExecuting(false);
            rs.setFinished(false);
            rs.setId(i);
            rs.setQj(-1);
            rs.setQk(-1);
            rs.setVj(-1);
            rs.setVk(-1);
            rs.setResult(-1);
            rs.setInstructionIndex(-1);

            ReservationStations.add(rs);
        }
        return ReservationStations;
    }

    private int getAvailableRS(String op)
    {
        String operation = op;
        if(op.equals("ADDI")){
            operation = "ADD";
        }
        else if(op.equals("RET")){
            operation = "JAL";
        }
        for(int i = 0; i < ListOfRS.size(); i++)
        {
            if(ListOfRS.get(i).getOperation().equals(operation) && ListOfRS.get(i).isBusy() == false){
                return ListOfRS.get(i).getId();
            }
        }
        return -2;
    }
    private void issue() {

        if(counter >=  ListOfInstructions.size())
            return;
        if(needJump == false) {
            int AvailableRS = getAvailableRS(ListOfInstructions.get(counter).getOp());
            if (AvailableRS == -1)
                return;
            if (ListOfInstructions.get(counter).getOp().equals("ADD") || ListOfInstructions.get(counter).getOp().equals("NAND") || ListOfInstructions.get(counter).getOp().equals("SLL")) {
                ListOfRS.get(AvailableRS).setQj(ListOfInstructions.get(counter).rs1.getQi());
                if (ListOfRS.get(AvailableRS).getQj() == -1) {
                    ListOfRS.get(AvailableRS).setVj(ListOfInstructions.get(counter).getRs1().getData());
                }
                ListOfRS.get(AvailableRS).setQk(ListOfInstructions.get(counter).getRs2().getQi());
                if (ListOfRS.get(AvailableRS).getQk() == -1) {
                    ListOfRS.get(AvailableRS).setVk(ListOfInstructions.get(counter).getRs2().getData());
                }
                ListOfRegisters.get(ListOfInstructions.get(counter).getRd().getIndex()).setQi(ListOfRS.get(AvailableRS).getId());
            }
            else if (ListOfInstructions.get(counter).getOp().equals("ADDI") ) {
                ListOfRS.get(AvailableRS).setVk(ListOfInstructions.get(counter).getImm());
                ListOfRS.get(AvailableRS).setQk(-1);
                ListOfRS.get(AvailableRS).setQj(ListOfInstructions.get(counter).getRs1().getQi());
                if (ListOfRS.get(AvailableRS).getQj() == -1) {
                    ListOfRS.get(AvailableRS).setVj(ListOfInstructions.get(counter).getRs1().getData());
                }
                ListOfRegisters.get(ListOfInstructions.get(counter).getRd().getIndex()).setQi(ListOfRS.get(AvailableRS).getId());
            }
            else if (ListOfInstructions.get(counter).getOp().equals("LOAD")) {
                ListOfRS.get(AvailableRS).setAddress(ListOfInstructions.get(counter).getImm());
                ListOfRS.get(AvailableRS).setQj(ListOfInstructions.get(counter).getRs1().getQi());
                if (ListOfRS.get(AvailableRS).getQj() == -1) {
                    ListOfRS.get(AvailableRS).setVj(ListOfInstructions.get(counter).getRs1().getData());
                }
                ListOfRegisters.get(ListOfInstructions.get(counter).getRd().getIndex()).setQi(ListOfRS.get(AvailableRS).getId());
            }
            else if (ListOfInstructions.get(counter).getOp().equals("STORE")) {
                ListOfRS.get(AvailableRS).setAddress(ListOfInstructions.get(counter).getImm());
                ListOfRS.get(AvailableRS).setQj(ListOfInstructions.get(counter).getRs1().getQi());
                if (ListOfRS.get(AvailableRS).getQj() == -1) {
                    ListOfRS.get(AvailableRS).setVj(ListOfInstructions.get(counter).getRs1().getData());
                }
                ListOfRS.get(AvailableRS).setQk(ListOfInstructions.get(counter).getRs2().getQi());
                if (ListOfRS.get(AvailableRS).getQk() == -1) {
                    ListOfRS.get(AvailableRS).setVk(ListOfInstructions.get(counter).getRs2().getData());
                }
            }
            else if (ListOfInstructions.get(counter).getOp().equals("JAL")) {
                ListOfInstructions.get(counter).setRd(ListOfRegisters.get(1));
                needJump = true;
                ListOfRS.get(AvailableRS).setVk(ListOfInstructions.get(counter).getImm());
                ListOfRS.get(AvailableRS).setQk(-1);
                ListOfRegisters.get(ListOfInstructions.get(counter).getRd().getIndex()).setQi(ListOfRS.get(AvailableRS).getId());
                foundBranch = true;
            }
            else if (ListOfInstructions.get(counter).getOp().equals("BNE")) {
                foundBranch = true;
                ListOfRS.get(AvailableRS).setAddress(ListOfInstructions.get(counter).getImm());
                ListOfRS.get(AvailableRS).setQj(ListOfInstructions.get(counter).getRs1().getQi());
                if (ListOfRS.get(AvailableRS).getQj() == -1) {
                    ListOfRS.get(AvailableRS).setVj(ListOfInstructions.get(counter).getRs1().getData());
                }
                ListOfRS.get(AvailableRS).setQk(ListOfInstructions.get(counter).getRs2().getQi());
                if (ListOfRS.get(AvailableRS).getQk() == -1) {
                    ListOfRS.get(AvailableRS).setVk(ListOfInstructions.get(counter).getRs2().getData());
                }
            }
            else if (ListOfInstructions.get(counter).getOp().equals("NEG")) {
                ListOfRS.get(AvailableRS).setQj(ListOfInstructions.get(counter).getRs1().getQi());
                if (ListOfRS.get(AvailableRS).getQj() == -1) {
                    ListOfRS.get(AvailableRS).setVj(ListOfInstructions.get(counter).getRs1().getData());
                }
                ListOfRS.get(AvailableRS).setQk(ListOfInstructions.get(counter).getRs2().getQi());
                if (ListOfRS.get(AvailableRS).getQk() == -1) {
                    ListOfRS.get(AvailableRS).setVk(ListOfInstructions.get(counter).getRs2().getData());
                }
                ListOfRegisters.get(ListOfInstructions.get(counter).getRs1().getIndex()).setQi(ListOfRS.get(AvailableRS).getId());
            }
            else if (ListOfInstructions.get(counter).getOp().equals("RET")) {
                ListOfRS.get(AvailableRS).setQj(ListOfRegisters.get(1).getQi());
                if (ListOfRS.get(AvailableRS).getQj() == -1) {
                    ListOfRS.get(AvailableRS).setVj(ListOfRegisters.get(1).getData());
                }
                needJump = true;
            }
            if(foundBranch){
                ListOfRS.get(AvailableRS).setAfterBranching(true);
            }
            ListOfRS.get(AvailableRS).setBusy(true);
            ListOfInstructions.get(counter).setPc(counter);
            ListOfInstructions.get(counter).setIssue(cycle);
            ListOfRS.get(AvailableRS).setInstructionIndex(counter);
            counter++;
        }
    }

    private int finishedExecution(ReservationStation S)
    {
        Instruction inst = ListOfInstructions.get(S.getInstructionIndex());
        int result = 0;
        if(inst.getOp().equals("ADD") || inst.getOp().equals("ADDI") )
        {
            result = S.getVj() + S.getVk();
        }
        else if(inst.getOp().equals("SLL")){
            result = S.getVj() << S.getVk();
        }
        else if(inst.getOp().equals("NEG"))
        {
            result = S.getVj() * -1;
        }
        else if(inst.getOp().equals("NOR")){
            result = ~(S.getVj() | S.getVk());
        }
        else if(inst.getOp().equals("LOAD")){
            result = memory[S.getAddress() + S.getVj()];
        }
        else if(inst.getOp().equals("STORE")){
            result = S.getVk();
        }
        else if(inst.getOp().equals("RET")){
            result = 1;
        }
        else if(inst.getOp().equals("JAL")){
            result = inst.getPc() + 1;
        }
        else if(inst.getOp().equals("BNE")){
            result = (S.getVj() != S.getVk()) ? 1 : 0;
            if(result == 1)
            {
                numberOfBranchesTaken = numberOfBranchesTaken + 1;
            }
        }
        return result;
    }
    private int finishedExecutionExtra(ReservationStation S)
    {
        Instruction inst = ListOfInstructions.get(S.getInstructionIndex());
        int address = 0;
        if(inst.getOp().equals("BNE")){
            address = inst.getPc() + S.getAddress() + 1;
        }
        else if(inst.getOp().equals("RET")){
            address = S.getVj();
        }
        else if(inst.getOp().equals("JAL")){
            address = S.getVk() + inst.getPc();
        }
        else
            address = -1;
        return address;
    }
    private void execute()
    {
        for(int i = 0; i < ListOfRS.size(); i++)
        {

            int neededCycles = ListOfRS.get(i).getNeededCycles();

            if(ListOfRS.get(i).isBusy() == true)
            {

                if(ListOfRS.get(i).isAfterBranching() == true)
                {
                    continue;
                }
                if(ListOfInstructions.get(ListOfRS.get(i).getInstructionIndex()).getIssue() == cycle)
                {

                    continue;
                }
                if(ListOfRS.get(i).isExecuting() == true && ListOfRS.get(i).isFinished() == false)
                {

                    ReservationStation Station = ListOfRS.get(i);
                    ListOfRS.get(i).setNeededCycles((neededCycles-1));
                    if(ListOfRS.get(i).getNeededCycles() == 0)
                    {
                        ListOfRS.get(i).setFinished(true);
                        ListOfRS.get(i).setExecuting(false);
                        int result = finishedExecution(Station);
                        int address = finishedExecutionExtra(Station);
                        ListOfRS.get(i).setResult(result);
                        if(address != -1)
                        {
                            ListOfRS.get(i).setAddress(address);
                        }
                        ListOfInstructions.get(ListOfRS.get(i).getInstructionIndex()).setExecute(cycle);
                    }

                }
                else if (ListOfRS.get(i).isExecuting() == false && ListOfRS.get(i).isFinished() == false)
                {

                    ReservationStation Station = ListOfRS.get(i);
                    if(ListOfRS.get(i).getQj() == -1 && ListOfRS.get(i).getQk() == -1)
                    {
                        ListOfRS.get(i).setExecuting(true);
                        ListOfRS.get(i).setNeededCycles((neededCycles-1));
                        ListOfInstructions.get(ListOfRS.get(i).getInstructionIndex()).setStartExecute(cycle);
                        if (ListOfRS.get(i).getNeededCycles() == 0)
                        {
                            ListOfRS.get(i).setFinished(true);
                            ListOfRS.get(i).setExecuting(false);
                            int result = finishedExecution(Station);
                            int address = finishedExecutionExtra(Station);
                            ListOfRS.get(i).setResult(result);
                            if(address != -1)
                            {
                                ListOfRS.get(i).setAddress(address);
                            }
                            ListOfInstructions.get(ListOfRS.get(i).getInstructionIndex()).setExecute(cycle);
                        }
                    }

                }
            }
        }
        Log.e("Ahmed ","execute end");
    }

    private boolean checkForWAW(ReservationStation S)
    {
        Instruction inst1 = ListOfInstructions.get(S.getInstructionIndex());

        for(int i = 0; i < ListOfRS.size(); i++) {
            if (S.getId() == ListOfRS.get(i).getId()) {
                continue;
            } else {
                if (ListOfRS.get(i).isFinished() == true) {
                    Instruction inst2 = ListOfInstructions.get(ListOfRS.get(i).getInstructionIndex());

                    if (inst1.getRd() == inst2.getRd()) {

                        if (inst2.getIssue() < inst1.getIssue()) {

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    private void flush()
    {
        for(int i = 0; i < ListOfRS.size(); i++)
        {
            if(ListOfRS.get(i).isAfterBranching() == true)
            {
                ListOfRS.get(i).reset();
            }
        }
    }
    private boolean readyToWrite(ReservationStation S)
    {
        Instruction inst = ListOfInstructions.get(S.getInstructionIndex());
        if(inst.getExecute()+1 != cycle)
        {
            return false;
        }
        else {
            if (inst.getOp().equals("BNE")) {
                foundBranch = false;
                if (S.getResult() == 1) {
                    flush();
                    counter = S.getAddress();
                }
            } else if (inst.getOp().equals("JAL") || inst.getOp().equals("RET")) {
                needJump = false;
                counter = S.getAddress();
            } else if (inst.getOp().equals("STORE")) {
                memory[S.getAddress() + S.getVj()] = S.getResult();
            } else if (inst.getOp().equals("ADD") || inst.getOp().equals("ADDI") || inst.getOp().equals("NOR") || inst.getOp().equals("LOAD") || inst.getOp().equals("JAL") || inst.getOp().equals("SLL")) {
                ListOfRegisters.get(inst.getRd().getIndex()).setData(S.getResult());
                if (ListOfRegisters.get(inst.getRd().getIndex()).getQi() == S.getId())
                    ListOfRegisters.get(inst.getRd().getIndex()).setQi(-1);
            } else if (inst.getOp().equals("NEG")) {
                ListOfRegisters.get(inst.getRs1().getIndex()).setData(S.getResult());
                if (ListOfRegisters.get(inst.getRs1().getIndex()).getQi() == S.getId())
                    ListOfRegisters.get(inst.getRs1().getIndex()).setQi(-1);
            }
            ListOfInstructions.get(S.getInstructionIndex()).setWriteBack(cycle);
//            ListOfInstructions.get(S.getInstructionIndex()).reset();
            CDBSource = ListOfRS.get(S.getId()).getId();
            CDBValue = ListOfRS.get(S.getId()).getResult();
            ListOfRS.get(S.getId()).reset();
            return true;
        }
    }
    private void WB()
    {
        for(int i = 0; i < ListOfRS.size(); i++)
        {
            if(ListOfRS.get(i).isFinished() == true && ListOfRS.get(i).isExecuting == false && ListOfRS.get(i).isBusy() == true)
            {
                for(int j = 0; j < ListOfRegisters.size(); j++)
                {
                    if(ListOfRS.get(i).getId() == ListOfRegisters.get(j).getQi())
                    {
                        if(checkForWAW(ListOfRS.get(i)) == false)
                        {
                            if(readyToWrite(ListOfRS.get(i)) == true)
                            {
                                break;
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < ListOfRS.size(); i++)
        {
            if(ListOfRS.get(i).getQj() == CDBSource)
            {
                ListOfRS.get(i).setQj(-1);
                ListOfRS.get(i).setVj(CDBValue);
            }
            else if(ListOfRS.get(i).getQk() == CDBSource)
            {
                ListOfRS.get(i).setQk(-1);
                ListOfRS.get(i).setVk(CDBValue);
            }
        }
    }

}

