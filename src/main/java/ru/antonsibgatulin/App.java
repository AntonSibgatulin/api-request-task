package ru.antonsibgatulin;

import ru.antonsibgatulin.entity.document.Document;
import ru.antonsibgatulin.model.api.IApiRequest;
import ru.antonsibgatulin.model.api.IApiRequestAdapter;
import ru.antonsibgatulin.model.api.alone.AloneApiImpl;
import ru.antonsibgatulin.model.api.multiply.MultiplyApiImpl;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        IApiRequestAdapter alone = new AloneApiImpl(TimeUnit.SECONDS, 5);
        IApiRequest multiply = new MultiplyApiImpl(TimeUnit.SECONDS, 5);

    }
}
