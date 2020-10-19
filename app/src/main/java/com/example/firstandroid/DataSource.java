package com.example.firstandroid;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static DataSource ourInstance = null;
    private final List<Integer> list;

    private DataSource(int startMinNumber, int startMaxNumber) {
        list = new ArrayList<>();
        for (int i = startMinNumber; i <= startMaxNumber; i++) {
            list.add(i);
        }
    }

    public static DataSource getInstance() {
        return ourInstance;
    }

    public static DataSource fullInstance(int startMinNumber, int startMaxNumber) {
        if (ourInstance == null) {
            ourInstance = new DataSource(startMinNumber, startMaxNumber);
        }
        return ourInstance;
    }

    public List<Integer> getData() {
        return ourInstance.list;
    }

    public void addNextNumber() {
        list.add(list.get(list.size() - 1) + 1);
    }

    public int getMaxNumber() {
        return ourInstance.list.get(ourInstance.list.size() - 1);
    }
}