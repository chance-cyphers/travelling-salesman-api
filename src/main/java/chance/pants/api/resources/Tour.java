package chance.pants.api.resources;

import java.util.ArrayList;
import java.util.List;

//TODO make this a generic class/interface that works for any simAnneal
public class Tour {

    private final List<Stop> stops;

    public Tour(List<Stop> stops) {
        this.stops = new ArrayList<>(stops);
    }

    public List<Stop> getStops() {
        return this.stops;
    }

    public Stop getStop(int tourPosition) {
        return stops.get(tourPosition);
    }

    public void setStop(int index, Stop city) {
        this.stops.set(index, city);
    }

    public int size() {
        return this.stops.size();
    }

    public double getTotalDistance() {
        double tourDistance = 0;
        for (int stopIndex = 0; stopIndex < size(); stopIndex++) {
            Stop currentStop = getStop(stopIndex);
            Stop nextStop = getNextStop(stopIndex);
            tourDistance += currentStop.distanceTo(nextStop);
        }
        return tourDistance;
    }

    private Stop getNextStop(int currentIndex) {
        if (currentIndex < size() - 1) {
            return getStop(currentIndex + 1);
        } else {
            return getStop(0);
        }
    }

    public Tour getSwappedTour(int firstIndex, int secondIndex) {
        Stop stopToSwap1 = getStop(firstIndex);
        Stop stopToSwap2 = getStop(secondIndex);
        Tour swappedTour = new Tour(stops);
        swappedTour.setStop(firstIndex, stopToSwap2);
        swappedTour.setStop(secondIndex, stopToSwap1);
        return swappedTour;
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < size(); i++) {
            geneString += getStop(i) + "|";
        }
        return geneString;
    }

}
