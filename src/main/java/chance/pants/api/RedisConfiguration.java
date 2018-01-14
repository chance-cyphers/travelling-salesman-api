package chance.pants.api;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        String address = "redis://h:p274c0bf76333d63bf53c25d6d93abeda23afbf43f175b8ca24e447b0fe84da2f@ec2-54-85-101-85.compute-1.amazonaws.com:56199";
        singleServerConfig.setAddress(System.getenv("REDIS_URL"));
        return Redisson.create(config);
    }

}
