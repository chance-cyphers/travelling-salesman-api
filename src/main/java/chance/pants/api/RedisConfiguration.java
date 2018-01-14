package chance.pants.api;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfiguration {

//    @Bean
//    @Profile("local")
//    public RedissonClient redissonClientLocal() {
//        Config config = new Config();
//        SingleServerConfig singleServerConfig = config.useSingleServer();
//        singleServerConfig.setAddress("redis://ec2-54-85-101-85.compute-1.amazonaws.com:56199");
//        singleServerConfig.setPassword("p274c0bf76333d63bf53c25d6d93abeda23afbf43f175b8ca24e447b0fe84da2f");
//        return Redisson.create(config);
//    }

    @Bean
    @Profile("heroku")
    public RedissonClient redissonClientHeroku() throws URISyntaxException {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();

        URI redisURI = new URI(System.getenv("REDIS_URL"));
        String password = redisURI.getUserInfo().split(":", 2)[1];
        String address = redisURI.getScheme() + "://" + redisURI.getHost() + ":" + redisURI.getPort();

        singleServerConfig.setAddress(address);
        singleServerConfig.setPassword(password);

        return Redisson.create(config);
    }

}
