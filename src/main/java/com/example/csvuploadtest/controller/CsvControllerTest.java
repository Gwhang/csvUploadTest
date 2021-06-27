package com.example.csvuploadtest.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.csvuploadtest.pojo.Student;
import com.example.csvuploadtest.util.CsvUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传验证
 */
@RestController
@RequestMapping("/csv")
public class CsvControllerTest {


    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        // 调用文件上传解析工具类
        List<Student> students = CsvUtils.achievementRead(file);
        return JSONObject.toJSONString(students);

    }

}
