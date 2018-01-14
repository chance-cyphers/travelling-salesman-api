package chance.pants.api;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/stop")
public class AlgorithmController {

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping(method=POST)
    public String createStop(@RequestBody String input) {
        redissonClient.getBucket("stop").set(input);
        return input;
    }

}
