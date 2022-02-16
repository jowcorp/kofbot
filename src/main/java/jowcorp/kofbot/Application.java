package jowcorp.kofbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import jowcorp.kofbot.bots.KofBot;

@Controller
@SpringBootApplication
public class Application {
	
	@ResponseBody
	@RequestMapping("/")
	public String home() {
		return "O KofBot está no ar!";
	}

	public static void main(String[] args) {
		try {
			TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
			api.registerBot(new KofBot()); // Registra o robô e permite que receba comandos no aplicativo
			SpringApplication.run(Application.class, args);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
}
