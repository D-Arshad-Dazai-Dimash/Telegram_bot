//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
////import org.telegram.telegrambots.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.*;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//public class Telegram_Book extends TelegramLongPollingBot {
//    public static boolean[][][] TT = new boolean[7][7][10];
//    public static int countBooking;
//    public static SendMessage message = new SendMessage(); int u =0;int a =0;int w; int b; int num; int first; int last; int dayOfTheWeek;
//    boolean cc; int week; int newcounter;
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update != null){
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            long chat_id = update.getMessage().getChatId();
//            if (update.getMessage().getText().trim().equals("/booking") && dayOfTheWeek == 0) {
//                u = 0;
//                a = 0;
//                w = 0;
//                b = 0;
//                num = 0;
//                newcounter = 0;
//                first = 0;
//                last = 0;
//                cc = true;
//                // Create a message object
//                message.setChatId(chat_id);
//                message.setText("Введите день недели начиная в которой вы хотите забронировать комнату\n1 - Понедельник \n2- Вторник \n3 - Среда \n4-Четверг \n5 - Пятница \n6 - Суббота \n7 - Воскресенье");
//                dayOfTheWeek++;
//            }
//            if(dayOfTheWeek > 0 && newcounter ==0 ){
//                onUpdateReceived(new Update());
//                if(newMessage(String.valueOf(update.getMessage().getText())) == -123456789){
//                    dayOfTheWeek = 0;
//                    message.setChatId(chat_id);
//                    message.setText("Введите день недели корректно (В цифровом формате)");
//                    onUpdateReceived(new Update());
//                }else{
//                    newcounter++;
//                    week = Integer.parseInt(update.getMessage().getText().trim());
//                }
//            }
//            if(countBooking ==0){
//                    countBooking =0;
//                    message.setChatId(chat_id);
//                    message.setText("Введите номер комнаты от 1 до 10");
//                    countBooking++;
//                }
//            while (a == 0 && w == 0) {
//                if (newMessage(update.getMessage().getText()) == -123456789) {
//                    message.setChatId(chat_id);
//                    message.setText("Введите номер комнаты корректно");
//                    onUpdateReceived(new Update());
//                } else {
//                    a++;
//                    num = Integer.parseInt(String.valueOf(update.getMessage().getText()));
//                    w++;
////                       u++;
//                }
//            }
//            if (w == 1) {
//                message.setChatId(chat_id);
//                message.setText("Введите промежуток времени в котором вы хотите бронировать \nформат ввода с 1 до 24 через пробел");
//                w++;
//                onUpdateReceived(new Update());
//            } else if (w > 1 && b == 0) {
//                String[] arr = update.getMessage().getText().trim().split(" ");
//                if ((Integer.parseInt(String.valueOf(arr[1]).trim())) >= 1 && Integer.parseInt(String.valueOf(arr[1]).trim()) <= 24 && Integer.parseInt(String.valueOf(arr[0]).trim()) >= 1 && Integer.parseInt(String.valueOf(arr[0]).trim()) <= 24 && Integer.parseInt(String.valueOf(arr[0])) < Integer.parseInt(String.valueOf(arr[1]))) {
//                    b++;
//                    first = Integer.parseInt(String.valueOf(arr[0]).trim());
//                    last = Integer.parseInt(String.valueOf(arr[1]).trim());
//                } else {
//                    b = 0;
//                    message.setChatId(chat_id);
//                    message.setText("Введите промежуток времени корректно");
//                    onUpdateReceived(new Update());
//                }
//            }
//            if (book(TT, dayOfTheWeek,num, first, last) == cc) {
//                message.setChatId(chat_id);
//                message.setText("C " + first + ":00 по " + last + ":00 вы забронировали номер " + num);
//            } else {
//                message.setChatId(chat_id);
//                message.setText("Номер " + num + " был забронировал кем то другим");
//                u = 0;
//                a = 0;
//                w = 0;
//                b = 0;
//                num = 0;
//                first = 0;
//                last = 0;
//                cc = true;
//            }
//            onUpdateReceived(new Update());
//            try {
//                execute(message); // Sending our message object to user
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//            }
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        // TODO
//        return "MyRoomReservationBot";
//    }
//
//    @Override
//    public String getBotToken() {
//        // TODO
//        return "5928332255:AAH0DJZHCwUKCTkg4PY7E4uY4VncXqhOfa4";
//    }
//    public static int newMessage(String update){
//        int a;
//            try {
//            a = Integer.parseInt(String.valueOf(update).trim());
//            }catch (NumberFormatException s){
//            a = -123456789;
//            }
//        return a;
//    }
//
//    public static boolean book(boolean[][][] room ,int week, int num ,int start, int endPoint) {
//        if (isBooked(room ,week, num ,start, endPoint)) {
//            return false;
//        }
//
//        for (int i = start-1; i < endPoint; i++) {
//            room[week][num-1][i] = true;
//        }
//        return true;
//    }
//    public static boolean isBooked(boolean[][][] room,int week,int num, int start, int end) {
//        for (int i = start-1; i < end; i++) {
//            if (room[week][num-1][i]) {
//                return true;
//            }
//        }
//        return false;
//    }
//}