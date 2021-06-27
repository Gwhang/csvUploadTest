package com.example.csvuploadtest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /** 学号 */
    private String studentId;
    /** 姓名 */
    private String name;
    /** 总分 */
    private Integer totalScore;


}
