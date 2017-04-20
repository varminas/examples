package lt.arminai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vytautas Arminas
 */
@Configuration
@EnableConfigurationProperties(MyCustomProperties.class)
public class MyCustomAutoConfiguration {
    @Autowired
    private MyCustomProperties properties;

    @Bean
    public MyConnectionFactory myConnectionFactory() {
        Map<MyConnectionFactory.PARAMS, Object> params = new HashMap<>();
        params.put(MyConnectionFactory.PARAMS.HOST, properties.getHost());
        params.put(MyConnectionFactory.PARAMS.PORT, properties.getPort());

        return new MyConnectionFactory(params);
    }
}
