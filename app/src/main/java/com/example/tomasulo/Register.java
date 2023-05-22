package com.example.tomasulo;

public class Register {
    private int data;
    private int Qi;
    private int index;

    public Register() {
        this.data = 0;
        Qi = -1;
        index =-1;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setQi(int qi) {
        Qi = qi;
    }

    public int getData() {
        return data;
    }

    public int getQi() {
        return Qi;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}