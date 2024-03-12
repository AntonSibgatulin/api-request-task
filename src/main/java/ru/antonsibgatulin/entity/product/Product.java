package ru.antonsibgatulin.entity.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Product {

    @JsonProperty("certificate_document")
    private String certificateDocument;

    @JsonProperty("certificate_document_date")
    private LocalDate certificateDocumentDate;

    @JsonProperty("certificate_document_number")
    private String certificateDocumentNumber;

    @JsonProperty("owner_inn")
    private String ownerInn;

    @JsonProperty("producer_inn")
    private String producerInn;

    @JsonProperty("production_date")
    private LocalDate productionDate;

    @JsonProperty("tnved_code")
    private String tnvedCode;

    @JsonProperty("uit_code")
    private String uitCode;

    @JsonProperty("uitu_code")
    private String uituCode;

    public String getCertificateDocument() {
        return certificateDocument;
    }

    public void setCertificateDocument(String value) {
        this.certificateDocument = value;
    }

    public LocalDate getCertificateDocumentDate() {
        return certificateDocumentDate;
    }

    public void setCertificateDocumentDate(LocalDate value) {
        this.certificateDocumentDate = value;
    }

    public String getCertificateDocumentNumber() {
        return certificateDocumentNumber;
    }

    public void setCertificateDocumentNumber(String value) {
        this.certificateDocumentNumber = value;
    }

    public String getOwnerInn() {
        return ownerInn;
    }

    public void setOwnerInn(String value) {
        this.ownerInn = value;
    }

    public String getProducerInn() {
        return producerInn;
    }

    public void setProducerInn(String value) {
        this.producerInn = value;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate value) {
        this.productionDate = value;
    }

    public String getTnvedCode() {
        return tnvedCode;
    }

    public void setTnvedCode(String value) {
        this.tnvedCode = value;
    }

    public String getUitCode() {
        return uitCode;
    }

    public void setUitCode(String value) {
        this.uitCode = value;
    }

    public String getUituCode() {
        return uituCode;
    }

    public void setUituCode(String value) {
        this.uituCode = value;
    }
}
