package com.messaging.todolist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

@Service
public class TelegramService {

	@Value("${telegram.apitoken}")
	String apiToken;

	@Value("${telegram.chatid}")
	String chatId;

	@Value("${telegram.url}")
	String urlString;

	//@Value("${apb.proxy.hostname}")
	//String proxyhost;

	//@Value("${apb.proxy.port}")
	//Integer proxyport;

	//@Value("${apb.proxy.on}")
	//boolean proxyon;

  private static Logger log = LoggerFactory.getLogger(TelegramService.class);

	//@Async("telegramExecutorSize")
	public void sendAlert(String message) {

		log.info("Sending Telegram Notification... for {}", message);

		try {
			String myurlString = String.format(urlString, apiToken, chatId, message);

			//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyhost, proxyport));
			URL url = new URL(myurlString);
			URLConnection conn = null;
    /*
			if (proxyon) {
				conn = url.openConnection(proxy);
			} else {
				conn = url.openConnection();
			}
    */

      			conn = url.openConnection();
			StringBuilder sb = new StringBuilder();
			InputStream is = new BufferedInputStream(conn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine = "";
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			String response = sb.toString();
			log.info("Response from Telegram: {}", response);
		} catch (Exception e) {
			log.error("Exception occured while sending alert : error message : {} ", e.getMessage());
		}
	}
}
