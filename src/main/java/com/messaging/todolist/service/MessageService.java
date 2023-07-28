package com.messaging.todolist.service;

import com.messaging.todolist.controller.MessageController;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class MessageService {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    public static final String ACCOUNT_SID = "AC333a9a5464f68757f05aecbe2667049c";
    public static final String AUTH_TOKEN = "2b66769dc3fc220c3b35183c69d43b13";

    @Value("${auth.sid}")
    private String authSID;

    @Value("${auth.token}")
    private String authToken;

    public void sendReply(String message) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String msgToSend = dtf.format(now) +": "+message;

        logger.info("Message to be sent : "+msgToSend);
        Twilio.init(authSID, authToken);
        Message msg = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+919868909794"),
                new com.twilio.type.PhoneNumber("whatsapp:+12187481670"),
                        msgToSend)
                        .create();

        logger.info("Message sent successfully");
    }
}
