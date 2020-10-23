package de.frifle.co2logger.sensor;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class FirstSerialTest {

    private static final Logger LOG = Logger.getLogger(FirstSerialTest.class.getName());

    private String portName = "/dev/ttyS0";
   

    @Test
    public void testReadCo2Value() throws Exception {
        try( MHZ19Sensor sensor = new MHZ19Sensor( portName ) ) {
            ReadCO2ValueRequest request = new ReadCO2ValueRequest();
            ReadCO2ValueResponse response = sensor.sendRequest(request);

            LOG.log(Level.INFO, "Got Response: {0}", response);

        }
    }

    @Test
    public void testDetectorRangeValue() throws Exception {
        try( MHZ19Sensor sensor = new MHZ19Sensor( portName ) ) {

            ReadDetectorRangeRequest request = new ReadDetectorRangeRequest();
            ReadDetectorRangeResponse response = sensor.sendRequest(request);

            LOG.log(Level.INFO, "Got Response: {0}", response);
        }
    }

    @Test
    public void testReadRawValues() throws Exception {
        try( MHZ19Sensor sensor = new MHZ19Sensor( portName ) ) {

            ReadRawValuesRequest request = new ReadRawValuesRequest();
            ReadRawValuesResponse response = sensor.sendRequest(request);

            LOG.log(Level.INFO, "Got Response: {0}", response);
        }
    }
}