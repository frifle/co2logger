package de.frifle.co2logger.sensor;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class FirstSerialTest {

    private static final Logger LOG = Logger.getLogger(FirstSerialTest.class.getName());

    private String portName = "/dev/ttyS0";


    @ParameterizedTest
    @MethodSource("provideRequests")
    public void testReadResponse( AbstractMHZ19Request<?> request ) throws Exception {
        try( MHZ19Sensor sensor = new MHZ19Sensor( portName ) ) {
            AbstractMHZ19Response response = sensor.sendRequest(request);

            LOG.log(Level.INFO, "Got Response: {0}", response);

            assertThat( response.getCommand(), is( request.getCommand() ));
        }
    }

//    @Test
    public void ignoreTestSetABCStatus() throws Exception {
        try( MHZ19Sensor sensor = new MHZ19Sensor( portName ) ) {
            SetABCStatusRequest request = new SetABCStatusRequest( ABCStatus.OFF );
            AbstractMHZ19Response response = sensor.sendRequest(request);

            LOG.log(Level.INFO, "Got Response: {0}", response);

        }
    }

    private static Stream<AbstractMHZ19Request<?>> provideRequests() {
    	return Stream.of( new ReadCO2ValueRequest()
    			, new ReadDetectorRangeRequest()
    			, new ReadRawValuesRequest()
    			, new ReadABCStatusRequest()
    			);
    }

}