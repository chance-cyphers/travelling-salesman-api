import chance.pants.api.algorithm.SimulatedAnnealing;
import chance.pants.api.models.Stop;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

public class SimulatedAnnealingTest {

    @Test
    public void solve_findsADecentSolution() {
        ArrayList<Stop> stops = new ArrayList<>();
        stops.add(new Stop("", 60, 200));
        stops.add(new Stop("", 180, 200));
        stops.add(new Stop("", 80, 180));
        stops.add(new Stop("", 140, 180));
        stops.add(new Stop("", 20, 160));
        stops.add(new Stop("", 100, 160));
        stops.add(new Stop("", 200, 160));
        stops.add(new Stop("", 140, 140));
        stops.add(new Stop("", 40, 120));
        stops.add(new Stop("", 100, 120));
        stops.add(new Stop("", 180, 100));
        stops.add(new Stop("", 60, 80));
        stops.add(new Stop("", 120, 80));
        stops.add(new Stop("", 180, 60));
        stops.add(new Stop("", 20, 40));
        stops.add(new Stop("", 100, 40));
        stops.add(new Stop("", 200, 40));
        stops.add(new Stop("", 20, 20));
        stops.add(new Stop("", 60, 20));
        stops.add(new Stop("", 160, 20));
        Collections.shuffle(stops);

        SimulatedAnnealing algorithm = new SimulatedAnnealing();

        List<Stop> solution = algorithm.findSolution(stops);
        int tourDistance = calculateTotalDistance(solution);

        assertThat(tourDistance, lessThan(1200));
    }

    @Test
    public void shouldHandleDifferentScales() {
        List<Stop> stops = makeSomeStops(20,200);
        Collections.shuffle(stops);

        System.out.println("total distance before: " + calculateTotalDistance(stops));

        SimulatedAnnealing algorithm = new SimulatedAnnealing();
        List<Stop> solution = algorithm.findSolution(stops);

        System.out.println("total distance after: " + calculateTotalDistance(solution));
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new SalesmanPanel(solution));
//        frame.setPreferredSize(new Dimension(400, 300));
//        frame.pack();
//        frame.setVisible(true);
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private List<Stop> makeSomeStops(int amount, double scale) {
        ArrayList<Stop> someStops = new ArrayList<>();

        for (int i = 0 ; i < amount ; i++) {
            double randomX = Math.random() * scale;
            double randomY = Math.random() * scale;
            someStops.add(new Stop("", randomX, randomY));
        }

        return someStops;
    }

    private int calculateTotalDistance(List<Stop> stops) {
        int totalDistance = 0;
        for (int stopIndex = 0; stopIndex < stops.size(); stopIndex++) {
            Stop currentStop = stops.get(stopIndex);
            Stop nextStop;
            if (stopIndex < stops.size() - 1) {
                nextStop = stops.get(stopIndex + 1);
            } else {
                nextStop = stops.get(0);
            }

            totalDistance += currentStop.distanceTo(nextStop);
        }
        return totalDistance;
    }

}
