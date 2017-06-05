package lt.arminai;

import javax.inject.Inject;
import java.io.PrintStream;

/**
 * Created by vytautas on 17.6.5.
 */
public class Greeter {

    private final PhraseBuilder phraseBuilder;

    @Inject
    public Greeter(PhraseBuilder phraseBuilder) {
        this.phraseBuilder = phraseBuilder;
    }

    public void greet(PrintStream to, String name) {
        to.println(createGreeting(name));
    }

    public String createGreeting(String name) {
        return phraseBuilder.buildPhrase("hello", name);
    }
}
