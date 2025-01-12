package com.chaney.infra.graalpy.client;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHost;

public class HttpClientWithBasicAuth {
    public static CloseableHttpClient createHttpClientWithBasicAuth(String username, String password) {
        // 创建一个 BasicCredentialsProvider 实例
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        // 设置认证信息，包括用户名、密码以及认证范围
        credentialsProvider.setCredentials(new AuthScope(new HttpHost("http://localhost:2812")),
                new UsernamePasswordCredentials(username, password.toCharArray()));

        // 创建 HttpClient 实例，并传入认证信息提供者

        return HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
    }
}