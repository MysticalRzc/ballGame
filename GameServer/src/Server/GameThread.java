package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread {

    private Thread gameThread = null;
    private Ball[] ballList = new Ball[16];
    private int ballNumber;
//    private PrintWriter out;
//    private BufferedReader in;
    private int gameid;
    private UsingBag bag = new UsingBag();
    private int areaR = 30;
    private static boolean active;
    private static boolean readFlag;
    private int round;          //回合

    public GameThread(int gameid) {
//        this.out = out;
//        this.in = in;
        this.gameid = gameid;
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
            ballList[i].setXY((ballList[i].getX() + ballList[i].getSpeed() * Math.cos(ballList[i].getDirection())), (ballList[i].getY() + ballList[i].getSpeed() * Math.sin(ballList[i].getDirection())));
//            ballList[i].setY((ballList[i].getY() + ballList[i].getSpeed() * Math.sin(ballList[i].getDirection())));
            ballList[i].SpeedSlowDown();
            if (ballList[i].getSpeed() != 0) {
                active = true;
            }
        }
        if (!active) {
            round++;
        }
        if (ballList[0].getFlag()) {
            ballList[0].setX(600);
            ballList[0].setY(300);
            ballList[0].SetFlag(false);
        }
        int leftBall = 0;
        int rightBall = 0;
        for (int i = 1; i < ballNumber; i++) {
            if (ballList[i].getFlag()) {
                if (i % 2 == 1) {
                    ballList[i].setXYMandatory(102 + 31 * leftBall, 625);
                    leftBall++;
                } else {
                    ballList[i].setXYMandatory(747 + 31 * rightBall, 625);
                    rightBall++;
                }
            }
        }
    }

    public void start() {
        gameNorms();
        gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        String sentMessage = "sentMessage:" + ballNumber;
                        for (int i = 0; i < ballNumber; i++) {
                            sentMessage = sentMessage + ":" + ballList[i].toString();
                        }
                        if (readFlag) {
                            System.out.println(GameServer.getGameMessage(gameid));
                            String[] ballMessage = GameServer.getGameMessage(gameid).split(":");
                            ballNumber = Integer.parseInt(ballMessage[1]);

                            ballList[0].SetBall(ballMessage[2]);

                            for (int i = 0; i < ballNumber && i < ballMessage.length; i++) {
//                                ballList[i].SetBall(ballMessage[i + 2]);
                                System.out.println(ballMessage[i]);
                            }
                            readFlag = false;
                        }
                        if (active) {
                            GameServer.setGameMessage(sentMessage, gameid);
                        } else {
                            GameServer.setGameMessage("sentmessage:" + round, gameid);
                        }
                        if (active) {
                            active = false;
                            crash();
                            move();
                        }
                        Thread.sleep(3);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        gameThread.start();
    }

    public void gameNorms() {
        readFlag = false;
        active = true;
        round = 0;
        ballNumber = 16;
        ballList[0] = new Ball("800;270;0;" + Math.PI * 1.1 + ";null");
        int index = 1;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= i; j++) {
                if (index >= ballNumber) {
                    return;
                }
                ballList[index++] = new Ball("" + (400 - 32 * i) + ";" + (270 - (-i * 16 + j * 32)) + ";0;0;null");
            }
        }
        String sentMessage = "sentMessage:" + ballNumber;
        for (int i = 0; i < ballNumber; i++) {
            sentMessage = sentMessage + ":" + ballList[i].toString();
        }
        GameServer.setGameMessage(sentMessage, gameid);
    }

    public static void setActive(boolean active) {
        GameThread.active = active;
        GameThread.readFlag = true;
    }

    public void stop() {
        gameThread.stop();
    }

    public double ONorms(double O) {
        while (O >= 2 * Math.PI) {
            O -= 2 * Math.PI;
        }
        while (O < 0) {
            O += 2 * Math.PI;
        }
        return O;
    }
}
