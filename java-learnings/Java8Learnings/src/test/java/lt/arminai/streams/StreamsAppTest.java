package lt.arminai.streams;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StreamsAppTest {

    private StreamsApp streamsApp;
    @Before
    public void setUp() throws Exception {
        streamsApp = new StreamsApp();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStreamsExcercised() throws Exception {
        streamsApp.streamsExcercised();
    }
}