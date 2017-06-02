package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyDrawPanel extends JPanel {

    private Thread game = null;
    private GameClient client;
    private int ballNumber;
    private Ball[] ballList = new Ball[16];
    private Graphics[] ballPaint = new Graphics[16];
    private int Home;
    private int Set;
    private int angle = 0;
    private int type = 5;
    private double press;
    private double release;
    private int sleepCount = 0;
    private double lengthHold;
    private Image rod = null;
    private UsingBag bag = new UsingBag();
    private double areaR = 30;

    public Thread getGame() {
        return game;
    }

    public MyDrawPanel(int Home, int Set) {
        JLabel nickJLabel = new JLabel();
        nickJLabel.setBounds(200, 10, 200, 40);
        nickJLabel.setText("昵称：null");
        nickJLabel.setFont(new Font("楷体", Font.PLAIN, 30));
        nickJLabel.setForeground(Color.blue);
        JLabel homeJLabel = new JLabel();
        homeJLabel.setBounds(400, 10, 200, 40);
        homeJLabel.setText("房间号：" + Home);
        homeJLabel.setFont(new Font("楷体", Font.PLAIN, 30));
        homeJLabel.setForeground(Color.blue);
        JLabel setJLabel = new JLabel();
        setJLabel.setBounds(600, 10, 200, 40);
        setJLabel.setText("座号：" + Set);
        setJLabel.setFont(new Font("楷体", Font.PLAIN, 30));
        setJLabel.setForeground(Color.blue);
        this.add(nickJLabel);
        this.add(homeJLabel);
        this.add(setJLabel);
        this.Home = Home;
        this.Set = Set;
    }

    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            Image table = ImageIO.read(new File("images\\table.bmp"));
            g.drawImage(table, 0, 0, 1100, 700, null);
            g.setColor(Color.white);
            g.fillOval((int) ballList[0].getX(), (int) ballList[0].getY(), 30, 30);
            for (int i = 1; i < ballNumber; i++) {
                if (i % 2 == 0) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.blue);
                }
                g.fillOval((int) ballList[i].getX(), (int) ballList[i].getY(), 30, 30);
            }
            MouseListener();
            double length;
            switch (type) {
                //1代表移动鼠标，选择击球方向
                //2代表按下鼠标左键，蓄力击球
                //3代表松开鼠标左键，正在击球，不再显示球杆，等台球击飞之后，出现type置4
                //4代表球体都在碰撞，当球都静止时，置1

                case 1:
                    angle = MouseAngle();
                    int beginX,
                     beginY,
                     endX,
                     endY;
                    beginX = (int) (ballList[0].getX() + 15 - 15 * Math.cos(angle / 180.0 * Math.PI));
                    beginY = (int) (ballList[0].getY() + 15 - 15 * Math.sin(angle / 180.0 * Math.PI));
                    endX = (int) (beginX - 15 * Math.cos(angle / 180.0 * Math.PI));
                    endY = (int) (beginY - 15 * Math.sin(angle / 180.0 * Math.PI));
                    int dotted = 0;         //虚线
                    g.setColor(Color.black);
                    while (endX > 76 && endY > 150 && endX < 1030 && endY < 560) {
                        beginX = (int) (ballList[0].getX() + 15 - (15 + dotted) * Math.cos(angle / 180.0 * Math.PI));
                        beginY = (int) (ballList[0].getY() + 15 - (15 + dotted) * Math.sin(angle / 180.0 * Math.PI));
                        endX = (int) (beginX - 15 * Math.cos(angle / 180.0 * Math.PI));
                        endY = (int) (beginY - 15 * Math.sin(angle / 180.0 * Math.PI));
                        g.drawLine(beginX, beginY, endX, endY);
                        dotted += 30;
                    }
                    g.drawImage(rotateImage((BufferedImage) rod, angle), (int) (ballList[0].getX() + 15 + 15 * Math.cos(angle / 180.0 * Math.PI) - 487), (int) (ballList[0].getY() + 15 + 15 * Math.sin(angle / 180.0 * Math.PI) - 495), null);
                    break;
                case 2:
                    length = LengthManage() - press;
                    if (length > 200) {
                        length = 200;
                    }
                    g.drawImage(rotateImage((BufferedImage) rod, angle), (int) (ballList[0].getX() + 15 + (15 + length) * Math.cos(angle / 180.0 * Math.PI) - 487), (int) (ballList[0].getY() + 15 + (15 + length) * Math.sin(angle / 180.0 * Math.PI) - 495), null);
                    break;
                case 3:
                    length = release - press;
                    if (length > 200) {
                        length = 200;
                        release = press + 199;
                    }
                    if (length <= 0) {
                        length = 0;
                        type = 4;
                        ballList[0].setSpeed(lengthHold * 0.1);
                        ballList[0].setDirection(ONorms(angle + 180) / 180.0 * Math.PI);
                    }
                    g.drawImage(rotateImage((BufferedImage) rod, angle), (int) (ballList[0].getX() + 15 + (15 + length) * Math.cos(angle / 180.0 * Math.PI) - 487), (int) (ballList[0].getY() + 15 + (15 + length) * Math.sin(angle / 180.0 * Math.PI) - 495), null);
                    if (release >= press) {
                        release -= lengthHold;
                    }
                    sleepCount++;
                    break;
                case 4:
                    String sentMessage = "" + ballNumber;
                    for (int i = 0; i < ballNumber; i++) {
                        sentMessage = sentMessage + ":" + ballList[i].toString();
                    }
                    GameClient.messageOutput("sentMessage:" + Home + ":" + sentMessage);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(MyDrawPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PaintThread() {
        ballNumber = 16;
        for (int i = 0; i < 16; i++) {
            ballList[i] = new Ball();
        }
        ballList[0] = new Ball("800;270;0;" + Math.PI * 1.1 + ";null");
        int index = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++) {
                ballList[index++] = new Ball("" + (400 - 32 * i) + ";" + (270 - (-i * 16 + j * 32)) + ";0;0;null");
            }
        }
        try {
            rod = ImageIO.read(new File("images\\rod.png"));
        } catch (IOException ex) {
            Logger.getLogger(MyDrawPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        game = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    try {
                        if (true) {
                            GameClient.messageOutput("gameMessage:" + Home);
                            String[] ballMessage = GameClient.getMessageCache().getMessage(5).split(":");
                            System.out.println(type);
                            if (ballMessage.length > 2) {
                                type = 5;
                                ballNumber = Integer.parseInt(ballMessage[1]);
                                for (int i = 0; i < ballNumber && i < ballMessage.length; i++) {
                                    ballList[i].SetBall(ballMessage[i + 2]);
                                }
                            } else if (ballMessage.length == 2 && (Integer.parseInt(ballMessage[1]) + 1) % 2 == Set - 1) {
                                try {
                                    if (type == 5) {
                                        type = 1;
                                    }
                                } catch (Exception ex) {
                                }
                            }
                            count = 1;
                        } else {
                            crash();
                            move();
                            count++;
                        }
                        repaint();
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyDrawPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void MouseListener() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
//                System.out.println("<<<<<<<");
                if (type > 2) {
                    return;
                }
                if (type == 1) {
                    Point pressMousePoint = MouseInfo.getPointerInfo().getLocation();
                    press = Math.sqrt(Math.pow((pressMousePoint.x - ballList[0].getX() - 15), 2) + Math.pow((pressMousePoint.y - ballList[0].getY() - 15), 2));;
                }
                type = 2;
            }

            public void mouseReleased(MouseEvent e) {
//                System.out.println(">>>>>>>>");
                if (type > 3) {
                    return;
                }
                if (type == 2) {
                    Point releaseMousePoint = MouseInfo.getPointerInfo().getLocation();
                    release = Math.sqrt(Math.pow((releaseMousePoint.x - ballList[0].getX() - 15), 2) + Math.pow((releaseMousePoint.y - ballList[0].getY() - 15), 2));;
                    lengthHold = (release - press) / 8;
                }
                sleepCount = 0;
                type = 3;
            }
        });
    }

    private int MouseAngle() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        point.x -= this.getLocationOnScreen().x;
        point.y -= this.getLocationOnScreen().y;
        int angle = 0;
        angle = (int) (Math.atan((point.y - ballList[0].getY() - 15) / (point.x - ballList[0].getX() - 15)) / Math.PI * 180);
        if (point.x - ballList[0].getX() - 15 <= 0) {
            angle -= 180;
        }
        angle = (int) ONorms(angle);
        return angle;
    }

    private double LengthManage() {
        Point nowMousePoint = MouseInfo.getPointerInfo().getLocation();
        double distance = Math.sqrt(Math.pow((nowMousePoint.x - ballList[0].getX() - 15), 2) + Math.pow((nowMousePoint.y - ballList[0].getY() - 15), 2));
        return distance;
    }

    private BufferedImage rotateImage(BufferedImage bufferedimage, int degree) {
        //图片旋转
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                        RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), h / 2, w / 2 + 7);        //旋转原点
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

    private double ONorms(double O) {
        while (O >= 360) {
            O -= 360;
        }
        while (O < 0) {
            O += 360;
        }
        return O;
    }

    public void crash() {
        for (int i = 0; i < ballNumber; i++) {
            for (int j = i + 1; j < ballNumber; j++) {
                double distance = Math.sqrt(Math.pow(ballList[i].getX() - ballList[j].getX(), 2) + Math.pow(ballList[i].getY() - ballList[j].getY(), 2));
                if (distance < areaR) {
                    double remember;
                    double x1 = ballList[i].getX();
                    double y1 = ballList[i].getY();
                    double x2 = ballList[j].getX();
                    double y2 = ballList[j].getY();
                    double O1 = ballList[i].getDirection();
                    double Opi1 = ballList[i].getDirection_PI();
                    double O2 = ballList[j].getDirection();
                    double Opi2 = ballList[j].getDirection_PI();
                    double v1 = ballList[i].getSpeed();
                    double v2 = ballList[j].getSpeed();
                    double v12, v22, O12, O22;
                    double O3;
                    double v1_vertical, v1_parallel, v2_vertical, v2_parallel;      //vertical 垂直方向
                    double O1_vertical, O1_parallel, O2_vertical, O2_parallel;      //parallel 水平方向

                    if (x2 - x1 == 0) {
                        O3 = 0;
                    } else if ((remember = (y2 - y1) / (x2 - x1)) > 0) {
                        O3 = Math.atan(remember);
                    } else {
                        O3 = Math.PI - Math.abs(Math.atan(-remember));
                    }
                    if (Opi1 < O3) {
                        v1_vertical = Math.abs(v1 * Math.sin(O3 - O1));
                        v1_parallel = Math.abs(v1 * Math.cos(O3 - O1));
                        O1_vertical = O1 - Math.PI / 2 + O3 - Opi1;
                        if (bag.angleSubtractionHalfPI(O3, O1)) //                            O1_parallel = O1 + O3 - Opi1;
                        {
                            O1_parallel = O3;
                        } else //O1_parallel = O1 - O3 + Opi1;
                        {
                            O1_parallel = O3 + Math.PI;
                        }
                    } else {
                        v1_vertical = Math.abs(v1 * Math.sin(O1 - O3));
                        v1_parallel = Math.abs(v1 * Math.cos(O1 - O3));
                        O1_vertical = O1 + Math.PI / 2 + O3 - Opi1;
                        if (bag.angleSubtractionHalfPI(O3, O1)) //                            O1_parallel = O1 + O3 - Opi1;
                        {
                            O1_parallel = O3;
                        } else //O1_parallel = O1 - O3 + Opi1;
                        {
                            O1_parallel = O3 + Math.PI;
                        }
                    }
                    if (Opi2 < O3) {
                        v2_vertical = Math.abs(v2 * Math.sin(O3 - O2));
                        v2_parallel = Math.abs(v2 * Math.cos(O3 - O2));
                        O2_vertical = O2 - Math.PI / 2 + O3 - Opi2;
                        if (bag.angleSubtractionHalfPI(O3, O2)) //                            O1_parallel = O1 + O3 - Opi1;
                        {
                            O2_parallel = O3;
                        } else //O1_parallel = O1 - O3 + Opi1;
                        {
                            O2_parallel = O3 + Math.PI;
                        }
                    } else {
                        v2_vertical = Math.abs(v2 * Math.sin(O2 - O3));
                        v2_parallel = Math.abs(v2 * Math.cos(O2 - O3));
                        O2_vertical = O2 + Math.PI / 2 + O3 - Opi2;
                        if (bag.angleSubtractionHalfPI(O3, O2)) //                            O1_parallel = O1 + O3 - Opi1;
                        {
                            O2_parallel = O3;
                        } else //O1_parallel = O1 - O3 + Opi1;
                        {
                            O2_parallel = O3 + Math.PI;
                        }
                    }
                    O1_vertical = ONorms(O1_vertical);
                    O1_parallel = ONorms(O1_parallel);
                    O2_vertical = ONorms(O2_vertical);
                    O2_parallel = ONorms(O2_parallel);

                    v12 = Math.sqrt(v1_vertical * v1_vertical + v2_parallel * v2_parallel);

//                    if (v2 * v2 * Math.cos(O2 - O3) == 0 || v1_vertical==0) {
//                        O12 = O1_vertical;
                    if (v1_vertical == 0 || v2_parallel == 0) {
                        if (v1_vertical == 0) {
                            O12 = O2_parallel;
                        } else {
                            O12 = O1_vertical;
                        }
                    } else if (O2_parallel > Math.PI * 1.5 && O1_vertical <= Math.PI * 0.5) {
                        O12 = O2_parallel + Math.abs(Math.atan(v2_parallel / v1_vertical));
                    } else if (O2_parallel <= Math.PI * 0.5 && O1_vertical > Math.PI * 1.5) {
                        O12 = O1_vertical + Math.abs(Math.atan(v2_parallel / v1_vertical));
                    } else if (O2_parallel > O1_vertical) {
                        O12 = O1_vertical + Math.abs(Math.atan(v2_parallel / v1_vertical));
                    } else {
                        O12 = O1_vertical - Math.abs(Math.atan(v2_parallel / v1_vertical));
                    }

                    v22 = Math.sqrt(v2_vertical * v2_vertical + v1_parallel * v1_parallel);

//                    if (v1 * v1 * Math.cos(O1 - O3) == 0 || v2_vertical==0) {
//                        O22 = O2_vertical;
                    if (v2_vertical == 0 || v1_parallel == 0) {
                        if (v2_vertical == 0) {
                            O22 = O1_parallel;
                        } else {
                            O22 = O2_vertical;
                        }
                    } else if (O1_parallel > Math.PI * 1.5 && O2_vertical <= Math.PI * 0.5) {
                        O22 = O1_parallel + Math.abs(Math.atan(v1_parallel / v2_vertical));
                    } else if (O2_vertical > Math.PI * 1.5 && O1_parallel <= Math.PI * 0.5) {
                        O22 = O2_vertical + Math.abs(Math.atan(v1_parallel / v2_vertical));
                    } else if (O1_parallel > O2_vertical) {
                        O22 = O2_vertical + Math.abs(Math.atan(v1_parallel / v2_vertical));
                    } else {
                        O22 = O2_vertical - Math.abs(Math.atan(v1_parallel / v2_vertical));
                    }

                    if (distance < areaR) {                    //防止小球粘连
                        distance = areaR + 0.5 - distance;
                        double x = Math.abs((distance / 2) * Math.cos(O3));
                        double y = Math.abs((distance / 2) * Math.sin(O3));
                        int a, b;
                        if (ballList[i].getX() > ballList[j].getX()) {
                            a = i;
                            b = j;
                        } else {
                            a = j;
                            b = i;
                        }
                        ballList[a].setX(ballList[a].getX() + x);
                        ballList[b].setX(ballList[b].getX() - x);
                        if (ballList[i].getY() > ballList[j].getY()) {
                            a = i;
                            b = j;
                        } else {
                            a = j;
                            b = i;
                        }
                        ballList[a].setY(ballList[a].getY() + y);
                        ballList[b].setY(ballList[b].getY() - y);
                    }
//                    ballList[i].setX((ballList[i].getX() - 1 * ballList[i].getSpeed() * Math.cos(ballList[i].getDirection())));
//                    ballList[i].setY((ballList[i].getY() - 1 * ballList[i].getSpeed() * Math.sin(ballList[i].getDirection())));
//                    ballList[j].setX((ballList[j].getX() - 1 * ballList[j].getSpeed() * Math.cos(ballList[j].getDirection())));
//                    ballList[j].setY((ballList[j].getY() - 1 * ballList[j].getSpeed() * Math.sin(ballList[j].getDirection())));
//                    System.out.println(O1_vertical + " " + O1_parallel + " " + O2_vertical + " " + O2_parallel);
//                    System.out.println(v1_vertical + " " + v1_parallel + " " + v2_vertical + " " + v2_parallel);
                    ballList[i].setSpeed(v12);
                    ballList[i].setDirection(O12);
                    ballList[j].setSpeed(v22);
                    ballList[j].setDirection(O22);

                    //为方便调试，进行小球信息输出
//                    bag.ballInformation(ballList[i], 1);
//                    System.out.println("碰撞小球编号 first:" + i + " second:" + j);
//                    System.out.println("两小球碰撞角：" + bag.angle(O3) + O3);
//                    System.out.println("原第一个球的角度：" + bag.angle(O1) + "速度：" + v1 + "  原第二个球的角度：" + bag.angle(O2) + "速度：" + v2);
//                    System.out.println("第一个球的角度：" + bag.angle(O12) + "速度：" + v12 + "  第二个球的角度：" + bag.angle(O22) + "速度：" + v22);
//                    System.out.println("第一个球的水平角度" + bag.angle(O1_parallel) + O1_parallel + "速度：" + v1_parallel);
//                    System.out.println("第一个球的垂直角度" + bag.angle(O1_vertical) + O1_vertical + "速度：" + v1_vertical);
//                    System.out.println("第二个球的水平角度" + bag.angle(O2_parallel) + O2_parallel + "速度：" + v2_parallel);
//                    System.out.println("第二个球的垂直角度" + bag.angle(O2_vertical) + O2_vertical + "速度：" + v2_vertical);
//
//                    System.out.println("\n\n");
                    //          System.out.println(Math.sqrt(Math.pow(ballList[i].getX()-ballList[j].getX(), 2)+Math.pow(ballList[i].getX()-ballList[j].getX(), 2)));
                    //          System.out.println(O3);
                }
            }
        }
    }

    public void move() {
        for (int i = 0; i < ballNumber; i++) {
            ballList[i].setX((ballList[i].getX() + ballList[i].getSpeed() * Math.cos(ballList[i].getDirection())));
            ballList[i].setY((ballList[i].getY() + ballList[i].getSpeed() * Math.sin(ballList[i].getDirection())));
            ballList[i].SpeedSlowDown();
        }
    }
}
