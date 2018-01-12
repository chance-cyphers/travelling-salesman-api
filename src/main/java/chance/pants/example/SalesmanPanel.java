package chance.pants.example;

import javax.swing.*;
import java.awt.*;

public class SalesmanPanel extends JPanel {

    TourFromExample tour;

    public SalesmanPanel(TourFromExample tour) {
        super();
        this.tour = tour;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0 ; i < tour.getTour().size() - 1 ; i++) {
            City from = tour.getTour().get(i);
            City to = tour.getTour().get(i+1);
            g.drawLine(from.x, from.y, to.x, to.y);
        }
    }

}
