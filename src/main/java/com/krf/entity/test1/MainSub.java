package com.krf.entity.test1;

import java.io.*;
import java.util.*;

public class MainSub {

    // 用于存储循环移位后的文本行
    private ArrayList<String> kwicList = new ArrayList<String>();
    // 用于存储文本行
    private ArrayList<String> lineTxt = new ArrayList<String>();
    private BufferedReader inputFile;
    private ArrayList<String> outputTxt = new ArrayList<>();


    /**
     * 读取文件
     * @param fileName
     */
    public void input(String fileName) {

        try {
            inputFile = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = inputFile.readLine()) != null) {
                lineTxt.add(line);
            }

            // 释放资源
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出文件
     * @param
     */
    public List<String> output(){
        Iterator<String> it = kwicList.iterator();
        // 循环遍历迭代器，将循环移位后的文本行添加到outputTxt中
        while (it.hasNext()) {
            outputTxt.add(it.next());
        }
       return outputTxt;
    }

    /**
     *循环移位
     */
    public void shift() {
        //获取每个单词，存入tokens
        Iterator<String> it = lineTxt.iterator();
        while (it.hasNext()) {
            // 用来对字符串进行分词，即按照一定的分隔符将字符串分割成一系列的标记（token） 默认分隔符为空格
            StringTokenizer token = new StringTokenizer(it.next());
            ArrayList<String> tokens = new ArrayList<String>();
            int i = 0;
            //循环添加单词
            int count = token.countTokens();
            while (i < count) {
                tokens.add(token.nextToken());
                i++;
            }

            //display(tokens);
            //切割各个单词，不断改变起始值和利用loop实现位移。
            for (i = 0; i < count; i++) { //用于实现获取每一次循环后的行的首个单词
                //StringBuffer是一个可变的字符序列，它是线程安全的
                StringBuffer lineBuffer = new StringBuffer();
                int index = i;
                for (int f = 0; f < count; f++) {

                    //从头继续位移
                    if (index >= count)
                        index = 0;
                    //存入StringBuffer
                    lineBuffer.append(tokens.get(index));
                    lineBuffer.append(" ");
                    index++;
                }
                String tmp = lineBuffer.toString();
                kwicList.add(tmp);
            }
        }

    }


    /**
     * 依字母顺序排序
     */
    public void alphabetizer() {
        Collections.sort(this.kwicList, new AlphabetizerComparator());
    }

    // 定制排序方式
    private class AlphabetizerComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {

            if (o1 == null && o2 == null) {
                throw new NullPointerException();
            }
            int compareValue = 0;
            char o1c = o1.toLowerCase().charAt(0); //忽略大小写
            char o2c = o2.toLowerCase().charAt(0); //忽略大小写
            compareValue = o1c - o2c;
            return compareValue;

        }

    }
}