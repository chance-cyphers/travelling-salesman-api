package chance.pants.api.controllers;

public class Stop {

    private long id;
    private String name;
    private double x;
    private double y;

    public Stop() {}

    public Stop(long id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setId(long id) {
        this.id = id;
    }

}
