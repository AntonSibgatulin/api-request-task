package ru.antonsibgatulin.model.api;

import ru.antonsibgatulin.entity.document.Document;
import ru.antonsibgatulin.model.user.IUser;

import java.time.Duration;
import java.time.Instant;

public interface IApiRequest {


    public void createDocument(Document document, String signature, IUser iUser);


}
