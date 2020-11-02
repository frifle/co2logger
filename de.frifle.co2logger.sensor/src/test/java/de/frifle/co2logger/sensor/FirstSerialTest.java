package de.frifle.co2logger.sensor;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FirstSerialTest {

	private static final Logger LOG = Logger.getLogger(FirstSerialTest.class.getName());

	private String portName = "/dev/ttyS0";

	@ParameterizedTest
	@MethodSource("provideRequests")
	void testReadResponse(AbstractMHZ19Request<?> request) throws Exception {
		try (MHZ19Sensor sensor = new MHZ19Sensor(portName)) {
			AbstractMHZ19Response response = sensor.sendRequest(request);

			LOG.log(Level.INFO, "Got Response: {0}", response);

			assertThat(response.getCommand(), is(request.getCommand()));
		}
	}

    @Test
    @Disabled
	void testSetABCStatus() throws Exception {
		try (MHZ19Sensor sensor = new MHZ19Sensor(portName)) {
			SetABCStatusRequest request = new SetABCStatusRequest(ABCStatus.OFF);
			AbstractMHZ19Response response = sensor.sendRequest(request);

			LOG.log(Level.INFO, "Got Response: {0}", response);

		}
	}

    @Test
    @Disabled
	void testReset() throws Exception {
		try (MHZ19Sensor sensor = new MHZ19Sensor(portName)) {
			ResetSensorRequest request = new ResetSensorRequest();
			AbstractMHZ19Response response = sensor.sendRequest(request);

			LOG.log(Level.INFO, "Got Response: {0}", response);

		}
	}

    @Test
    //@Disabled
	void testZeroPointCalibration() throws Exception {
		try (MHZ19Sensor sensor = new MHZ19Sensor(portName)) {
			ZeroPointCalibrationRequest request = new ZeroPointCalibrationRequest();
			AbstractMHZ19Response response = sensor.sendRequest(request);

			LOG.log(Level.INFO, "Got Response: {0}", response);

		}
	}

	@Test
	@Disabled
	void testSetSensorRange() throws Exception {
		try (MHZ19Sensor sensor = new MHZ19Sensor(portName)) {
			SetSensorRangeRequest request = new SetSensorRangeRequest(SensorRange.TWO_THOUSAND);
			ACKResponse response = sensor.sendRequest(request);

			LOG.log(Level.INFO, "Got Response: {0}", response);

		}
	}

	private static Stream<AbstractMHZ19Request<?>> provideRequests() {
		return Stream.of(new ReadCO2ValueRequest()
				, new ReadRawCO2Request()
				, new ReadRawValuesRequest()
				, new ReadSensorRangeRequest()
				, new ReadDACRangeRequest()
				, new ReadABCStatusRequest()
				);
	}

}