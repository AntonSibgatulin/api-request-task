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

    private Map<IUser, Instant> lastRequestTimes = new HashMap<>();

    public MultiplyApiImpl(TimeUnit timeUnit, int requestLimit) {
        super(timeUnit, requestLimit);
    }


    public void waitForRequestLimit(IUser iUser) throws InterruptedException {
        synchronized (iUser.getUser().getId()) {
            Instant lastRequestTime = lastRequestTimes.get(iUser);
            if (lastRequestTime == null) {
                lastRequestTime = Instant.now().minus(getRequestLimitInterval());
                lastRequestTimes.put(iUser, lastRequestTime);
            }
            Instant now = Instant.now();
            if (lastRequestTime.plus(getRequestLimitInterval()).isBefore(now)) {
                getRequestSemaphore().release();
            } else {
                getRequestSemaphore().acquire();
            }
            lastRequestTimes.put(iUser, now);
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
