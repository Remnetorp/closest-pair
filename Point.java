
public class Point{
    private double x,y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point point){
        double dx = x - point.getX();
        double dy = y - point.getY();
        return Math.hypot(dx, dy);
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
}