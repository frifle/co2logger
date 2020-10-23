package de.frifle.co2logger.webui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.metrics.annotation.Gauge;

import de.frifle.co2logger.sensor.ABCStatus;

@RequestScoped
public class MHZ19Metrics {

	private static final Logger LOG = Logger.getLogger(MHZ19Metrics.class.getName());

	@Inject
	private MHZ19SensorBoundary sensor;

	private MHZ19Data dto;

	@PostConstruct
	private void readData() {
		try {
			dto = sensor.readCurrentData();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "oups, no connection to mh-z19-sensor", e);
		}
	}

	public ABCStatus getAbcStatus() {
		return dto.getAbcStatus();
	}

	@Gauge(unit = "ppm", name = "co2Value", displayName = "CO2 Value")
	public int getCo2Value() {
		return dto.getCo2Value();
	}

	@Gauge(unit = "ppm", name = "unclambedCo2Value", displayName = "Unclamped CO2 Value")
	public int getUnclampedCo2Value() {
		return dto.getUnclampedCo2Value();
	}

	@Gauge(unit = "some count", name = "rawCo2Value", displayName = "RAW CO2 Value")
	public int getRawCo2Value() {
		return dto.getRawCo2Value();
	}

	@Gauge(unit = "°C", name = "temperature", displayName = "Temperature")
	public int getTemperature() {
		return dto.getTemperature();
	}

	public int getDacRangehighValue() {
		return dto.getDacRangehighValue();
	}

	public int getDacRangelowValue() {
		return dto.getDacRangelowValue();
	}

	@Gauge(unit = "°C", name = "rawTemperature", displayName = "RAW Temperature")
	public int getRawTemperature() {
		return dto.getRawTemperature();
	}

	@Gauge(unit = "some count", name = "light", displayName = "Light")
	public int getLightValue() {
		return dto.getLightValue();
	}

	public int getSensorRangeHighValue() {
		return dto.getSensorRangeHighValue();
	}

	public int getSensorRangeLowValue() {
		return dto.getSensorRangeLowValue();
	}

}
