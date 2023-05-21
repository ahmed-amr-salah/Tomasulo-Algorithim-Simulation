package com.example.tomasulo;


public class ReservationStation
{
    int neededCycles;
    String operation;
    int Qj, Qk;
    int Vj, Vk;
    int address;
    boolean busy;
    boolean isExecuting;
    int result;
    int id;

    public int getInstructionIndex() {
        return instructionIndex;
    }

    public void setInstructionIndex(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    int instructionIndex;

    public int getNeededCycles() {
        return neededCycles;
    }

    public void setNeededCycles(int neededCycles) {
        this.neededCycles = neededCycles;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getQj() {
        return Qj;
    }

    public void setQj(int qj) {
        Qj = qj;
    }

    public int getQk() {
        return Qk;
    }

    public void setQk(int qk) {
        Qk = qk;
    }

    public int getVj() {
        return Vj;
    }

    public void setVj(int vj) {
        Vj = vj;
    }

    public int getVk() {
        return Vk;
    }

    public void setVk(int vk) {
        Vk = vk;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean isExecuting() {
        return isExecuting;
    }

    public void setExecuting(boolean executing) {
        isExecuting = executing;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    void reset()
    {
        Qj = 0;
        Qk = 0;
        Vj = 0;
        Vk = 0;
        address = 0;
        boolean busy = false;
        boolean isExecuting = false;
        result = -1;
    }

}