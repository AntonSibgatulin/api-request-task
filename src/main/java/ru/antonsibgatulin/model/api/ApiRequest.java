package ru.antonsibgatulin.model.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ru.antonsibgatulin.entity.description.Description;
import ru.antonsibgatulin.entity.document.Document;
import ru.antonsibgatulin.entity.product.Product;
import ru.antonsibgatulin.utils.TImeUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public abstract class ApiRequest {
    private final String API_URL = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private Long lastRequestTime = 0L;

    private long requestLimitInterval;

    private TimeUnit timeUnit;
    private int requestLimit;

    public ApiRequest(TimeUnit timeUnit, int requestLimit) {

        this.httpClient = HttpClients.createDefault();

        this.objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());


        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;

        requestLimitInterval = TImeUtils.getChronoUnit(timeUnit).getDuration().getSeconds() * 1000 / requestLimit;


    }


    public void waitForRequestLimit() throws InterruptedException {

        long now = System.currentTimeMillis();
        synchronized (lastRequestTime) {
            if (now - lastRequestTime > requestLimitInterval) {
                lastRequestTime = now;
            } else {
                Thread.sleep(requestLimitInterval - (now - lastRequestTime));
                waitForRequestLimit();
            }
        }

    }


    protected void doApiRequest(Document document, String signature) {
        System.out.println(Thread.currentThread().getId() + " started");
        try {
            HttpPost request = new HttpPost(getAPI_URL());
            request.setEntity(new StringEntity(getJsonDataInString(document), StandardCharsets.UTF_8));
            request.setHeader("Content-Type", "application/json");
            request.setHeader("X-Signature", signature);
            try (CloseableHttpResponse response = getHttpClient().execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                }
            }
        } catch (Exception ignore) {
            System.out.println(ignore); // или любое другое логирование
        }
    }

    private String getJsonDataInString(Document document) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(document);
    }


    public String getAPI_URL() {
        return API_URL;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public long getRequestLimitInterval() {
        return requestLimitInterval;
    }
}
