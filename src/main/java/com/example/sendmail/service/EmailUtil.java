package com.example.sendmail.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.crypto.Data;
import java.util.Date;

    public class EmailUtil {

        private static final String NEW_ORDER_EMAIL_CONTENT = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Our Funky HTML Page</title>\n" +
                "<meta name=\"description\" content=\"Our first page\">\n" +
                "<meta name=\"keywords\" content=\"html tutorial template\">\n" +
                "</head>\n" +
                "<body>\n" +
                "Hello BIDV\n" +
                "</body>\n" +
                "</html>";

        public static String getNewOrderEmailContent() {

            String linkOrder = "https://www.bidvmetlife.com.vn/vn/";
            return String.format(NEW_ORDER_EMAIL_CONTENT, "NAMTV", "Than Nam",
                    linkOrder,new Date(), "Than Nam",
                    "123", "thannam27062001", "PH",
                    "0971265513", "100K");
        }

        public static String getNewOrderEmailSubject(String orderId) {
            return String.format("Hello BIDV ", orderId);
        }

        public static void sendEmail(Session session,EmailBody emailBody){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(emailBody.getFrom(), "namtv"));
            msg.setReplyTo(InternetAddress.parse("namtvph13393@fpt.edu.vn", false));
            Address[] addresses = new Address[emailBody.getTo().size()];
            for (int i = 0; i < emailBody.getTo().size(); i++) {
                addresses[i] = new InternetAddress(emailBody.getTo().get(i));
            }
            msg.setRecipients(Message.RecipientType.CC, addresses);
            msg.setSubject(emailBody.getSubject(), "UTF-8");
            msg.setSentDate(new Date());

            MimeMultipart mp = new MimeMultipart();
            BodyPart part = new MimeBodyPart();
            part.setContent(emailBody.getBody(), "text/html; charset=utf-8");
            mp.addBodyPart(part);
            msg.setContent(mp);
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
