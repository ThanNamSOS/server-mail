package com.example.sendmail.service;


public class EmailRequest {

    public EmailRequest() {

    }

    public EmailRequest(String[] to, String[] cc, String[] bcc, String subject, String body, boolean isHtml) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.isHtml = isHtml;
    }

    private String[] to;

    private String[] cc;

    private String[] bcc;

    private String subject;

    private String body;

    private boolean isHtml;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean html) {
        isHtml = html;
    }


}
