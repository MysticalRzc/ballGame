package Client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintWriter;
import javax.swing.*;

public class GameWindows {

    private GameClient client;
    private MyDrawPanel DPanel = null;
    private int GameHome,GameSet;
    
    public void Start(int GameHome, int GameSet) {
        this.GameHome = GameHome;
        this.GameSet = GameSet;
        JFrame GFrame = new JFrame(); // ��������
        DPanel = new MyDrawPanel(GameHome,GameSet); // ��������
        GFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GFrame.setLocation(100, 25);
        GFrame.setSize(1116, 738);
        GFrame.setVisible(true);
        //       GFrame.addWindowListener(new MyWindowsLister(client));
        GFrame.addWindowListener(new WindowsAdapter());
        GFrame.add(DPanel); //JFrame�м���DPanel
        GFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DPanel.PaintThread();
        DPanel.getGame().start();
    }

    private class WindowsAdapter implements WindowListener {

        public WindowsAdapter() {
        }

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            if (DPanel != null) {
                DPanel.getGame().stop();
            }
            GameClient.GameStop(GameHome,GameSet);
            GameClient.messageOutput("exitHome:" + GameHome + ":" + GameSet);
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
}
