package lt.arminai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Vytautas Arminas
 */
public class MyConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(MyConnectionFactory.class);

    private Map<PARAMS, Object> params;

    public MyConnectionFactory(Map<PARAMS, Object> params) {
        this.params = params;
    }

    public MyConnection create() {
        return () -> logger.debug("connecting to host: '{}', port: '{}'",
                params.get(PARAMS.HOST), params.get(PARAMS.PORT));
    }

    public Map<PARAMS, Object> getParams() {
        return params;
    }

    public enum PARAMS {
        HOST,
        PORT
    }
}
