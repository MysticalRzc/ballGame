package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GameClient extends javax.swing.JFrame {

    public GameClient() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRemoteName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRemotePort = new javax.swing.JTextField();
        connectServer = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageOutput = new javax.swing.JTextArea();
        Jpanel = new javax.swing.JPanel();
        state = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        home1 = new javax.swing.JLabel();
        home1Build = new javax.swing.JLabel();
        home1Connect = new javax.swing.JLabel();
        home1ConnectNumber = new javax.swing.JLabel();
        buildHome1 = new javax.swing.JButton();
        joinHome1 = new javax.swing.JButton();
        home2Build = new javax.swing.JLabel();
        home2Connect = new javax.swing.JLabel();
        home2ConnectNumber = new javax.swing.JLabel();
        joinHome2 = new javax.swing.JButton();
        home2 = new javax.swing.JLabel();
        buildHome2 = new javax.swing.JButton();
        joinHome3 = new javax.swing.JButton();
        buildHome3 = new javax.swing.JButton();
        home3Connect = new javax.swing.JLabel();
        home3Build = new javax.swing.JLabel();
        home3 = new javax.swing.JLabel();
        home3ConnectNumber = new javax.swing.JLabel();
        home4 = new javax.swing.JLabel();
        home4Build = new javax.swing.JLabel();
        home4Connect = new javax.swing.JLabel();
        home4ConnectNumber = new javax.swing.JLabel();
        buildHome4 = new javax.swing.JButton();
        joinHome4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        gameId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("连接服务器"));

        jLabel1.setText("服务器主机名：");

        txtRemoteName.setText("localhost");

        jLabel2.setText("服务器端口号：");

        txtRemotePort.setText("7");

        connectServer.setText("连接服务器");
        connectServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectServer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectServer)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("反馈信息"));

        messageOutput.setColumns(20);
        messageOutput.setRows(5);
        jScrollPane1.setViewportView(messageOutput);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        Jpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("开始游戏菜单"));

        state.setText("请连接服务器。");

        refreshButton.setText("刷新");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        home1.setText("房间1:");

        home1Build.setText("未创建");

        home1Connect.setText("连接人数：");

        home1ConnectNumber.setText("0");

        buildHome1.setText("创建房间");
        buildHome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildHome1ActionPerformed(evt);
            }
        });

        joinHome1.setText("加入游戏");
        joinHome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinHome1ActionPerformed(evt);
            }
        });

        home2Build.setText("未创建");

        home2Connect.setText("连接人数：");

        home2ConnectNumber.setText("0");

        joinHome2.setText("加入游戏");
        joinHome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinHome2ActionPerformed(evt);
            }
        });

        home2.setText("房间2:");

        buildHome2.setText("创建房间");
        buildHome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildHome2ActionPerformed(evt);
            }
        });

        joinHome3.setText("加入游戏");
        joinHome3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinHome3ActionPerformed(evt);
            }
        });

        buildHome3.setText("创建房间");
        buildHome3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildHome3ActionPerformed(evt);
            }
        });

        home3Connect.setText("连接人数：");

        home3Build.setText("未创建");

        home3.setText("房间3:");

        home3ConnectNumber.setText("0");

        home4.setText("房间4:");

        home4Build.setText("未创建");

        home4Connect.setText("连接人数：");

        home4ConnectNumber.setText("0");

        buildHome4.setText("创建房间");
        buildHome4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildHome4ActionPerformed(evt);
            }
        });

        joinHome4.setText("加入游戏");
        joinHome4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinHome4ActionPerformed(evt);
            }
        });

        jLabel3.setText("游戏id：");

        gameId.setText("null");

        javax.swing.GroupLayout JpanelLayout = new javax.swing.GroupLayout(Jpanel);
        Jpanel.setLayout(JpanelLayout);
        JpanelLayout.setHorizontalGroup(
            JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addComponent(state)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)
                        .addContainerGap())
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addComponent(home3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home3Build)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home3Connect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home3ConnectNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(64, 64, 64)
                        .addComponent(buildHome3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(joinHome3))
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpanelLayout.createSequentialGroup()
                                .addComponent(home2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(home2Build)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(home2Connect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(home2ConnectNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpanelLayout.createSequentialGroup()
                                .addComponent(home1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(home1Build)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(home1Connect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(home1ConnectNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelLayout.createSequentialGroup()
                                .addComponent(buildHome1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(joinHome1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelLayout.createSequentialGroup()
                                .addComponent(buildHome2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(joinHome2))))
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addComponent(home4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home4Build)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home4Connect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home4ConnectNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(64, 64, 64)
                        .addComponent(buildHome4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(joinHome4))
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gameId, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        JpanelLayout.setVerticalGroup(
            JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(state)
                    .addComponent(refreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(gameId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(joinHome1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(home1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(home1Build)
                        .addComponent(home1Connect)
                        .addComponent(home1ConnectNumber)
                        .addComponent(buildHome1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(home2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(home2Build)
                        .addComponent(home2Connect)
                        .addComponent(home2ConnectNumber))
                    .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(joinHome2)
                        .addComponent(buildHome2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(home3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(home3Build)
                        .addComponent(home3Connect)
                        .addComponent(home3ConnectNumber))
                    .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(joinHome3)
                        .addComponent(buildHome3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(home4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(home4Build)
                        .addComponent(home4Connect)
                        .addComponent(home4ConnectNumber))
                    .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(joinHome4)
                        .addComponent(buildHome4))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Jpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectServerActionPerformed
        try {
            String remoteName = txtRemoteName.getText();
            int remotePort = Integer.parseInt(txtRemotePort.getText());
            //构建套接字格式地址。
            SocketAddress remoteAddr = new InetSocketAddress(remoteName, remotePort);
            //1.创建套接字clientSocket(Socket)并链接到远程服务器。
            clientSocket = new Socket();
            clientSocket.connect(remoteAddr);
            connectServer.setEnabled(false);
            state.setText("在线^0^");
            messageCache = new MessageCache(new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8")));
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
            setGameId();
            refreshButton();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "连接错误", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_connectServerActionPerformed
    public void setGameId() {
        if (state.getText().compareTo("在线^0^") == 0) {
            out.println("getGameId");
            String[] command = getMessageCache().getMessage(1).split(":");
            gameId.setText(command[1]);
            clientId = Integer.parseInt(command[1]);
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (out != null) {
            out.println("clientIsClosing:" + clientName);
        }
    }//GEN-LAST:event_formWindowClosing

    private void refreshButton() {
        if (state.getText().compareTo("在线^0^") == 0) {
            out.println("refreshMessage:" + clientName);
            String[] command = getMessageCache().getMessage(2).split(":");
            int homeNumber = Integer.parseInt(command[2]);
            if (homeNumber == 0) {
                home1Build.setText("未创建");
                home1ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome1.setEnabled(false);
                buildHome1.setEnabled(true);
            } else {
                home1Build.setText("已创建");
                home1ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome1.setEnabled(true);
                buildHome1.setEnabled(false);
            }
            homeNumber = Integer.parseInt(command[3]);
            if (homeNumber == 0) {
                home2Build.setText("未创建");
                home2ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome2.setEnabled(false);
                buildHome2.setEnabled(true);
            } else {
                home2Build.setText("已创建");
                home2ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome2.setEnabled(true);
                buildHome2.setEnabled(false);
            }
            homeNumber = Integer.parseInt(command[4]);
            if (homeNumber == 0) {
                home3Build.setText("未创建");
                home3ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome3.setEnabled(false);
                buildHome3.setEnabled(true);
            } else {
                home3Build.setText("已创建");
                home3ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome3.setEnabled(true);
                buildHome3.setEnabled(false);
            }
            homeNumber = Integer.parseInt(command[5]);
            if (homeNumber == 0) {
                home4Build.setText("未创建");
                home4ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome4.setEnabled(false);
                buildHome4.setEnabled(true);
            } else {
                home4Build.setText("已创建");
                home4ConnectNumber.setText(Integer.toString(homeNumber));
                joinHome4.setEnabled(true);
                buildHome4.setEnabled(false);
            }
        }
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refreshButton();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void buttonClose() {
        java.awt.event.ActionEvent unUse = null;
        this.refreshButtonActionPerformed(unUse);
        buildHome1.setEnabled(false);
        buildHome2.setEnabled(false);
        buildHome3.setEnabled(false);
        buildHome4.setEnabled(false);
        joinHome1.setEnabled(false);
        joinHome2.setEnabled(false);
        joinHome3.setEnabled(false);
        joinHome4.setEnabled(false);
        refreshButton.setEnabled(false);
    }

    private boolean buildHome(int homeIndex) {
        if (state.getText().compareTo("在线^0^") == 0) {
            out.println("buildHome:" + clientName + ":" + homeIndex);
            String[] command = getMessageCache().getMessage(3).split(":");
            if (command[1].compareTo("0") != 0) {
//                clientGameHome = Integer.parseInt(command[1]);
//                clientGameHomeSet = Integer.parseInt(command[2]);
                gameWindows = new GameWindows();
                gameWindows.Start(homeIndex, Integer.parseInt(command[2]));
                return true;
            }
            return false;
        }
        return false;
    }
    private void buildHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildHome1ActionPerformed
        buildHome(1);
    }//GEN-LAST:event_buildHome1ActionPerformed

    private void buildHome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildHome2ActionPerformed
        buildHome(2);
    }//GEN-LAST:event_buildHome2ActionPerformed

    private void buildHome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildHome3ActionPerformed
        buildHome(3);
    }//GEN-LAST:event_buildHome3ActionPerformed

    private void buildHome4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildHome4ActionPerformed
        buildHome(4);
    }//GEN-LAST:event_buildHome4ActionPerformed
    private boolean joinHome(int homeIndex) {
        if (state.getText().compareTo("在线^0^") == 0) {
            out.println("joinHome:" + clientName + ":" + homeIndex);
            String[] command = getMessageCache().getMessage(4).split(":");
            if (command[1].compareTo("0") != 0) {
//                clientGameHome = Integer.parseInt(command[1]);
//                clientGameHomeSet = Integer.parseInt(command[2]);
                gameWindows = new GameWindows();
                gameWindows.Start(homeIndex, Integer.parseInt(command[2]));
                return true;
            }
            return false;
        }
        return false;
    }

    private void joinHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinHome1ActionPerformed
        joinHome(1);
    }//GEN-LAST:event_joinHome1ActionPerformed

    private void joinHome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinHome2ActionPerformed
        joinHome(2);
    }//GEN-LAST:event_joinHome2ActionPerformed

    private void joinHome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinHome3ActionPerformed
        joinHome(3);
    }//GEN-LAST:event_joinHome3ActionPerformed

    private void joinHome4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinHome4ActionPerformed
        joinHome(4);
    }//GEN-LAST:event_joinHome4ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //   GameThread.start();
    }//GEN-LAST:event_formWindowOpened
//    public void GameWindowsStop() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                boolean flag = false;
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                        if (windowsOpen == false) {
//                            if (flag != windowsOpen) {
//                                gameWindows.Stop();
//                                out.println("exitHome:" + clientName + ":" + clientGameHome + ":" + clientGameHomeSet);
//                                flag = false;
//                                java.awt.event.ActionEvent unUse = null;
//                                client.refreshButtonActionPerformed(unUse);
//                                refreshButton.setEnabled(true);
//                            }
//                        } else if (flag == false) {
//                            flag = true;
//                        }
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    try {
//                        Thread.sleep(1000);
//                        if (windowsOpen == false) {
//                            if (flag != windowsOpen) {
//                                gameWindows.Stop();
//                                out.println("exitHome:" + clientName + ":" + clientGameHome + ":" + clientGameHomeSet);
//                                flag = false;
//                                java.awt.event.ActionEvent unUse = null;
//                                client.refreshButtonActionPerformed(unUse);
//                                refreshButton.setEnabled(true);
//                            }
//                        } else if (flag == false) {
//                            flag = true;
//                        }
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }).start();
//    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameClient().setVisible(true);
//                windowsOpen = true;
//                client.GameWindowsStop();
            }
        });
    }

//==================================================================================
    private GameWindows gameWindows = null;
    private String clientName;
    private int clientId;
    private static boolean windowsOpen = false;
    private Socket clientSocket = null;
//    private BufferedReader in; //网络输入流
    private static PrintWriter out; //网络输出流
    private static MessageCache messageCache;

    public static void GameStop(int GameHome, int GameSet) {
    }

    public static void messageOutput(String message) {
        out.println(message);
    }

    public static MessageCache getMessageCache() {
        return messageCache;
    }

    public static void setMessageCache(MessageCache aMessageCache) {
        messageCache = aMessageCache;
    }

    public static JTextArea getMessageOutput() {
        return messageOutput;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
//    public boolean getWindowsOpen() {
//        return client.windowsOpen;
//    }
//
//    public void setWindowsOpen(boolean windowsOpen) {
//        client.windowsOpen = windowsOpen;
//    }
//    public static GameClient client = new GameClient();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel;
    private javax.swing.JButton buildHome1;
    private javax.swing.JButton buildHome2;
    private javax.swing.JButton buildHome3;
    private javax.swing.JButton buildHome4;
    private javax.swing.JButton connectServer;
    private javax.swing.JLabel gameId;
    private javax.swing.JLabel home1;
    private javax.swing.JLabel home1Build;
    private javax.swing.JLabel home1Connect;
    private javax.swing.JLabel home1ConnectNumber;
    private javax.swing.JLabel home2;
    private javax.swing.JLabel home2Build;
    private javax.swing.JLabel home2Connect;
    private javax.swing.JLabel home2ConnectNumber;
    private javax.swing.JLabel home3;
    private javax.swing.JLabel home3Build;
    private javax.swing.JLabel home3Connect;
    private javax.swing.JLabel home3ConnectNumber;
    private javax.swing.JLabel home4;
    private javax.swing.JLabel home4Build;
    private javax.swing.JLabel home4Connect;
    private javax.swing.JLabel home4ConnectNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton joinHome1;
    private javax.swing.JButton joinHome2;
    private javax.swing.JButton joinHome3;
    private javax.swing.JButton joinHome4;
    private static javax.swing.JTextArea messageOutput;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel state;
    private javax.swing.JTextField txtRemoteName;
    private javax.swing.JTextField txtRemotePort;
    // End of variables declaration//GEN-END:variables
}
