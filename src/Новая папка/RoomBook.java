//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class RoomBook extends TelegramLongPollingBot {
//    public static Map<Character, Map<Integer, Map<Integer ,Map<String , Map<Integer , String>>>>> ROOM = new HashMap<>();
//    public static Map<Integer, String> time = new HashMap<>();
//    int floor; int day; int room; int step1;int step2;int step3;int step4;
//    int case1; int case2; int case3;
//
//    SendMessage message = new SendMessage();
//    public RoomBook() {
//        for (int i = 0; i < 11; i++) {
//            time.put(i+9 , "Avalible");
//        }
//        // Initialize days of the week
//            }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            long chatId = update.getMessage().getChatId();
//            if(messageText.equals("/book") || case1> 0){
//                case1++;
//                if (step1 == 0) {
//                    step2 = 0;
//                    step3 = 0;
//                    step4 = 0;
//                    message.setChatId(chatId);
//                    message.setText("Enter floor number (1-7):");
//                    try {
//                        execute(message); // Sending our message object to user
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    step1++;
//                    onUpdateReceived(new Update());
//                } else if (step1 > 1 && step2 == 0) {
//                    floor = Integer.parseInt(update.getMessage().getText());
//                    message = new SendMessage();
//                    message.setChatId(chatId);
//                    message.setText("Enter room number (1-10):");
//                    try {
//                        execute(message); // Sending our message object to user
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    step2++;
//                    onUpdateReceived(new Update());
//                } else if (step2 > 0 && step3 == 0) {
//                    room = Integer.parseInt(update.getMessage().getText());
//                    message = new SendMessage();
//                    message.setChatId(chatId);
//                    message.setText("Enter day of the week:");
//                    try {
//                        execute(message); // Sending our message object to user
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    step3++;
//                    onUpdateReceived(new Update());
//                } else if (step4 == 0 && step3 > 0) {
//                    day = Integer.parseInt(update.getMessage().getText());
//                    bookRoom(floor, room, String.valueOf(day), chatId);
//                    step4++;
//                }
//                case1=0;
//                onUpdateReceived(new Update());
//            }else if(messageText.equals("/check") || case2>0){
//                case2++;
//                if (step1 == 0) {
//                    step2 = 0;
//                    step3 = 0;
//                    step4 = 0;
//                    SendMessage message = new SendMessage();
//                    message.setChatId(chatId);
//                    message.setText("Enter floor number (1-7):");
//                    try {
//                        execute(message); // Sending our message object to user
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    step1++;
//                    onUpdateReceived(new Update());
//                } else if (step1 > 0 && step2 == 0) {
//                    floor = Integer.parseInt(update.getMessage().getText());
//                    message = new SendMessage();
//                    message.setChatId(chatId);
//                    message.setText("Enter room number (1-10):");
//                    try {
//                        execute(message); // Sending our message object to user
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    step2++;
//                    onUpdateReceived(new Update());
//                } else if (step2 > 0 && step3 == 0) {
//                    room = Integer.parseInt(update.getMessage().getText());
//                    checkRoom(floor, room, chatId);
//                }
//                case2=0;
//                onUpdateReceived(new Update());
//            } else if(messageText.equals("/checkday")|| case3 >0){
//                case3++;
//                if (step1 == 0) {
//                    SendMessage message = new SendMessage();
//                    message.setChatId(chatId);
//                    message.setText("Enter day of the week:");
//                    try {
//                        execute(message); // Sending our message object to user
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    step1++;
//                    onUpdateReceived(new Update());
//                } else {
//                    day = Integer.parseInt(update.getMessage().getText());
//                    checkDay(String.valueOf(day), chatId);
//                }
//                case3=0;
//                onUpdateReceived(new Update());
//            }
////            else {
////                SendMessage message = new SendMessage();
////                message.setChatId(chatId);
////                message.setText("Неправильная команда");
////                try {
////                    execute(message); // Sending our message object to user
////                } catch (TelegramApiException e) {
////                    e.printStackTrace();
////                }
////            }
//            onUpdateReceived(new Update());
//        }
//    }
//    public void bookRoom(int floor, int room, String day, long chatId) {
//        if (ROOM.get(floor).get(room).equals("Available") && days.get(day).equals("Available")) {
//            ROOM.get(floor).put(room, "Booked");
//            days.put(day, "Booked");
//            SendMessage message = new SendMessage();
//            message.setChatId(chatId);
//            message.setText("Room has been successfully booked!");
//            try {
//                execute(message); // Sending our message object to user
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        } else {
//            SendMessage message = new SendMessage();
//            message.setChatId(chatId) ;
//            message.setText("Room is not available for booking on this day!");
//            try {
//                execute(message); // Sending our message object to user
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void checkRoom(int floor, int room, long chatId) {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Room status: " + ROOM.get(floor).get(room));
//        try {
//            execute(message); // Sending our message object to user
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void checkDay(String day, long chatId) {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Day status: " + days.get(day));
//        try {
//            execute(message); // Sending our message object to user
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        // TODO
//        return "Telegram_Bot_Book";
//    }
//
//    @Override
//    public String getBotToken() {
//        // TODO
//        return "5928332255:AAH0DJZHCwUKCTkg4PY7E4uY4VncXqhOfa4";
//    }
//
//}
//
//
