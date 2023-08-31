package com.fep.forexampal.config;

//import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
//import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;

@Service
public class ImageServiceClient {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.setErrorHandler();
        //restTemplate.setRequestFactory(requestFactory());
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return new RestTemplate();
    }

/*    private HttpComponentsClientHttpRequestFactory requestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //factory.setHttpClient());
        factory.setConnectTimeout(5000);
        return factory;
    }*/

/*    private PoolingHttpClientConnectionManager getClientConnectionManager() {
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(200);
        clientConnectionManager.setDefaultMaxPerRoute(40);
        return clientConnectionManager;
    }*/
}
