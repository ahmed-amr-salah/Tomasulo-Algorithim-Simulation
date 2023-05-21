package com.example.tomasulo;

public class Instruction {
    private String op;
    private int issue;
    private int execute;
    private int writeBack;
    private int startExecute;
    private int type;
    private int numberOfCycles;
    // Type--> "Load =1, Store=2,Archthimatic =3,Return =4"
    public Instruction(String op) {
        this.op = op;
        this.issue = -1;
        this.execute = -1;
        this.writeBack = -1;
        this.startExecute = -1;
        if(op=="LOAD"){this.type = 1;}
        else if(op=="STORE"){this.type = 2;}
        else if(op=="ADD" ||op=="ADDI"||op=="NEG"||op=="SLL"||op=="NAND"  ){this.type = 3;}
        else if(op=="JAL" ||op=="RET"){this.type = 5;}
    }

    // Getters and setters for the properties
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public int getExecute() {
        return execute;
    }

    public void setExecute(int execute) {
        this.execute = execute;
    }

    public int getWriteBack() {
        return writeBack;
    }

    public void setWriteBack(int writeBack) {
        this.writeBack = writeBack;
    }

    public int getStartExecute() {
        return startExecute;
    }

    public void setStartExecute(int startExecute) {
        this.startExecute = startExecute;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
