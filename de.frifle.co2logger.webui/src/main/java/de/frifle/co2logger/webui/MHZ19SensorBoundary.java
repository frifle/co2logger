package de.frifle.co2logger.webui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.frifle.co2logger.sensor.MHZ19Exception;
import de.frifle.co2logger.sensor.MHZ19Sensor;
import de.frifle.co2logger.sensor.ReadABCStatusRequest;
import de.frifle.co2logger.sensor.ReadABCStatusResponse;
import de.frifle.co2logger.sensor.ReadCO2ValueRequest;
import de.frifle.co2logger.sensor.ReadCO2ValueResponse;
import de.frifle.co2logger.sensor.ReadDACRangeRequest;
import de.frifle.co2logger.sensor.ReadDACRangeResponse;
import de.frifle.co2logger.sensor.ReadRawCO2Request;
import de.frifle.co2logger.sensor.ReadRawCO2Response;
import de.frifle.co2logger.sensor.ReadRawValuesRequest;
import de.frifle.co2logger.sensor.ReadRawValuesResponse;
import de.frifle.co2logger.sensor.ReadSensorRangeRequest;
import de.frifle.co2logger.sensor.ReadSensorRangeResponse;

@ApplicationScoped
public class MHZ19SensorBoundary {

	private static final Logger LOG = Logger.getLogger(MHZ19SensorBoundary.class.getName());

	@Inject
	@ConfigProperty(name = "default.commPort")
	private String defaultCommPort;

	private MHZ19Sensor sensor;

	@PostConstruct
	private void initSensor() {
		try {
			sensor = new MHZ19Sensor(defaultCommPort);
		} catch (MHZ19Exception e) {
			LOG.log(Level.WARNING, "oups, cant instantiate the MH-Z19 Sensor Bean at port "+defaultCommPort+".", e);
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
	private void closeSensor() {
		if (sensor != null) {
			sensor.close();
		}
	}

	public MHZ19Data readCurrentData() throws IOException {
		MHZ19Data dto = new MHZ19Data();
		{
			ReadCO2ValueResponse response = sensor.sendRequest(new ReadCO2ValueRequest());
			dto.setCo2Value(response.getCo2Value());
			dto.setTemperature(response.getTemperature());
		}
		{
			ReadRawCO2Response response = sensor.sendRequest(new ReadRawCO2Request());
			dto.setRawCo2Value(response.getRawCo2Value());
		}
		{
			ReadRawValuesResponse response = sensor.sendRequest(new ReadRawValuesRequest());
			dto.setUnclampedCo2Value( response.getCo2Value() );
			dto.setLightValue( response.getLightValue() );
			dto.setRawTemperature( response.getTemperature() );
		}
		{
			ReadSensorRangeResponse response = sensor.sendRequest(new ReadSensorRangeRequest());
			dto.setSensorRangeLowValue( response.getLowValue() );
			dto.setSensorRangeHighValue( response.getHighValue() );
		}
		{
			ReadDACRangeResponse response = sensor.sendRequest(new ReadDACRangeRequest());
			dto.setDacRangelowValue( response.getLowValue() );
			dto.setDacRangehighValue( response.getHighValue() );
		}
		{
			ReadABCStatusResponse response = sensor.sendRequest(new ReadABCStatusRequest());
			dto.setAbcStatus( response.getABCStatus());
		}
		return dto;
	}

}