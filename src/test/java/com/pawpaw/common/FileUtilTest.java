package com.pawpaw.common;

import com.pawpaw.common.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileUtilTest {


    @Test
    public void getExtension() {
        File file = new File("C:\\Users\\liujixin\\Desktop\\股票池.sel");
        String ext = FileUtil.getExtension(file);
        System.out.println(ext);
    }


    @Test
    public void changeExtension() {
        File file = new File("C:\\Users\\liujixin\\Desktop\\股票池.sel");
        boolean isSucc = FileUtil.changeExtension(file, "txt");
        System.out.println(isSucc);
    }


    @Test
    public void prepareFile() throws IOException {
        File file = new File("F:/a/b/c/1.txt");
        FileUtil.prepareFile(file);
    }

}




