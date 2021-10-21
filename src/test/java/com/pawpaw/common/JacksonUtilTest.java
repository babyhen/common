package com.pawpaw.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.util.JacksonUtil;
import com.pawpaw.common.util.RestTemplateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class JacksonUtilTest {


    @Test
    public void test() throws JsonProcessingException {
        JacksonUtil util=new JacksonUtil();
        String s=util.object2Json(new Student("aa",new Date()));
        System.out.println(s);
        Student student=util.json2Object(s,Student.class);
        System.out.println(student);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Student {

    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birth;

}




