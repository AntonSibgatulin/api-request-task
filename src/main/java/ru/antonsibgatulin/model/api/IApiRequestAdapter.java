package ru.antonsibgatulin.model.api;

import ru.antonsibgatulin.entity.document.Document;
import ru.antonsibgatulin.model.user.IUser;

public interface IApiRequestAdapter {
    public void createDocument(Document document, String signature);

}
