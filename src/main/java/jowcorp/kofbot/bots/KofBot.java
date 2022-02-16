package jowcorp.kofbot.bots;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;
import static org.telegram.telegrambots.meta.api.methods.ParseMode.MARKDOWN;

import java.util.logging.Logger;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class KofBot extends TelegramLongPollingBot  {
	
	private static final String BOT_NAME = System.getenv("BOT_NAME");
	
	private static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
	
	private static final Logger LOG = Logger.getLogger(KofBot.class.getName());
	
	private void sendMessage(String msg, String chatId) throws TelegramApiException {
		SendMessage sm = new SendMessage();
		sm.setText(msg);
		sm.setParseMode(MARKDOWN);
		sm.setChatId(chatId);
		LOG.log(INFO, "Chat id: {0}", chatId);
		execute(sm);
	}
	
	public KofBot() {
		super();
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		try {
			
			if (update.hasMessage() && update.getMessage().hasText()) {
				String text = update.getMessage().getText();
				
				switch (text) {
				case "/start":
					sendMessage("Bem-vindo ao melhor robô do mundo!\n*YEAAAAH*\n⬇️➡️↘️ + 👊", update.getMessage().getChatId().toString());					
					LOG.log(INFO, "Robozinho estralando");
					break;
					
				case "/shibaraias":
					sendMessage("Shibaraias and labashúrias", update.getMessage().getChatId().toString());
					LOG.log(INFO, "Shibaraias and labashúrias");
					break;
				default:
					break;
				}
			}
			
		} catch (TelegramApiException e) {
			LOG.log(FINE, "Um problema aconteceu ao tentar criar a mensagem.");
			
		} catch (Exception e) {
			LOG.log(FINE, "Um erro inesperado ocorreu.");
		}
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

}
