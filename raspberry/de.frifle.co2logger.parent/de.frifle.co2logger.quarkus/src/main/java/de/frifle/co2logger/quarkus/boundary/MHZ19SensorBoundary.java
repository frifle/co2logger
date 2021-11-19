package de.frifle.co2logger.quarkus.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.frifle.co2logger.sensor.ABCStatus;
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
import de.frifle.co2logger.sensor.SensorRange;
import de.frifle.co2logger.sensor.SetABCStatusRequest;
import de.frifle.co2logger.sensor.SetSensorRangeRequest;
import de.frifle.co2logger.quarkus.boundary.simplecache.InvalidateCache;
import de.frifle.co2logger.quarkus.boundary.simplecache.SimpleCache;

@ApplicationScoped
public class MHZ19SensorBoundary {

	private static final Logger LOG = Logger.getLogger(MHZ19SensorBoundary.class.getName());

	@Inject
	@ConfigProperty(name = "commPort")
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

	public String getSensorCommPortName() {
		return sensor.getCommPortName();
	}

	public int getSensorNumber() {
		return sensor.getSensorNumber();
	}

	@SimpleCache
	public synchronized MHZ19Dto readCurrentData() throws IOException {
		MHZ19Dto dto = new MHZ19Dto();
		readCO2ValueStatus(dto);
		readRawCO2Status(dto);
		readRawValuesStatus(dto);
		readSensorRangeStatus(dto);
//		readDACRangeStatus(dto);
		readABCStatus(dto);
		return dto;
	}

	@InvalidateCache
	public synchronized void toggleABCStatus() throws IOException {
		MHZ19Dto dto = new MHZ19Dto();
		readABCStatus(dto);
		setABCStatus( dto.getAbcStatus()==ABCStatus.OFF ? ABCStatus.ON : ABCStatus.OFF );
	}

	@InvalidateCache
	public synchronized void toggleSensorRange() throws IOException {
		MHZ19Dto dto = new MHZ19Dto();
		readSensorRangeStatus(dto);
		setSensorRange( dto.getSensorRangeHighValue() == SensorRange.TWO_THOUSAND.getHighValue() ? SensorRange.FIVE_THOUSAND : SensorRange.TWO_THOUSAND );
	}

	private void readCO2ValueStatus( MHZ19Dto dto ) throws IOException {
		ReadCO2ValueResponse response = sensor.sendRequest(new ReadCO2ValueRequest());
		dto.setCo2Value(response.getCo2Value());
		dto.setTemperature(response.getTemperature());
	}

	private void readRawCO2Status( MHZ19Dto dto ) throws IOException {
		ReadRawCO2Response response = sensor.sendRequest(new ReadRawCO2Request());
		dto.setRawCo2Value(response.getRawCo2Value());
	}

	private void readRawValuesStatus( MHZ19Dto dto ) throws IOException {
		ReadRawValuesResponse response = sensor.sendRequest(new ReadRawValuesRequest());
		dto.setUncutCo2Value( response.getCo2Value() );
		dto.setLightValue( response.getLightValue() );
		dto.setRawTemperature( response.getTemperature() );
	}

	private void readSensorRangeStatus( MHZ19Dto dto ) throws IOException {
		ReadSensorRangeResponse response = sensor.sendRequest(new ReadSensorRangeRequest());
		dto.setSensorRangeLowValue( response.getLowValue() );
		dto.setSensorRangeHighValue( response.getHighValue() );
	}

	private void readDACRangeStatus( MHZ19Dto dto ) throws IOException {
		ReadDACRangeResponse response = sensor.sendRequest(new ReadDACRangeRequest());
		dto.setDacRangelowValue( response.getLowValue() );
		dto.setDacRangehighValue( response.getHighValue() );
	}

	private void readABCStatus( MHZ19Dto dto ) throws IOException {
		ReadABCStatusResponse response = sensor.sendRequest(new ReadABCStatusRequest());
		dto.setAbcStatus( response.getABCStatus());
	}

	private void setABCStatus( ABCStatus status ) throws IOException {
		sensor.sendRequest(new SetABCStatusRequest( status ));
	}

	private void setSensorRange( SensorRange range ) throws IOException {
		sensor.sendRequest(new SetSensorRangeRequest( range ));
	}
}