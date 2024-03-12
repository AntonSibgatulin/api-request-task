package ru.antonsibgatulin.model.api.alone;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import ru.antonsibgatulin.entity.document.Document;
import ru.antonsibgatulin.model.api.IApiRequest;
import ru.antonsibgatulin.model.api.ApiRequest;
import ru.antonsibgatulin.model.api.IApiRequestAdapter;
import ru.antonsibgatulin.model.user.IUser;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class AloneApiImpl extends ApiRequest implements IApiRequest, IApiRequestAdapter {


    public AloneApiImpl(TimeUnit timeUnit, int requestLimit) {
        super(timeUnit, requestLimit);
    }


    @Override
    public void createDocument(Document document, String signature, IUser iUser) {
        try {
            this.waitForRequestLimit();
        } catch (InterruptedException e) {
            return;
        }
        doApiRequest(document, signature);
    }

    @Override
    public void createDocument(Document document, String signature) {
        createDocument(document, signature, null);
    }
}
