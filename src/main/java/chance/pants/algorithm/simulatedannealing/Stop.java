package chance.pants.algorithm.simulatedannealing;

public class Stop {

    private double x;
    private double y;

    public Stop(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double distanceTo(Stop stop){
        double xDistance = Math.abs(getX() - stop.getX());
        double yDistance = Math.abs(getY() - stop.getY());
        return Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
    }

    @Override
    public String toString(){
        return getX()+", "+getY();
    }

}
