package lt.arminai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyCustomAutoConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class CustomSpringBootAutoconfigureApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
        MyConnectionFactory myConnectionFactory = context.getBean(MyConnectionFactory.class);
        Map<MyConnectionFactory.PARAMS, Object> params = myConnectionFactory.getParams();

        assertThat(params.size(), is(2));
        assertThat(params.get(MyConnectionFactory.PARAMS.HOST), is("test"));
        assertThat(params.get(MyConnectionFactory.PARAMS.PORT), is("8080"));
    }


}
