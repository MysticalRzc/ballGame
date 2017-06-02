package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageCache {

    private static String[] messageCache;

    public MessageCache(BufferedReader in) {
        messageCache = new String[100];
        this.getThread(in);
    }

    public String getMessage(int index) {
        Date date = new Date();
        long begin = date.getTime();
        while (messageCache[index] == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MessageCache.class.getName()).log(Level.SEVERE, null, ex);
            }
//            System.out.println(messageCache[index]);
            //System.out.println("waiting...");
            date = new Date();
            long end = date.getTime();
            if (end - begin > 2000) {
                GameClient.getMessageOutput().append("MessageCache outPut: getMessage" + "null" + "\n");
                return null;
            }
        }
        GameClient.getMessageOutput().append("MessageCache outPut: getMessage" + messageCache[index] + "\n");
        GameClient.getMessageOutput().setCaretPosition(GameClient.getMessageOutput().getText().length());
        String message = messageCache[index];
        messageCache[index] = null;
//        System.out.println(message);
        return message;
    }

    public boolean setMessage(String message, int index) {
        GameClient.getMessageOutput().append("MessageCache outPut: setMessage" + message + "\n");
        GameClient.getMessageOutput().setCaretPosition(GameClient.getMessageOutput().getText().length());
        messageCache[index] = message;
        return true;
    }

    private void getThread(BufferedReader in) {
        new Thread(new Runnable() {
            String message;

            public void run() {
                while (true) {
                    try {
                        if ((message = in.readLine()) != null) {
                            messageCache[Integer.parseInt(message.substring(0, 3))] = message;
                            GameClient.getMessageOutput().append("MessageCache outPut: setMessage" + message + "\n");
                            GameClient.getMessageOutput().setCaretPosition(GameClient.getMessageOutput().getText().length());
//                            setMessage(message, Integer.parseInt(message.substring(0, 3)));
                        }
//                        System.out.println(message);
                    } catch (IOException ex) {
                        Logger.getLogger(MessageCache.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }).start();
    }
}
