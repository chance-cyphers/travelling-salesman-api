package chance.pants.api.controllers;

import chance.pants.api.algorithm.SimulatedAnnealing;
import chance.pants.api.domain.Stop;
import chance.pants.api.domain.Tour;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping(method=GET)
    public Tour getTour() {
        List<Stop> allStops = getAllStops();
        if (allStops.size() <= 1) { return new Tour(allStops); }

        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
        List<Stop> solution = simulatedAnnealing.findSolution(allStops);
        return new Tour(solution);
    }

    private List<Stop> getAllStops() {
        RMap<String, Stop> stops = redissonClient.getMap("stops");
        if (stops == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(stops.values());
    }
}
