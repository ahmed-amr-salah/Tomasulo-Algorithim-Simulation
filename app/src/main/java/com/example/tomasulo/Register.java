package com.example.tomasulo;

public class Register {

    private int data;
    private int Qi;


    public Register() {
        this.data = 0;
        Qi = -1;
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
}
