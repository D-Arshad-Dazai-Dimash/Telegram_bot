
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class Telegram_Arshad extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        int a =0;
        nextMessege(update , a);
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "Telegram_Arshad";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5810628850:AAE21IWkCGt6LpqxsXsezJlzXYpbY63yX7k";
    }

    public int sumTwoOperation(int a , String str){
        return a;
    }

    public String nextMessege(Update update, int a){
        StringBuilder str = new StringBuilder();
        str.append("");
        String strA =String.valueOf(str.append(update.getMessage().getText()));
        strA = strA.trim();
        if(String.valueOf(str).equals("/end")){
            return "";
        }
        String[] words = strA.split(" ");
        if(words.length == 1){
            return "";
        }
        int b =a;
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (words.length == 2) {
                a = a + Integer.parseInt(String.valueOf(words[0])) + Integer.parseInt(String.valueOf(words[1]));
            } else if (words.length == 3) {
                    a = a + Integer.parseInt(String.valueOf(words[2])) + Integer.parseInt(String.valueOf(words[1]));
            }
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage(); // Create a message object
            message.setChatId(chat_id);
            message.setText(String.valueOf(a));

            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return nextMessege(new Update() , b);
    }
}