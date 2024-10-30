package com.krf.controller;

import com.krf.Result.Result;
import com.krf.service.KWICService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class KWICController {

    @Autowired
    private KWICService kwicService;

    /**
     * 开始KWIC算法
     * @return
     */
    @PostMapping("/start/{style}")
    public Result<List<String>> startKWIC(@PathVariable Integer style,MultipartFile inputFile) throws IOException {
        log.info("id值：{}，接收到了文件内容：{}",style,inputFile);
        List<String> outputTxt = kwicService.startKWIC(style,inputFile);
        return Result.success(outputTxt);
    }



}
