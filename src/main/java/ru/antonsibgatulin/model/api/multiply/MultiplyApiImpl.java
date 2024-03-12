package ru.antonsibgatulin.model.api.multiply;

import ru.antonsibgatulin.entity.document.Document;
import ru.antonsibgatulin.entity.user.UserData;
import ru.antonsibgatulin.model.api.ApiRequest;
import ru.antonsibgatulin.model.api.IApiRequest;
import ru.antonsibgatulin.model.user.IUser;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MultiplyApiImpl extends ApiRequest implements IApiRequest {

    private Map<String, Long> lastRequestTimes = new HashMap<>();

    public MultiplyApiImpl(TimeUnit timeUnit, int requestLimit) {
        super(timeUnit, requestLimit);
    }


    public void waitForRequestLimit(IUser iUser) throws InterruptedException {

        long now = System.currentTimeMillis();
        boolean sleep = false;

        String id = iUser.getUser().getId();
        Long lastRequestTime = null;
        synchronized (id.intern()) {
            lastRequestTime = lastRequestTimes.get(id);
            if (lastRequestTime == null) {
                lastRequestTime = 0L;
                lastRequestTimes.put(id, lastRequestTime);

            }
            if (now - lastRequestTime > getRequestLimitInterval()) {
                lastRequestTimes.put(id, now);
            } else {
                sleep = true;
            }

        }

        if (sleep) {
            Thread.sleep(getRequestLimitInterval() - (now - lastRequestTime));
            waitForRequestLimit();
        }
    }

    @Override
    public void createDocument(Document document, String signature, IUser iUser) {

        try {
            waitForRequestLimit(iUser);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        doApiRequest(document, signature);
    }
}
