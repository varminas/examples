package lt.arminai;

import lt.arminai.config.MyConnection;
import lt.arminai.config.MyConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * @author Vytautas Arminas
 */
@Service
public class MainRunner implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(MainRunner.class);

    @Autowired
    private MyConnectionFactory myConnectionFactory;

    @Override
    public void run(String... strings) throws Exception {
        logger.debug("Starting");

        MyConnection myConnection = myConnectionFactory.create();
        myConnection.connect();
    }
}
