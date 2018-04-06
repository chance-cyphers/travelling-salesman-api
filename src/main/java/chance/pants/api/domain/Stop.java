package chance.pants.api.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Stop {

    private long id;
    private String name;
    private double x;
    private double y;

    public Stop(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public long getId() { return this.id; }
    public String getName() { return this.name; }
    public double getX() { return this.x; }
    public double getY() { return this.y; }

    public void setId(long id) { this.id = id; }

    public double distanceTo(Stop stop){
        double xDistance = Math.abs(getX() - stop.getX());
        double yDistance = Math.abs(getY() - stop.getY());
        return Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
    }

}
