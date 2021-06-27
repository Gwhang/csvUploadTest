package com.example.csvuploadtest.util;
import com.csvreader.CsvReader;
import com.example.csvuploadtest.pojo.Student;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * csv 文件读取工具类
 */
public class CsvUtils {


    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(CsvUtils.class);

    /**
     * 文件上传
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static List<Student> achievementRead(MultipartFile file){

        List<Student> result = new ArrayList<Student>();

        try {
            byte [] byteArr=file.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            /** 创建CSV读对象 */
            CsvReader csvReader = new CsvReader(inputStream, Charset.forName("utf-8"));
            /** 读表头 */
            csvReader.readHeaders();

            while (csvReader.readRecord()){
                Student student = new Student();
                /** 读一整行 */
                String rawRecord = csvReader.getRawRecord();
                /** 分割 */
                String[] split = rawRecord.split(",");
                /** 学号 */
                student.setStudentId(split[0]);
                /** 姓名 */
                student.setName(split[1]);
                /** 各科成绩 */
                int chinese = Integer.parseInt(split[2]);
                int math = Integer.parseInt(split[3]);
                int english = Integer.parseInt(split[4]);
                int physics = Integer.parseInt(split[5]);
                int chemistry = Integer.parseInt(split[6]);
                int biology = Integer.parseInt(split[7]);
                student.setTotalScore(biology + chemistry + chinese + english + math + physics);
                /** 放入list */
                result.add(student);
            }

        }catch (Exception e){
            log.error("读取CSV文件异常 异常信息为："+e);
            throw new RuntimeException("读取CSV文件异常 异常信息为："+e);
        }
        return result;
    }


    /**
     * 读csv文件
     * @param filePath
     */
    public static List<Student> achievementRead(String filePath){

        List<Student> result = new ArrayList<Student>();

        if (StringUtils.isNotBlank(filePath)) {
            try {
                /** 创建CSV读对象 */
                CsvReader csvReader = new CsvReader(filePath,',', Charset.forName("utf-8"));

                /** 读表头 */
                csvReader.readHeaders();

                while (csvReader.readRecord()){
                    Student student = new Student();
                    /** 读一整行 */
                    String rawRecord = csvReader.getRawRecord();
                    /** 分割 */
                    String[] split = rawRecord.split(",");
                    /** 学号 */
                    student.setStudentId(split[0]);
                    /** 姓名 */
                    student.setName(split[1]);
                    /** 各科成绩 */
                    int chinese = Integer.parseInt(split[2]);
                    int math = Integer.parseInt(split[3]);
                    int english = Integer.parseInt(split[4]);
                    int physics = Integer.parseInt(split[5]);
                    int chemistry = Integer.parseInt(split[6]);
                    int biology = Integer.parseInt(split[7]);
                    student.setTotalScore(biology + chemistry + chinese + english + math + physics);
                    /** 放入list */
                    result.add(student);
                }

            } catch (IOException e) {
                log.error("读取文件异常："+ e.getMessage());
            }
        } else {
            log.error("文件路径不能为空！");
        }
        return result;
    }

}
