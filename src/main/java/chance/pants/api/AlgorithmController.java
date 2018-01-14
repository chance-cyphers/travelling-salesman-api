package chance.pants.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/stop")
public class AlgorithmController {

    @RequestMapping(method=POST)
    public String createStop() {
        return "Hello world";
    }

}
