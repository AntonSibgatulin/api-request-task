package ru.antonsibgatulin.entity.description;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Description {

    @JsonProperty("participantInn")
    private String participantInn;

    public String getParticipantInn() { return participantInn; }
    public void setParticipantInn(String value) { this.participantInn = value; }

}