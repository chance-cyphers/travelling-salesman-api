package chance.pants.api.algorithm;

import chance.pants.api.domain.Stop;
import chance.pants.api.domain.Tour;

import java.util.List;

public class SimulatedAnnealing {

    //TODO research starting temp and cooling rate
    private static final double startingTemp = 10000;
    private static final double coolingRate = 0.003;

    public List<Stop> findSolution(List<Stop> stops) {
        double temp = startingTemp;
        Tour currentSolution = new Tour(stops);
        Tour bestSolution = new Tour(stops);

        while (temp > 1) {
            Tour neighborSolution = findNeighbor(currentSolution);

            double currentEnergy = currentSolution.getTotalDistance();
            double neighborEnergy = neighborSolution.getTotalDistance();

            if (calculateAcceptanceProbability(currentEnergy, neighborEnergy, temp) > Math.random()) {
                currentSolution = neighborSolution;
            }

            if (currentSolution.getTotalDistance() < bestSolution.getTotalDistance()) {
                bestSolution = currentSolution;
            }

            temp *= 1 - coolingRate;
        }

        return bestSolution.getStops();
    }

    //TODO inject function for finding neighbor
    private Tour findNeighbor(Tour currentSolution) {
        int randomIndex1 = (int) (currentSolution.size() * Math.random());
        int randomIndex2 = (int) (currentSolution.size() * Math.random());
        return currentSolution.getSwappedTour(randomIndex1, randomIndex2);
    }

    private static double calculateAcceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        if (newEnergy < currentEnergy) {
            return 1.0;
        }
        //it seems like the temperature should be a function of the average distance from cities?
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

}
