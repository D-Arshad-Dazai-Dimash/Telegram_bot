import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class HotelBot extends TelegramLongPollingBot {

    private static final int NUM_BLOCKS = 5;
    private static final int NUM_FLOORS = 3;
    private static final int NUM_ROOMS = 10;
    private static final int START_TIME = 9;
    private static final int END_TIME = 20;

    private static boolean[][][][] rooms = new boolean[7][NUM_BLOCKS][NUM_FLOORS][NUM_ROOMS];
    private static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] BLOCKS = {"D", "E", "F", "G", "H"};

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (message.equals("/start")) {
                // Handle the "/start" command
                SendMessage startMessage = new SendMessage();
                startMessage.setChatId(chatId);
                startMessage.setText("Welcome to the Room Booking Bot!\nPlease enter the command '/book' to start booking a room.");
                try {
                    execute(startMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message.startsWith("/book")) {
                // Handle the "/book" command
                String[] bookCommand = message.split(" ");
                if (bookCommand.length != 4) {
                    SendMessage invalidCommand = new SendMessage();
                    invalidCommand.setChatId(chatId);
                    invalidCommand.setText("Invalid command. Please enter the command in the following format: '/book [day] [start time]-[end time]'.");
                    try {
                        execute(invalidCommand);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    int dayOfWeek = -1;
                    for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
                        if (DAYS_OF_WEEK[i].toLowerCase().startsWith(bookCommand[1].toLowerCase())) {
                            dayOfWeek = i;
                            break;
                        }
                    }
                    if (dayOfWeek == -1) {
                        SendMessage invalidDay = new SendMessage();
                        invalidDay.setChatId(chatId);
                        invalidDay.setText("Invalid day. Please enter a valid day of the week (Monday-Sunday).");
                        try {
                            execute(invalidDay);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String[] timeInterval = bookCommand[2].split("-");
                        int startTime = Integer.parseInt(timeInterval[0]);
                        int endTime = Integer.parseInt(timeInterval[1]);
                        if (startTime < START_TIME || startTime >= END_TIME || endTime <= startTime || endTime > END_TIME) {
                            SendMessage invalidTime = new SendMessage();
                            invalidTime.setChatId(chatId);
                            invalidTime.setText("Invalid time interval. Please enter a valid time interval between " + START_TIME + " and " + (END_TIME - 1) + ".");
                            try {
                                execute(invalidTime);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        } else {
                            List<String> availableRooms = getAvailableRooms(dayOfWeek, startTime, endTime);
                            if (availableRooms.isEmpty()) {
                                SendMessage noAvailableRooms = new SendMessage();
                                noAvailableRooms.setChatId(chatId);
                                noAvailableRooms.setText("Sorry, there are no available rooms at the specified time and day.");
                                try {
                                    execute(noAvailableRooms);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                SendMessage availableRoomsMessage = new SendMessage();
                                availableRoomsMessage.setChatId(chatId);
                                availableRoomsMessage.setText("The following rooms are available at the specified time and day:");
                                for (String room : availableRooms) {
                                    availableRoomsMessage.setText(availableRoomsMessage.getText() + "\n" + room);
                                }
                                try {
                                    execute(availableRoomsMessage);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                                // Add code here to handle booking the room
                            }
                        }
                    }
                }
            }
        }
    }

    private static List<String> getAvailableRooms(int dayOfWeek, int startTime, int endTime) {
        List<String> availableRooms = new ArrayList<>();
        for (int block = 0; block < NUM_BLOCKS; block++) {
            for (int floor = 0; floor < NUM_FLOORS; floor++) {
                for (int room = 0; room < NUM_ROOMS; room++) {
                    boolean isAvailable = true;
                    for (int time = startTime; time < endTime; time++) {
                        if (rooms[dayOfWeek][block][floor][room]) {
                            isAvailable = false;
                            break;
                        }
                    }
                    if (isAvailable) {
                        availableRooms.add(BLOCKS[block] + "-" + (floor + 1) + "-" + (room + 1));
                    }
                }
            }
        }
        return availableRooms;
    }


    @Override
    public String getBotUsername() {
        // TODO
        return "Telegram_Bot_Book";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5928332255:AAH0DJZHCwUKCTkg4PY7E4uY4VncXqhOfa4";
    }
}

