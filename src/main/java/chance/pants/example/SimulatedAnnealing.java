package chance.pants.example;

public class SimulatedAnnealing {

    public static double calculateAcceptanceProbability(int currentEnergy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < currentEnergy) {
            return 1.0;
        }

        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

    public static TourFromExample findSolution(TourFromExample startingTour, double temp, double coolingRate) {
        TourFromExample currentSolution = new TourFromExample(startingTour.getTour());
        TourFromExample best = new TourFromExample(currentSolution.getTour());

        // Loop until system has cooled
        while (temp > 1) {
            // Create new neighbour tour
            TourFromExample newSolution = new TourFromExample(currentSolution.getTour());

            // Get a random positions in the tour
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());

            // Get the cities at selected positions in the tour
            City cityToSwap1 = newSolution.getCity(tourPos1);
            City cityToSwap2 = newSolution.getCity(tourPos2);

            // Swap them
            newSolution.setCity(tourPos2, cityToSwap1);
            newSolution.setCity(tourPos1, cityToSwap2);

            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            // Decide if we should accept the neighbour
            if (calculateAcceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new TourFromExample(newSolution.getTour());
            }

            if (currentSolution.getDistance() < best.getDistance()) {
                best = new TourFromExample(currentSolution.getTour());
            }

            temp *= 1 - coolingRate;
        }
        return best;
    }

}
