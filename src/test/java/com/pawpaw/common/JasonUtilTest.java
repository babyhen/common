package com.pawpaw.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.json.JacksonUtil;
import com.pawpaw.common.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class JasonUtilTest {
    static Dog dog;

    @BeforeClass
    public static void init() {
        dog = new Dog();
        dog.setAge(4);
        dog.setName("周正送");


    }

    @Test
    public void json2Object() throws JsonProcessingException {
        String str = JsonUtil.object2Json(dog);
        System.out.println(str);
        String name = JsonUtil.json2Object(str, "name", String.class);
        System.out.println(name);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Dog {
    private int age;
    private String name;
    private Date birth;

}




