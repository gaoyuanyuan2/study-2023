package com.yan.demo.base.tool;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 批量删除码云
 * @author : Y
 * @since 2024/10/7 11:24
 */
public class BatchDeleteGitee {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList(
                "java-training-camp-harden-code","","","","",""
                ,"","","","","",""
                ,"","","","","",""
                ,"","","","","",""
                ,"","","","","",""
                ,"","","","","","");
        for (String name : names) {
            if(StringUtils.isBlank(name)) continue;
            TimeUnit.SECONDS.sleep(1);
            String key = delete(name);
            delete2(name, key);
        }
    }

    public static String delete(String name) throws Exception {


        URL url = new URL("https://gitee.com/yanguowei/" + name);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("DELETE");

        httpConn.setRequestProperty("Connection", "keep-alive");
        httpConn.setRequestProperty("Pragma", "no-cache");
        httpConn.setRequestProperty("Cache-Control", "no-cache");
        httpConn.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"");
        httpConn.setRequestProperty("Accept", "*/*");
        httpConn.setRequestProperty("X-CSRF-Token", "L4ExIddSYs4krErmr2FkX2qzx2t4PPl/v2m5GphtUQdCUa603dXGX2iXh7krPjED52JEudDI+QNQUi4homWSNg==");
        httpConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpConn.setRequestProperty("Origin", "https://gitee.com");
        httpConn.setRequestProperty("Sec-Fetch-Site", "same-origin");
        httpConn.setRequestProperty("Sec-Fetch-Mode", "cors");
        httpConn.setRequestProperty("Sec-Fetch-Dest", "empty");
        httpConn.setRequestProperty("Referer", "https://gitee.com/yanguowei/alertmanager/settings");
        httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        httpConn.setRequestProperty("Cookie", "user_locale=zh-CN; oschina_new_user=false; sensorsdata2015jssdkchannel=%7B%22prop%22%3A%7B%22_sa_channel_landing_url%22%3A%22%22%7D%7D; remote_way=http; slide_id=10; BEC=1f1759df3ccd099821dcf0da6feb0357; tz=Asia%2FShanghai; Hm_lvt_24f17767262929947cc3631f99bfd274=1725969092,1726029503,1728269540; HMACCOUNT=4FC09AA8F3F164F9; gitee_user=true; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221890298%22%2C%22first_id%22%3A%2219264e45c63365-02988768dbb07f-4343363-2073600-19264e45c6410cb%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fcn.bing.com%2F%22%2C%22%24latest_utm_source%22%3A%22baidu%22%2C%22%24latest_utm_medium%22%3A%22sem%22%2C%22%24latest_utm_campaign%22%3A%22enterprise%22%2C%22%24latest_utm_content%22%3A%22competition%22%2C%22%24latest_utm_term%22%3A%22101164%22%7D%2C%22%24device_id%22%3A%2217c62569d8fc25-0b68ab2691e0cd-4343363-2073600-17c62569d90db2%22%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgzYzZkZDkwYmYzLTBmZTk3OTAzMDUyMDg1LTQzNDMzNjMtMjA3MzYwMC0xODNjNmRkOTBjMDZkYSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4OTAyOTgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%221890298%22%7D%7D; csrf_token=1aJ34cgRaH0G%2Fo88MdfD8OcKV8EuOrQN5OVNl4N1ocm4cuh0wpbM7ErFQmO1iJasatvUE4bOtHEL3tqsuX1i%2BA%3D%3D; Hm_lpvt_24f17767262929947cc3631f99bfd274=1728271374; gitee-session-n=MVNrOXFKUnNJQk9YbmZVY3ZILzA1cTRaVTB4bldWYU42WHExN2tzNUdmVlUvblgvOUh1OW9MdU02VHZyQzBOcXAwenc1T1phcUxTM1hUWXJwbTV5ZitDanhYYWsxcmhrM0MrUUp2UmltN3dRMDdNMzVLOWl4cStnc1loY3JSQ3Q4ZjBDUUI0dmlYN1hmTENKK3FucDNvTVdWaVZsWUU3dVMxYU9qeHlqVzZMaFZOZmw0bkp6N3NZaWtaNDJFbnB5QmNwTElJaFplbHg3WStOQkk5RnBKY1VjdVZEaHRscktPRFJFRndldUtPa005TlF2QVpydm82SENLenVTb2wwVGpubVpUV3pjMjZ6eWFzdkxjUXFKNW5NMFJzWnQvWkk3RTRQd3VGODl4UVpJYjV6Z2Znb2lBeU5ZQVAxVHdjZnhGRXRsLzFGT1pobEZ6Vnl4Tks0bGhqV3FoRS9SZ0JycUZlV1BlLzBlSEpPVE9KcmNSbW5QNmhHUVlyTVYwcjk1MFgydU9MTGNJZWcvUk1ndWpoT1hVeHlOYU1NVVIvUFhNaENLQnJOcDZmUjVyK0xFQ1o1RE5HK1FQU3I4Q2g3ckxGNDlzM2ZDSUtWbnBlaFRsNklSQ2dOUjBxUnN3T0Q4SnJzVmIvc2RCMzNLRC8yK1JsVDcrb0ViZTBzOHI5cjRpTkdLZ3J3cjV2RjI4YWpjWUtUL2dPaWRBSEFnbFJSaFRtMXZzSVZrYXJVZ2xqUXAyMnN4S29taGYrSkRGai92aEgzUnBEM1VZSW5hUVlNMy9tRXBaZzc2TmdtMWZKZlJ1SVBxZmVZck5JMzNUZ1k1dlhnajk3L21FalJQZjJvQy0tdysvL1B3UFlqWDFkMWRGK0FOYnpmdz09--11e7b3798d82f6503f8ea58243535a30a6ff443d");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("path_with_namespace=yanguowei%2F" + name);
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
        Pattern p = Pattern.compile("的所有数据将被删除，此操作无法恢复！.*?value=\"(.*?)\"");
        response = response.replaceAll("\\\\", "");
        Matcher m = p.matcher(response);
        if (m.find()) {
            m.find();
            System.out.println(m.group(1));
            return m.group(1);
        }
        return "";
    }

    public static void delete2(String name, String key) throws Exception {
        URL url = new URL("https://gitee.com/yanguowei/" + name);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Connection", "keep-alive");
        httpConn.setRequestProperty("Pragma", "no-cache");
        httpConn.setRequestProperty("Cache-Control", "no-cache");
        httpConn.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"");
        httpConn.setRequestProperty("Accept", "*/*;q=0.5, text/javascript, application/javascript, application/ecmascript, application/x-ecmascript");
        httpConn.setRequestProperty("X-CSRF-Token", "L4ExIddSYs4krErmr2FkX2qzx2t4PPl/v2m5GphtUQdCUa603dXGX2iXh7krPjED52JEudDI+QNQUi4homWSNg==");
        httpConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpConn.setRequestProperty("Origin", "https://gitee.com");
        httpConn.setRequestProperty("Sec-Fetch-Site", "same-origin");
        httpConn.setRequestProperty("Sec-Fetch-Mode", "cors");
        httpConn.setRequestProperty("Sec-Fetch-Dest", "empty");
        httpConn.setRequestProperty("Referer", "https://gitee.com/yanguowei/alertmanager/settings");
        httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        httpConn.setRequestProperty("Cookie", "user_locale=zh-CN; oschina_new_user=false; sensorsdata2015jssdkchannel=%7B%22prop%22%3A%7B%22_sa_channel_landing_url%22%3A%22%22%7D%7D; remote_way=http; slide_id=10; BEC=1f1759df3ccd099821dcf0da6feb0357; tz=Asia%2FShanghai; Hm_lvt_24f17767262929947cc3631f99bfd274=1725969092,1726029503,1728269540; HMACCOUNT=4FC09AA8F3F164F9; gitee_user=true; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221890298%22%2C%22first_id%22%3A%2219264e45c63365-02988768dbb07f-4343363-2073600-19264e45c6410cb%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fcn.bing.com%2F%22%2C%22%24latest_utm_source%22%3A%22baidu%22%2C%22%24latest_utm_medium%22%3A%22sem%22%2C%22%24latest_utm_campaign%22%3A%22enterprise%22%2C%22%24latest_utm_content%22%3A%22competition%22%2C%22%24latest_utm_term%22%3A%22101164%22%7D%2C%22%24device_id%22%3A%2217c62569d8fc25-0b68ab2691e0cd-4343363-2073600-17c62569d90db2%22%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgzYzZkZDkwYmYzLTBmZTk3OTAzMDUyMDg1LTQzNDMzNjMtMjA3MzYwMC0xODNjNmRkOTBjMDZkYSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4OTAyOTgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%221890298%22%7D%7D; Hm_lpvt_24f17767262929947cc3631f99bfd274=1728271374; csrf_token=gj8yNn5REMFSD0DCs9RMPHLZCKutujGmGhHann9gejDv762jdNa0UB40jZ03ixlg%2FwiLeQVOMdr1Kk2lRWi5AQ%3D%3D; gitee-session-n=UFRmaWIrS3VFZDJObHVEN1IyaGlhUmFyNk92SDlFc0RtRTlXb1VIbFJDUmw3Vk1zRDJQcDF1dm1SRzNvVVJWZW1jbTVGQm1pS3VRZzVaRVY2ZjVCV3RUN0xzamdIcFFSR250dks4Z0MyRzZzQWpVeEZGYWk3bXRjQkR3ZXUxbFpLWHFzTjlpVWtTMVVXbnFtVS9WYTdiV3IxZURpRzdscCtoNGhhbHBNMnpTWWoxUUhLc1AvYXlTeEYzbW85dC81S3JLUFFpRkRlOUdSU2F0NjRMVk1LUTU4RDR4Ymd2V2kvNmNJcVE0VmtFeFlCbDBBZ1lJT1hEQ2NNU1NCaHFYZFhlZVd3SXVaOHdIS3hzdzQ5LzNSYlFQRGJtVVVKcnIrdmhxMU1jaFFjVVREek9oY2x5MTZUdHR1bGVHZWJnaVhJRmI2Vk5MREVlbkkvdlFXREJ0MTFIZnltdnNUWGlNOUNuWWt6Rk5KbThkN2wxVFBxZkVVT0ZaVDhYdkgvN2VKU2RlbHExaTdleWNZZ3ZOaGlsNno0akZFVkNTOXBjcW5BOFh5bEIzU3E3RzJUemtzalhiNVhudkxxOG1HMlVQV2w4S2tVVXFoQ0xvdEpZRzAxdmdXMWJCdVkxVnpBNUJ4N2FLUCtjbXErV2lkR3NJQjVoTlhPVjVsaCtJZzNYWEpmdXVjSU5wN2xZbjlhVUpxL0plQU5VRnFQNGM5WGFkMXFaS3g3NkpYUDR2UU1Sa2Z5MU5jNEk2Um9HVloyYy9wYitmbHN4dmg0WmMzTWdEbmt1UWoyUk1LWGhXMHZrazNMbVMwU2IzbjJLRUtnalRKemt6NEJjOExZb2JMc3F4dC0tenFzSGFIcTdUK0pGQm5HTnJ0MXdQdz09--4994c75f3bb7175a731ae6c7131452c413427444");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("utf8=%E2%9C%93&_method=DELETE&_pavise%5Bbundle%5D=" + key + "&_pavise%5Bpassword%5D=ygw520ygw");
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}