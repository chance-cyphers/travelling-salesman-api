import chance.pants.algorithm.simulatedannealing.SimulatedAnnealingAlg;
import chance.pants.algorithm.simulatedannealing.Stop;
import chance.pants.example.City;
import chance.pants.example.SimulatedAnnealing;
import chance.pants.example.TourFromExample;
import chance.pants.example.TourManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

public class SimulatedAnnealingTest {

    @Test
    public void solve_findsADecentSolution() {
        City city = new City(60, 200);
        TourManager.addCity(city);
        City city2 = new City(180, 200);
        TourManager.addCity(city2);
        City city3 = new City(80, 180);
        TourManager.addCity(city3);
        City city4 = new City(140, 180);
        TourManager.addCity(city4);
        City city5 = new City(20, 160);
        TourManager.addCity(city5);
        City city6 = new City(100, 160);
        TourManager.addCity(city6);
        City city7 = new City(200, 160);
        TourManager.addCity(city7);
        City city8 = new City(140, 140);
        TourManager.addCity(city8);
        City city9 = new City(40, 120);
        TourManager.addCity(city9);
        City city10 = new City(100, 120);
        TourManager.addCity(city10);
        City city11 = new City(180, 100);
        TourManager.addCity(city11);
        City city12 = new City(60, 80);
        TourManager.addCity(city12);
        City city13 = new City(120, 80);
        TourManager.addCity(city13);
        City city14 = new City(180, 60);
        TourManager.addCity(city14);
        City city15 = new City(20, 40);
        TourManager.addCity(city15);
        City city16 = new City(100, 40);
        TourManager.addCity(city16);
        City city17 = new City(200, 40);
        TourManager.addCity(city17);
        City city18 = new City(20, 20);
        TourManager.addCity(city18);
        City city19 = new City(60, 20);
        TourManager.addCity(city19);
        City city20 = new City(160, 20);
        TourManager.addCity(city20);

        double temp = 10000;
        double coolingRate = 0.003;

        TourFromExample initialTour = new TourFromExample();
        initialTour.generateRandomIndividual();

        System.out.println("initial: " + initialTour.getDistance());

        TourFromExample best = SimulatedAnnealing.findSolution(initialTour, temp, coolingRate);

        assertThat(best.getDistance(), lessThan(1200));
    }

    @Test
    public void solve_findsADecentSolution_newAlg() {
        ArrayList<Stop> stops = new ArrayList<>();
        stops.add(new Stop(60, 200));
        stops.add(new Stop(180, 200));
        stops.add(new Stop(80, 180));
        stops.add(new Stop(140, 180));
        stops.add(new Stop(20, 160));
        stops.add(new Stop(100, 160));
        stops.add(new Stop(200, 160));
        stops.add(new Stop(140, 140));
        stops.add(new Stop(40, 120));
        stops.add(new Stop(100, 120));
        stops.add(new Stop(180, 100));
        stops.add(new Stop(60, 80));
        stops.add(new Stop(120, 80));
        stops.add(new Stop(180, 60));
        stops.add(new Stop(20, 40));
        stops.add(new Stop(100, 40));
        stops.add(new Stop(200, 40));
        stops.add(new Stop(20, 20));
        stops.add(new Stop(60, 20));
        stops.add(new Stop(160, 20));
        Collections.shuffle(stops);

        SimulatedAnnealingAlg algorithm = new SimulatedAnnealingAlg();

        List<Stop> solution = algorithm.findSolution(stops);
        int tourDistance = calculateTotalDistance(solution);

        assertThat(tourDistance, lessThan(1200));
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
