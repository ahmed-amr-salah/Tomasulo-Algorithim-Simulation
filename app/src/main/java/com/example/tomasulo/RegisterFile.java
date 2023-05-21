package com.example.tomasulo;

import java.util.Arrays;

public class RegisterFile
{
    int [] Registers = new int[8];
    int [] FunctionalUnits = new int[8];

    RegisterFile() {
        Arrays.fill(Registers, 10);
        Arrays.fill(FunctionalUnits, 10);
        Registers[0] = 0;
    }

    void setValue(int address, int data)
    {
        if(address == 0)
            return;
        else
            Registers[address] = data;
    }
    void setFunctionalUnit(int address, int index)
    {
        if(address == 0)
            return;
        else
            FunctionalUnits[address] = index;
    }
    int getValue(int address)
    {
        return Registers[address];
    }
    int getFunctionalUnit(int address)
    {
        return FunctionalUnits[address];
    }
}
