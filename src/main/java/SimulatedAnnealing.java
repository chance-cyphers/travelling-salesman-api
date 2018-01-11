public class SimulatedAnnealing {

    public static double calculateAcceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }

    public static Tour findSolution(Tour startingTour, double temp, double coolingRate) {
        Tour currentSolution = new Tour(startingTour.getTour());
        Tour best = new Tour(currentSolution.getTour());

        // Loop until system has cooled
        while (temp > 1) {
            // Create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            // Get a random positions in the tour
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());

            // Get the cities at selected positions in the tour
            City citySwap1 = newSolution.getCity(tourPos1);
            City citySwap2 = newSolution.getCity(tourPos2);

            // Swap them
            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);

            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            // Decide if we should accept the neighbour
            if (calculateAcceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
            }

            temp *= 1 - coolingRate;
        }
        return best;
    }

}
