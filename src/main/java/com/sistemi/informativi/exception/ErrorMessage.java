package com.sistemi.informativi.exception;

import java.util.Date;

/*
Oggetto Messaggio di Eccezione all'interno
dei JSON di risposta ce verrano costruiti
dalla Classe Exception Advice
 */
public class ErrorMessage {

    private int statusCode;

    private Date date;

    private String message;

    private String description;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ErrorMessage(int statusCode, Date date, String message, String description) {
        this.statusCode = statusCode;
        this.date = date;
        this.message = message;
        this.description = description;
    }
}
