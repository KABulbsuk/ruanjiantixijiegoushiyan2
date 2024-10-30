package com.krf.entity.test3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Output {

    private ArrayList<String> kwicList;
    private ArrayList<String> outputTxt = new ArrayList<>();

    public Output(ArrayList<String> kwicList) {

        this.kwicList = kwicList;
    }

    /**
     * 获取输出结果
     */
    public List<String> output(){

        // 循环遍历迭代器
        Iterator<String> it = kwicList.iterator();
        while (it.hasNext()) {
            outputTxt.add(it.next());
        }

        return outputTxt;
    }
}