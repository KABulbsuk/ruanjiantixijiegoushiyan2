package com.krf.service.impl;


import com.krf.constant.Style;
import com.krf.entity.test1.MainSub;
import com.krf.entity.test2.KWICSubject;
import com.krf.entity.test3.Alphabetizer;
import com.krf.entity.test3.Input;
import com.krf.entity.test3.Output;
import com.krf.entity.test3.Shift;
import com.krf.entity.test4.Pipe;
import com.krf.service.KWICService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class KWICServiceImpl implements KWICService {
    @Override
    public List<String> startKWIC(Integer style,MultipartFile file) throws IOException {
        // 将文件存储到本地
        file.transferTo(new File("C:\\Users\\86158\\Pictures\\Desktop\\test2\\input.txt"));

        // 输出结果
        List<String> outputTxt = null;

        // style为1时使用主程序-子程序模式
        if(style.equals(Style.MAIN_SUB)){
            log.info("使用主程序-子程序模式...");
            outputTxt = startByMianSub();
        } else if (style.equals(Style.OOP)){
            log.info("使用面向对象模式...");
            outputTxt = startByOPP();
        } else if (style.equals(Style.OBSERVER)){
            log.info("使用观察者模式...");
            outputTxt = startByObserver();
        } else if (style.equals(Style.PIPE_FILTER)){
            log.info("使用管道过滤模式...");
            outputTxt = startByPipeFilter();
        } else {
            throw new RuntimeException("没有这个设计风格的算法");
        }

        return outputTxt;
    }


    /**
     * 主程序-子程序模式
     * @return
     */
    private List<String> startByMianSub(){

        MainSub kwic = new MainSub();
        // 输入文件
        kwic.input("C:\\Users\\86158\\Pictures\\Desktop\\test2\\input.txt");
        // 循环位移
        kwic.shift();
        // 排序
        kwic.alphabetizer();
        // 返回输出结果
        return kwic.output();
    }

    /**
     * 面向对象模式
     * @return
     */
    private List<String> startByOPP(){
        // 创建输入类
        Input input = new Input();
        // 读取文件
        input.input("C:\\Users\\86158\\Pictures\\Desktop\\test2\\input.txt");
        // 循环位移
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        // 排序
        Alphabetizer alphabetizer = new Alphabetizer (shift.getKwicList());
        alphabetizer.sort();
        // 输出结果
        Output output = new Output (alphabetizer.getKwicList());
        return output.output();
    }

    /**
     * 观察者模式
     * @return
     */
    private List<String> startByObserver(){
        // 创建主题
        KWICSubject kwicSubject = new KWICSubject();

        // 创建观察者
        com.krf.entity.test2.Input input = new com.krf.entity.test2.Input("C:\\Users\\86158\\Pictures\\Desktop\\test2\\input.txt");
        com.krf.entity.test2.Shift shift = new com.krf.entity.test2.Shift(input.getLineTxt());
        com.krf.entity.test2.Alphabetizer alphabetizer = new com.krf.entity.test2.Alphabetizer(shift.getKwicList());
        com.krf.entity.test2.Output output = new com.krf.entity.test2.Output(alphabetizer.getKwicList(), "D:\\test\\output.txt");

        // 将观察者加入主题
        kwicSubject.addObserver(input);
        kwicSubject.addObserver(shift);
        kwicSubject.addObserver(alphabetizer);
        kwicSubject.addObserver(output);
        // 逐步调用各个观察者
        kwicSubject.startKWIC();

        return output.getOutputTxt();
    }

    /**
     * 管道过滤模式
     * @return
     */
    private List<String> startByPipeFilter() throws IOException {
        // 读取文件
        File inFile = new File("C:\\Users\\86158\\Pictures\\Desktop\\test2\\input.txt");
        // 创建管道
        Pipe pipe1 = new Pipe ();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        // 创建过滤器
        com.krf.entity.test4.Input input = new com.krf.entity.test4.Input(inFile, pipe1);
        com.krf.entity.test4.Shift shift = new com.krf.entity.test4.Shift(pipe1, pipe2);
        com.krf.entity.test4.Alphabetizer alphabetizer  = new com.krf.entity.test4.Alphabetizer(pipe2, pipe3);
        com.krf.entity.test4.Output output = new com.krf.entity.test4.Output(null,pipe3);
        // 开始过滤
        input.transform();
        shift.transform();
        alphabetizer.transform();
        output.transform();

        return output.getOutputTxt();
    }
}
