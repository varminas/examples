package lt.arminai;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by vytautas on 17.6.5.
 */
@RunWith(Arquillian.class)
public class GreeterTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(Greeter.class, PhraseBuilder.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

    @Inject
    Greeter greeter;

    @Test
    public void shouldCreateGreeting() {
        Assert.assertEquals("Hello, Vytautas!", greeter.createGreeting("Vytautas"));

        greeter.greet(System.out, "Vytautas");
    }
}
