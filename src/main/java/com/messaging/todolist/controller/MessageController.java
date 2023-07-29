package com.messaging.todolist.controller;


import com.messaging.todolist.service.TelegramService;
import com.messaging.todolist.util.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    //@Autowired
    //private MessageService messageService;

    @Autowired
    private TelegramService messageService;

    @PostMapping("/sendmessage")
    public void getMessage(@RequestBody String body) {
        logger.info("MessageController json body" + body);
        Map<String, Object> requestBody = Utils.convertStringToMap(body);
        String message = String.valueOf(requestBody.get("message"));
        logger.info("message" + message);
        messageService.sendAlert(message);
    }

}
