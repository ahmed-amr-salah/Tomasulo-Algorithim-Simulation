package com.example.tomasulo;


public class ReservationStation {

    private int Qj;
    private int id;
    private int Qk;
    private int Vj;
    private int Vk;
    private int address;
    private String op;
    private boolean Ready;
    private int immediate;
    private Instruction instruction;


    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
        changeTheState(instruction);
    }


    public void setQj(int qj) {
        Qj = qj;
    }

    public void setVj(int vj) {
        Vj = vj;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public void setReady(boolean ready) {
        Ready = ready;
    }


    public void setImmediate(int immediate) {
        this.immediate = immediate;
    }

    public int getQj() {
        return Qj;
    }

    public int getVj() {
        return Vj;
    }

    public int getAddress() {
        return address;
    }

    public String getOp() {
        return op;
    }

    public boolean isReady() {
        return Ready;
    }


    public int getImmediate() {
        return immediate;
    }

    public void changeTheState(Instruction instruction)
    {
        this.op = instruction.getOp();
        this.Ready = false;
    }

}
