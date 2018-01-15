package chance.pants.api.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfiguration {

    @Bean
    @Profile("local")
    public RedissonClient redissonClientLocal() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }

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
