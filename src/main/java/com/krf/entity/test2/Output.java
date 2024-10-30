package com.krf.entity.test2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Output implements Observer {

    private ArrayList<String> kwicList;
    private ArrayList<String> outputTxt = new ArrayList<>();

    public Output(ArrayList<String> kwicList,String filename) {

        this.kwicList = kwicList;
    }

    @Override
    public void toDo(){

        Iterator<String> it = kwicList.iterator();
        while (it.hasNext()) {
            outputTxt.add(it.next());
        }
    }

    public List<String> getOutputTxt(){
        return this.outputTxt;
    }

}
