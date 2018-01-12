package chance.pants.example;

import chance.pants.algorithm.simulatedannealing.Stop;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SalesmanPanel extends JPanel {

    List<Stop> stops;

    public SalesmanPanel(List<Stop> stops) {
        super();
        this.stops = stops;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0 ; i < stops.size() - 1 ; i++) {
            Stop from = stops.get(i);
            Stop to = stops.get(i+1);
            g.drawLine((int)from.getX(), (int)from.getY(), (int)to.getX(), (int)to.getY());
        }
    }

}
