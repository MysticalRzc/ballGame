package Server;

import static Server.GameServer.messageOutput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

public class ClientThread extends SwingWorker<List<String>, String> {

    private Socket toClientSocket; //会话套接字
    private BufferedReader in; //输入流
    private PrintWriter out; //输出流
    private static int clientId;
    private static int gameHomeId;
    private GameThread gameThread = null;

    public ClientThread(Socket toClientSocket, int gameName) { //构造函数
        this.toClientSocket = toClientSocket;
        this.clientId = gameName;
    }

    private void refreash() {
        if (GameServer.gameId.getGameIDNumber(1) == 0) {
            GameServer.home1.setText("未创建");
            GameServer.home1SetNum.setText("0");
        } else {
            GameServer.home1.setText("已创建");
            GameServer.home1SetNum.setText("" + GameServer.gameId.getGameIDNumber(1));
        }
        if (GameServer.gameId.getGameIDNumber(2) == 0) {
            GameServer.home2.setText("未创建");
            GameServer.home2SetNum.setText("0");
        } else {
            GameServer.home2.setText("已创建");
            GameServer.home2SetNum.setText("" + GameServer.gameId.getGameIDNumber(2));
        }
        if (GameServer.gameId.getGameIDNumber(3) == 0) {
            GameServer.home3.setText("未创建");
            GameServer.home3SetNum.setText("0");
        } else {
            GameServer.home3.setText("已创建");
            GameServer.home3SetNum.setText("" + GameServer.gameId.getGameIDNumber(3));
        }
        if (GameServer.gameId.getGameIDNumber(4) == 0) {
            GameServer.home4.setText("未创建");
            GameServer.home4SetNum.setText("0");
        } else {
            GameServer.home4.setText("已创建");
            GameServer.home4SetNum.setText("" + GameServer.gameId.getGameIDNumber(4));
        }
    }

    @Override
    protected List<String> doInBackground() {
        try {
            in = new BufferedReader(new InputStreamReader(toClientSocket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(toClientSocket.getOutputStream(), "UTF-8"), true);
            String clientMessage;
            messageOutput.append(toClientSocket.getRemoteSocketAddress() + "\n");
            while (true) {
                if ((clientMessage = in.readLine()) != null) {
                    messageOutput.append("ClientThread Output: " + clientMessage + "\n");
                    messageOutput.setCaretPosition(messageOutput.getText().length());
                    String str = ":";
                    String[] command = clientMessage.split(str);
                    if (command[0].compareTo("sentMessage") == 0) {
                        int homeId = Integer.parseInt(command[1]);
                        String sentMessage = "sentMessage:";
                        for (int i = 2; i < command.length; i++) {
                            sentMessage = sentMessage + command[i]+":";
                        }
                        GameServer.setGameMessage(sentMessage, homeId);
                        GameThread.setActive(true);
                    }
                    else if(command[0].compareTo("getGameId") == 0) {
                        out.println("001gaveGameId:" + clientId);
                    }else if (command[0].compareTo("clientIsClosing") == 0) {
                        int gameName = Integer.parseInt(command[1]);
                        GameServer.gameId.removeName(gameName);
                        messageOutput.append(clientMessage + '\n');
                        break;
                    } else if (command[0].compareTo("refreshMessage") == 0) {
                        StringBuilder message = new StringBuilder(1000);
                        message.append("002refreshMessage").append(":");
                        message.append(GameServer.gameId.getHomeNumber()).append(":");
                        for (int i = 1; i <= GameServer.gameId.getHomeNumber(); i++) {
                            message.append(GameServer.gameId.getGameIDNumber(i)).append(":");
                        }
                        out.println(message);
                    } else if (command[0].compareTo("buildHome") == 0) {
                        if (GameServer.gameId.getGameIDNumber(Integer.parseInt(command[2])) == 0) {
                            setGameHomeId(Integer.parseInt(command[2]));
                            out.println("003gameId:" + command[2] + ":" + GameServer.gameId.getGameID(Integer.parseInt(command[2])));
                            gameThread = new GameThread(gameHomeId);
                            gameThread.start();
                        } else {
                            out.println("003gameId:" + "0");
                        }
                    } else if (command[0].compareTo("joinHome") == 0) {
                        if (GameServer.gameId.getGameIDNumber(Integer.parseInt(command[2])) != 0 && GameServer.gameId.getGameIDNumber(Integer.parseInt(command[2])) < GameServer.gameId.homeNumber) {
                            out.println("004gameId:" + command[2] + ":" + GameServer.gameId.getGameID(Integer.parseInt(command[2])));
                        } else {
                            out.println("004gameId:" + "0");
                        }
                    } else if (command[0].compareTo("exitHome") == 0) {
                        boolean a = GameServer.gameId.removeGameID(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                        if(gameThread != null)
                            gameThread.stop();
                    } else if (command[0].compareTo("gameMessage") == 0) {
                        int homeId = Integer.parseInt(command[1]);
                        out.println("005"+GameServer.getGameMessage(homeId));
                    }
                }
                refreash();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int getGameName() {
        return clientId;
    }

    public static void setGameName(int aGameName) {
        clientId = aGameName;
    }

    public static int getGameHomeId() {
        return gameHomeId;
    }

    public static void setGameHomeId(int aGameHomeId) {
        gameHomeId = aGameHomeId;
    }
}
