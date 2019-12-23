package com.example.spring;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: Staro
 * @date: 2019/12/4 8:58
 * @Description:
 */
public class HttpTest {
    String url = "http://10.167.194.18:8008/studio/scene/iDScene";
    @Test
    public void restTemplate() {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("OAUTH_TOKEN", "iDS_ZSH_FOXCONN_NB");
        headers.add("Cookie","PYCKET_ID=2|1:0|10:1575419608|9:PYCKET_ID|48:MTQ2MjQ2ZjUtMjcxNC00ZDhiLWFhYWUtZWEyOGNkNGYwZGRk|191ad9a25878632094b262be06934eae225f718604379015f4ced73e2869d29b");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
//        headers.add("Content-Type", "application/json; charset=UTF-8");
//        headers.add("Access-Control-All-Credentials", "true");
//        headers.add("Access-Control-Allow-Headers", "*");
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS,PATCH,HEAD");
//        headers.add("Content-Length", "7479");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        System.out.println(exchange.getBody());
    }

    @Test
    public void httpclient(){
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("OAUTH_TOKEN", "iDS_ZSH_FOXCONN_NB");
                httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
                client = HttpClients.createDefault();
                response = client.execute(httpGet);
                org.apache.http.HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void httpURLConnection(){
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();

            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.setRequestProperty("OAUTH_TOKEN", "iDS_ZSH_FOXCONN_NB");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();

            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
