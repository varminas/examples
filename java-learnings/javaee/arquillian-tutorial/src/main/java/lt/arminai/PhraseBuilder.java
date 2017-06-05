package lt.arminai;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vytautas on 17.6.5.
 */
public class PhraseBuilder {
    private Map<String, String> templates;

    public String buildPhrase(String id, Object... args) {
        return MessageFormat.format(templates.get(id), args);
    }

    @PostConstruct
    public void initialize() {
        templates = new HashMap<>();
        templates.put("hello", "Hello, {0}!");
    }
}
