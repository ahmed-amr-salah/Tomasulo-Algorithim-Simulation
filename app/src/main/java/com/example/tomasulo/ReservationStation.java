package com.example.tomasulo;


public class LoadReservationStation {

    private int Qj;
    private int Vj;
    private int address;
    private String op;
    private boolean Ready;
    private int numberOfCycles;
    private int immediate;
    private Instruction instruction;


    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public LoadReservationStation() {
        Qj = -1;
        Vj = -1;
        this.address = -1;
        this.op = "Load";
        Ready = true;
        this.numberOfCycles = 2;
        this.immediate = immediate;
        this.instruction = null;
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

    public void setNumberOfCycles(int numberOfCycles) {
        this.numberOfCycles = numberOfCycles;
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

    public int getNumberOfCycles() {
        return numberOfCycles;
    }

    public int getImmediate() {
        return immediate;
    }
}
