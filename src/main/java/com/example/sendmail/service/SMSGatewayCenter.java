package com.example.sendmail.service;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;

public class SMSGatewayCenter {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC39c4f5f1f5683ff306387a56cd4f7793";
    public static final String AUTH_TOKEN = "[Redacted]";

    public static void main(String[] args) {
        Twilio.init("AC39c4f5f1f5683ff306387a56cd4f7793", "2abfbd51b6bd3d1b649739b042d4303b");
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+84971265513"),
                        "MG94952ca9511132e798bb3713f2217d8b",
                        "hello do van duoc, ban co khoe khong?")
                .create();

        System.out.println(message.getSid());
    }
}