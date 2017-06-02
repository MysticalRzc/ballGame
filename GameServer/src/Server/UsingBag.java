package Server;

public class UsingBag {

    public String angle(double angle) {
        String ang = (int) (angle / Math.PI * 180) + " ";
        if (angle >= 0 && angle <= Math.PI / 2) {
            return ang + "右下  ";
        }
        if (angle > Math.PI / 2 && angle <= Math.PI) {
            return ang + "左下  ";
        }
        if (angle > Math.PI && angle <= Math.PI / 2 * 3) {
            return ang + "左上  ";
        }
        if (angle > Math.PI / 2 * 3 && angle <= Math.PI * 2) {
            return ang + "右上  ";
        }
        return "error  ";
    }

    public void ballInformation(Ball ball, int index) {
        System.out.println("=================第" + index + "个小球的信息==================");
        System.out.println("坐标：(" + ball.getX() + "," + ball.getY() + ")");
        System.out.println("速度" + ball.getSpeed() + "\t方向" + angle(ball.getDirection()) + ball.getDirection());
    }

    public boolean angleSubtractionHalfPI(double firstAngle, double secondAngle) {
        double subtraction;
        if (firstAngle > secondAngle) {
            subtraction = firstAngle - secondAngle;
        } else {
            subtraction = secondAngle - firstAngle;
        }
        if (subtraction > Math.PI) {
            return Math.PI * 2 - subtraction < Math.PI / 2;
        } else {
            return subtraction < Math.PI / 2;
        }
    }
    public void ballNorm(Ball[] ballList, int num) {
        int index = 1;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < i; j++) {
                if (index >= num) {
                    return;
                }
                ballList[index] = new Ball();
                ballList[index++].SetBall("" + 500 + 31 * i + ";" + 300 + (-i * 25 + j * 31) + ";0;0;null");
            }
        }
    }
}
