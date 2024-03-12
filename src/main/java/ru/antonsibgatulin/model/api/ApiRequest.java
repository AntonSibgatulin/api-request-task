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
    private final Semaphore requestSemaphore;
    private final Duration requestLimitInterval;
    private Instant lastRequestTime;

    public ApiRequest(TimeUnit timeUnit, int requestLimit) {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        this.requestSemaphore = new Semaphore(requestLimit);

        ChronoUnit chronoUnit = TImeUtils.getChronoUnit(timeUnit);

        this.requestLimitInterval = Duration.of(1, chronoUnit);

        lastRequestTime = Instant.now().minus(this.requestLimitInterval);
    }


    public void waitForRequestLimit() throws InterruptedException {
        Instant now = Instant.now();
        if (lastRequestTime.plus(getRequestLimitInterval()).isBefore(now)) {
            lastRequestTime = now;
            getRequestSemaphore().release();
        } else {
            getRequestSemaphore().acquire();
            lastRequestTime = Instant.now();
        }
    }

    protected void doApiRequest(Document document, String signature) {
        try {
            HttpPost request = new HttpPost(getAPI_URL());
            request.setEntity(new StringEntity(getJsonDataInString(), StandardCharsets.UTF_8));
            request.setHeader("Content-Type", "application/json");
            request.setHeader("X-Signature", signature);
            try (CloseableHttpResponse response = getHttpClient().execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
            }
        } catch (Exception ignore) {
            System.out.println(ignore); // или любое другое логирование
        }
    }

    private String getJsonDataInString() throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(generateDocument());
    }

    private Document generateDocument() {
        Document document = new Document();
        //description
        document.setDescription(new Description());
        document.getDescription().setParticipantInn("string");
        // document
        document.setDocID("string");
        document.setDocStatus("string");
        document.setDocType("LP_INTRODUCE_GOODS");
        document.setImportRequest(true);
        document.setOwnerInn("string");
        document.setParticipantInn("string");
        document.setProducerInn("string");
        document.setProductionDate(LocalDate.of(2020, 1, 23));
        document.setProductionType("string");
        // product
        document.setProducts(new Product[]{new Product()});
        document.getProducts()[0].setCertificateDocument("string");
        document.getProducts()[0].setCertificateDocumentDate(LocalDate.of(2020, 1, 23));
        document.getProducts()[0].setCertificateDocumentNumber("string");
        document.getProducts()[0].setOwnerInn("string");
        document.getProducts()[0].setProducerInn("string");
        document.getProducts()[0].setProductionDate(LocalDate.of(2020, 1, 23));
        document.getProducts()[0].setTnvedCode("string");
        document.getProducts()[0].setUitCode("string");
        document.getProducts()[0].setUituCode("string");

        document.setRegDate(LocalDate.of(2020, 1, 23));
        document.setRegNumber("string");
        return document;

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

    public Semaphore getRequestSemaphore() {
        return requestSemaphore;
    }

    public Duration getRequestLimitInterval() {
        return requestLimitInterval;
    }
}
