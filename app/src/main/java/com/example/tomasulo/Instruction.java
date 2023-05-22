package com.example.tomasulo;

public class Instruction {
    private String op;
    private int issue;
    private int execute;
    private int writeBack;
    private int startExecute;
    private int type;
    public Register rs1 = new Register();
    public Register rs2 = new Register();
    public Register rd= new Register();
    private int imm;
    private int pc;

    public Instruction(String op,int rs1,int rs2,int rd,int imm) {
        this.op = op;
        this.issue = -1;
        this.execute = -1;
        this.writeBack = -1;
        this.imm = imm;
        this.rs1.setIndex(rs1);
        this.rs2.setIndex(rs2);
        this.rd.setIndex(rd);
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

    public Register getRs1() {
        return rs1;
    }

    public void setRs1(Register rs1) {
        this.rs1 = rs1;
    }

    public Register getRs2() {
        return rs2;
    }

    public void setRs2(Register rs2) {
        this.rs2 = rs2;
    }


    public int getImm() {
        return imm;
    }

    public void setImm(int imm) {
        this.imm = imm;
    }
    public Register getRd() {
        return rd;
    }

    public void setRd(Register rd) {
        this.rd = rd;
    }
    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void reset()
    {
        issue = -1;
        execute = -1;
        writeBack = -1;
        startExecute= -1;
    }
}
