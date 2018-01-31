package chance.pants.api.controllers;

import chance.pants.api.resources.Stop;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/stop")
public class StopController {

    @Autowired
    RedissonClient redissonClient;

//    @Autowired
//    SimpMessagingTemplate websocket;

    @RequestMapping(method=POST)
    public Stop createStop(@RequestBody Stop newStop) {
        long stopId = redissonClient.getAtomicLong("stopId").incrementAndGet();
        newStop.setId(stopId);
        redissonClient.getMap("stops").fastPut(stopId, newStop);
        return newStop;
    }

    @RequestMapping(method=GET)
    public List<Stop> getAllStops() {
        RMap<String, Stop> stops = redissonClient.getMap("stops");
        if (stops == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(stops.values());
    }

    @RequestMapping(path="/{stopId}", method=GET)
    public Stop getStop(@PathVariable long stopId) {
        RMap<String, Stop> stops = redissonClient.getMap("stops");
        if (stops == null) {
            return null;
        }
        return stops.get(stopId);
    }

    @RequestMapping(method=DELETE)
    public void deleteStops() {
        redissonClient.getKeys().flushall();
    }

}
