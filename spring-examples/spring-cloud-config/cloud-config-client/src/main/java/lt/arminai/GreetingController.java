package lt.arminai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vytautas on 16.1.4.
 */
@RestController
@RefreshScope
public class GreetingController {

    @Value("${a}")
	String name = "World";

    @RequestMapping("/greetings")
    public String home() {
        return "Hello " + name;
    }

}
