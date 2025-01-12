package com.chaney.infra.graalpy;

import com.chaney.infra.graalpy.client.HttpClientWithBasicAuth;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class MonitClient {

    // 创建 HttpClient
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    // 查询指定名称的应用状态，，获取 token
    public Map<String, String> queryStatus(String instanceName) throws IOException {
        // 向 localhost:2812 发送请求 /{{instanceName}} 带有basicAuth
        HttpGet httpGet = new HttpGet("http://localhost:2812/" + instanceName);
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        httpGet.setHeader("Authorization", "Basic YWRtaW46bW9uaXQ=");
        return httpClient.execute(httpGet, this::handleResponse);
    }

    private Map<String, String> handleResponse(ClassicHttpResponse response) throws IOException {
        String token = null;
        String cookie;
        Header[] responseHeaders = response.getHeaders("Set-Cookie");
        for (Header responseHeader : responseHeaders) {
            if (responseHeader.getName().equals("Set-Cookie")) {
                cookie = responseHeader.getValue();
                token = cookie.split(";")[0].split("=")[1];
            }
        }
        byte[] bytes = response.getEntity().getContent().readAllBytes();
        Document doc = Jsoup.parse(new String(bytes, StandardCharsets.UTF_8));
        Element statusTable = doc.getElementById("status-table");
        if (null == statusTable) {
            return new HashMap<>();
        }

        // 创建一个 Map 来存储键值对
        Map<String, String> keyValuePairs = new HashMap<>();

        // 遍历表格中的每一行 <tr>
        Elements rows = statusTable.select("tr");
        for (Element row : rows) {
            Elements columns = row.select("td");
            if (columns.size() == 2) {
                String key = columns.get(0).text();
                String value = columns.get(1).text();
                keyValuePairs.put(key, value);
            }
        }
        keyValuePairs.put("token-csrf", token);
        return keyValuePairs;
    }

    public Map<String, String> restart(String instanceName) throws IOException {

        Map<String, String> status;
        try {
            status = queryStatus(instanceName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String token = status.get("token-csrf");
        HttpPost httpPost = new HttpPost("http://localhost:2812/" + instanceName);
        httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpPost.addHeader("Authorization", "Basic YWRtaW46bW9uaXQ=");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", "securitytoken=" + token);
        httpPost.addHeader("Origin", "http://localhost:2812");

        BasicNameValuePair securitytoken = new BasicNameValuePair("securitytoken", token);
        BasicNameValuePair action = new BasicNameValuePair("action", "restart");
        httpPost.setEntity(new UrlEncodedFormEntity(List.of(securitytoken, action)));
        return httpClient.execute(httpPost, this::handleResponse);
    }


    // 通过命令操作对应用状态执行 操作
}
