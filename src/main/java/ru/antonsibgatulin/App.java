package ru.antonsibgatulin;

import ru.antonsibgatulin.entity.description.Description;
import ru.antonsibgatulin.entity.document.Document;
import ru.antonsibgatulin.entity.product.Product;
import ru.antonsibgatulin.model.api.IApiRequest;
import ru.antonsibgatulin.model.api.IApiRequestAdapter;
import ru.antonsibgatulin.model.api.alone.AloneApiImpl;
import ru.antonsibgatulin.model.api.multiply.MultiplyApiImpl;

import java.time.LocalDate;
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

    public static Document generateTestDocument() {
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

}
