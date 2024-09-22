package be.springboot.pp.externalconfigs;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomRestClient {

//    @Value("${http.max.total:100}")
//    private int maxTotal;
//
//    @Value("${http.max.per.route:25}")
//    private int defaultMaxPerRoute;
//
//    @Value("${http.default.keep.alive:20000}")
//    private int defaultKeepAliveTime;
//
//    @Value("${http.idle.connection.timeout:60000}")
//    private int idleConnectionTimeout;

//    @Value("${http.connection.request.timeout:5000}")
//    private int connectionRequestTimeout;

//    @Value("${http.connect.timeout:5000}")
//    private int connectTimeout;

//    @Value("${http.read.timeout:45000}")
//    private int readTimeout;

//    @Bean
//    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy100() {
//        return (response, context) -> {
//            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
//            while (it.hasNext()) {
//                HeaderElement he = it.nextElement();
//                String param = he.getName();
//                String value = he.getValue();
//
//                if (value != null && param.equalsIgnoreCase("timeout")) {
//                    return Long.parseLong(value) * 1000;
//                }
//            }
//            return defaultKeepAliveTime;
//        };
//    }

    public RestTemplate getCustomRestTemplate() {

        // modify settings/configurations of RestTemplate
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setMaxTotal(maxTotal);
//        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
//        CloseableHttpClient httpClient =
//                HttpClientBuilder
//                        .create()
//                        .setConnectionManager(connectionManager)
//                        .setKeepAliveStrategy(connectionKeepAliveStrategy100())
//                        .evictIdleConnections(idleConnectionTimeout, TimeUnit.MILLISECONDS)
//                        .build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectionRequestTimeout(connectionRequestTimeout);
//        factory.setConnectTimeout(connectTimeout);
//        factory.setReadTimeout(readTimeout);
//        return new RestTemplate(factory);
        return new RestTemplate();
    }
}
