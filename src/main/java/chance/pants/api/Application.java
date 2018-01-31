package chance.pants.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

@SpringBootApplication
@Controller
public class Application extends SpringBootServletInitializer implements WebSocketConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(stockWebSocketHandler(), "/new-stop").setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public WebSocketHandler stockWebSocketHandler() {
        System.out.println("websockethandler bean is getting made, at least");
        return new PerConnectionWebSocketHandler(StockWebSocketHandler.class);
    }

    @Bean
    public BroadcastHandler createBroadcastHandler() {
        return new BroadcastHandler();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
