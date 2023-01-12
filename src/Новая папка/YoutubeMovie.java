//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.channels.Channels;
//import java.nio.channels.ReadableByteChannel;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class YoutubeMovie extends TelegramLongPollingBot {
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            long chatId = update.getMessage().getChatId();
//
//            if (isYouTubeLink(messageText)) {
//                File video = downloadVideoFromYouTube(messageText);
//                if (video != null) {
//                    SendDocument sendDocument = new SendDocument();
//                    sendDocument.setChatId(chatId);
//                    sendDocument.setDocument(video);
//                    try {
//                        execute(sendDocument);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    private boolean isYouTubeLink(String messageText) {
//        // Regular expression to check if the message text is a valid YouTube link
//        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(messageText);
//        return matcher.matches();
//    }
//
//    private File downloadVideoFromYouTube(String youtubeLink) {
//        try {
//            // Use youtube-dl library to download the video
//            String command = "youtube-dl -f mp4 --output video.mp4 " + youtubeLink;
//            Process p = Runtime.getRuntime().exec(command);
//            p.waitFor();
//
//            // Return the downloaded video file
//            return new File("video.mp4");
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "my_bot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "my_bot_token";
//    }
//}