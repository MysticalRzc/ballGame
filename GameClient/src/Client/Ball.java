package Client;

/**
 *
 * @author lenovo
 */
public class Ball {
    private int sizeX = 1030;
    private int sizeY = 560;
    private int topMargin = 150;
    private int leftMargin = 73;
    private int ballSize = 30;
    private double x;
    private double y;
    private double speed;
    private int id;
    private double direction;
    private String attribute;
    public Ball(){}

    public Ball(String message)
    {
        String[] str = message.split(";");
        x = Integer.parseInt(str[0]);
        y = Integer.parseInt(str[1]);
        speed = Double.parseDouble(str[2]);
        direction = Double.parseDouble(str[3]);
        attribute = str[4];
        
    }
    
    public void SetBall(String message)
    {
        String[] str = message.split(";");
        x = Integer.parseInt(str[0]);
        y = Integer.parseInt(str[1]);
        speed = Double.parseDouble(str[2]);
        direction = Double.parseDouble(str[3]);
        attribute = str[4];
    }
    @Override
    public String toString() {
        return (int)x + ";" + (int)y + ";" + speed + ";" + direction + ";" + attribute;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if(x+ballSize>sizeX)
        {
            x += sizeX -x-ballSize;
            if(direction <Math.PI)
                direction = Math.PI-direction;
            else
                direction = 3*Math.PI-direction;
        }
        if(x<leftMargin)
        {
            x = leftMargin*2-x;
            if(direction <Math.PI)
                direction = Math.PI-direction;
            else
                direction = 3*Math.PI-direction;
        }
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        while(direction>2*Math.PI)
            direction-=2*Math.PI;
        while(direction<0)
            direction+=2*Math.PI;
        this.direction = direction;
    }
    
    public double getDirection_PI(){
        double dir = direction;
        while(dir>Math.PI)
            dir-=Math.PI;
        return dir;
    }
    
    public void setY(double y) {
        if(y+ballSize>sizeY)
        {
            y += sizeY -y-ballSize;
//            if(direction <Math.PI)
                direction = 2*Math.PI-direction;
//            else
//                direction = 3*Math.PI-direction;
        }
        if(y<topMargin)
        {
            y = topMargin*2-y;
        //    if(direction <Math.PI/2 || direction >3*Math.PI/2)
                direction = 2*Math.PI-direction;
//            else
//                direction = 3*Math.PI-direction;
        }
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void SpeedSlowDown()
    {
        if(speed>0)
            this.speed-=(0.003+0.001*speed*speed);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }    
}