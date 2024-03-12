package ru.antonsibgatulin.entity.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.antonsibgatulin.entity.description.Description;
import ru.antonsibgatulin.entity.product.Product;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;


import java.time.LocalDate;

public class Document {

    @JsonProperty("description")
    private Description description;

    @JsonProperty("doc_id")
    private String docID;

    @JsonProperty("doc_status")
    private String docStatus;

    @JsonProperty("doc_type")
    private String docType;

    @JsonProperty("importRequest")
    private boolean importRequest;

    @JsonProperty("owner_inn")
    private String ownerInn;

    @JsonProperty("participant_inn")
    private String participantInn;

    @JsonProperty("producer_inn")
    private String producerInn;

    @JsonProperty("production_date")
    private LocalDate productionDate;

    @JsonProperty("production_type")
    private String productionType;

    @JsonProperty("products")
    private Product[] products;

    @JsonProperty("reg_date")
    private LocalDate regDate;

    @JsonProperty("reg_number")
    private String regNumber;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description value) {
        this.description = value;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String value) {
        this.docID = value;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String value) {
        this.docStatus = value;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String value) {
        this.docType = value;
    }

    public boolean getImportRequest() {
        return importRequest;
    }

    public void setImportRequest(boolean value) {
        this.importRequest = value;
    }

    public String getOwnerInn() {
        return ownerInn;
    }

    public void setOwnerInn(String value) {
        this.ownerInn = value;
    }

    public String getParticipantInn() {
        return participantInn;
    }

    public void setParticipantInn(String value) {
        this.participantInn = value;
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

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String value) {
        this.productionType = value;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] value) {
        this.products = value;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate value) {
        this.regDate = value;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String value) {
        this.regNumber = value;
    }


}