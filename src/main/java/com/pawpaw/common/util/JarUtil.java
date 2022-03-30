package com.pawpaw.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Slf4j
public class JarUtil {
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 获得MANIFEST.MF文件里的属性值
     *
     * @param clz
     * @param name
     * @return
     * @throws IOException
     */
    public static String getManiFestField(Class clz, String name) throws IOException {
        InputStream stream = clz.getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
        Manifest manifest = new Manifest(stream);
        Attributes attributes = manifest.getMainAttributes();
        String value = attributes.getValue(name);
        return value;
    }

}
