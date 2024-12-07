package com.yan.demo.base.xls.read;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

/**
 * 小交通业务
 *
 * @author : Y
 * @since 2024/9/20 16:59
 */
public class SassPushBigTable {

    public static void main(String[] args) throws Exception {
        pushOne("YC2409130012371205");
    }

    public static void pushOne(String ddbh) throws Exception {
        if (StringUtils.isBlank(ddbh)) {
            System.out.println("订单编号为空");
            return;
        }
        URL url = new URL("https://fee.abctrip.cn/usecar/fca/usecar/normal/synchClfyBb");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Connection", "keep-alive");
        httpConn.setRequestProperty("Pragma", "no-cache");
        httpConn.setRequestProperty("Cache-Control", "no-cache");
        httpConn.setRequestProperty("ve-currency", "CNY");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        httpConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpConn.setRequestProperty("Accept", "*/*");
        httpConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        httpConn.setRequestProperty("ve-timezone", "Asia/Shanghai");
        httpConn.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"");
        httpConn.setRequestProperty("Origin", "https://fee.abctrip.cn");
        httpConn.setRequestProperty("Sec-Fetch-Site", "same-origin");
        httpConn.setRequestProperty("Sec-Fetch-Mode", "cors");
        httpConn.setRequestProperty("Sec-Fetch-Dest", "empty");
        httpConn.setRequestProperty("Referer", "https://fee.abctrip.cn/fca/usecar/normal/monitor.html");
        httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        httpConn.setRequestProperty("Cookie", "h5VeLanguage=zhcn; uid_vetech_yw=CgpvGWUfYjizVRMgCVgWAg==; veLanguage=zh-CN; VE_FCCUI=7afd0f12-256d-4649-bda2-b3d7b75b5ec7; FCA_SESSION=5bb6c61eac054f36ae4e3d74a7b260b5; VE_FCSAASUI=0fd18593-61dd-49c2-9bf4-3f9f3bad3bd7");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("{\"ddbh\":\"" + ddbh + "\"}");
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();
        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(new Date() + ":" + ddbh + ":" + response);
    }
}