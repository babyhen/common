package com.pawpaw.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class FileUtil {


    public static String getJarFilePath() throws URISyntaxException {
        String path = FileUtil.class.getResource("/").getPath();
        log.info("resource path {}", path);
        if (StringUtils.contains(path, ".jar")) {
            int i = path.indexOf(".jar");
            path = path.substring(0, i + 4);
        }
        URI u = new URI(path);
        path = u.getPath();
        return path;
    }

    public static void delFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childs = file.listFiles();
            for (int i = 0; i < childs.length; i++) {
                delFile(childs[i]);
            }
        }

    }

    /**
     * 获取文件扩展名
     *
     * @param file
     * @return
     */
    public static String getExtension(File file) {
        String name = file.getName();
        return StringUtils.substringAfterLast(name, ".");
    }

    /**
     * 改变文件扩展名
     *
     * @param file
     * @return
     */
    public static boolean changeExtension(File file, String newExtension) {
        if (!file.exists()) {
            return false;
        }
        String fullName = file.getName();
        String name = StringUtils.substringBeforeLast(fullName, ".");
        File nf = null;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String newFullName = name;
            if (i != 0) {
                newFullName += "-" + i;
            }
            newFullName += "." + newExtension;
            nf = new File(file.getParentFile(), newFullName);
            if (!nf.exists()) {
                break;
            }
        }
        boolean b = file.renameTo(nf);
        return b;
    }

    /**
     * 准备文件
     * 如果是目录，那么则创建所有层级的目录
     * 如果是文件，如果没创建，则创建一个新的文件
     *
     * @param dest
     */
    public static void prepareFile(File dest) throws IOException {
        if (dest.exists()) {
            return;
        }
        createDir(dest.getParentFile());  //先创建上级目录，否则创建文件会失败
        boolean isCreateNewFile = dest.createNewFile();
        if (isCreateNewFile) {
            log.warn("创建了文件 {}", dest.getAbsolutePath());
        }
    }


    public static void createDir(File dest) {
        boolean isCreateNewDir = dest.mkdirs();
        if (isCreateNewDir) {
            log.warn("创建了目录{}", dest.getAbsolutePath());
        }
    }
}
