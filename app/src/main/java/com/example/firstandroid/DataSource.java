package com.example.firstandroid;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static int mStartMinNumber;
    private static int mStartMaxNumber;
    private static boolean isStartFulled = false;


    private static final DataSource ourInstance = new DataSource();
    private final List<Integer> list;

    private DataSource() {
        list = new ArrayList<>();
    }

    public static DataSource getInstance() {
        if (!isStartFulled) {
            for (int i = mStartMinNumber; i <= mStartMaxNumber; i++) {
                ourInstance.list.add(i);
            }
            isStartFulled = true;
        }
        return ourInstance;
    }

    public List<Integer> getData() {
        return list;
    }

    public int addNextNumber() {
        int newMax = list.get(list.size() - 1)  + 1;
        list.add(newMax);
        return newMax;
    }

    public static void SetStartMinNumberAndStartMaxNumber(int startMinNumber, int startMaxNumber){
        mStartMinNumber = startMinNumber;
        mStartMaxNumber = startMaxNumber;
    }

}