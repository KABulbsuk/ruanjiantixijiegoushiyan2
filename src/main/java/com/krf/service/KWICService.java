package com.krf.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface KWICService {
    /**
     * 使用主程序-子程序
     * @param file
     * @return
     * @throws IOException
     */
    List<String> startKWIC(Integer style, MultipartFile file) throws IOException;
}
