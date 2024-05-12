package com.example.menetrendek;

import android.util.Pair;
import java.util.LinkedList;
import java.util.List;

public class Transportation {
    private final int ID;
    public List<Pair<String, Integer>> timeTable = new LinkedList<>();
    private String destination;

    public Transportation(int ID, LinkedList<Pair<String, Integer>> timeTable, String destination) {
        this.ID = ID;
        this.destination = destination;
        this.timeTable.addAll(timeTable);
    }

    public int getID() {
        return ID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
