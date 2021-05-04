package com.dvp6.grupo1.userdetails.model;

public class OpenTicket {
    private String username;
    private String subject;
    private String description;
    private String status;

    /*
     * Método construtor da classe.
     */
    public OpenTicket(String username, String subject, String description, String status) {
        this.username = username;
        this.subject = subject;
        this.description = description;
        this.status = status;
    }

    /*
     * Métodos Getters e Setters.
     */
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
