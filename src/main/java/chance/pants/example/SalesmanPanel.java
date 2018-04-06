package chance.pants.example;

import chance.pants.api.domain.Stop;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SalesmanPanel extends JPanel {

    List<Stop> stops;
    double xScale;
    double yScale;

    public SalesmanPanel(List<Stop> stops) {
        super();
        this.stops = stops;

        double maxY = 0;
        double maxX = 0;
        for (Stop stop : stops) {
            if (stop.getX() > maxX) maxX = stop.getX();
            if (stop.getY() > maxY) maxY = stop.getY();
        }

        xScale = 350 / maxX;
        yScale = 250.0 / maxY;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < stops.size() - 1; i++) {
            Stop from = stops.get(i);
            Stop to = stops.get(i + 1);
            drawFromToLine(g, from, to);
        }
        Stop from = stops.get(stops.size() - 1);
        Stop to = stops.get(0);
        drawFromToLine(g, from, to);
    }

    private void drawFromToLine(Graphics g, Stop from, Stop to) {
        int fromX = (int) (from.getX() * xScale);
        int fromY = (int) (from.getY() * yScale);
        int toX = (int) (to.getX() * xScale);
        int toY = (int) (to.getY() * yScale);
        g.drawLine(fromX, fromY, toX, toY);
    }

}
