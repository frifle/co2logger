package de.frifle.co2logger.webui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.metrics.annotation.Gauge;

import de.frifle.co2logger.sensor.ABCStatus;
import de.frifle.co2logger.webui.boundary.MHZ19Dto;
import de.frifle.co2logger.webui.boundary.MHZ19SensorBoundary;

@ApplicationScoped
public class MHZ19Metrics {

	private static final Logger LOG = Logger.getLogger(MHZ19Metrics.class.getName());

	@Inject
	private MHZ19SensorBoundary sensor;

	private MHZ19Dto getDto() {
		try {
			// here you get cached data, to this call usually is not expensive
			return sensor.readCurrentData();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "oups, no connection to mh-z19-sensor", e);
			throw new RuntimeException(e);
		}
	}

	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        // intentionally empty
		// replaces @StartUp
    }

	public ABCStatus getAbcStatus() {
		return getDto().getAbcStatus();
	}

	@Gauge(unit = "ppm", name = "co2Value", displayName = "CO2 Value")
	public int getCo2Value() {
		return getDto().getCo2Value();
	}

	@Gauge(unit = "ppm", name = "unclambedCo2Value", displayName = "Unclamped CO2 Value")
	public int getUnclampedCo2Value() {
		return getDto().getUnclampedCo2Value();
	}

	@Gauge(unit = "some count", name = "rawCo2Value", displayName = "RAW CO2 Value")
	public int getRawCo2Value() {
		return getDto().getRawCo2Value();
	}

	@Gauge(unit = "°C", name = "temperature", displayName = "Temperature")
	public int getTemperature() {
		return getDto().getTemperature();
	}

	public int getDacRangehighValue() {
		return getDto().getDacRangehighValue();
	}

	public int getDacRangelowValue() {
		return getDto().getDacRangelowValue();
	}

	@Gauge(unit = "°C", name = "rawTemperature", displayName = "RAW Temperature")
	public int getRawTemperature() {
		return getDto().getRawTemperature();
	}

	@Gauge(unit = "some count", name = "light", displayName = "Light")
	public int getLightValue() {
		return getDto().getLightValue();
	}

	public int getSensorRangeHighValue() {
		return getDto().getSensorRangeHighValue();
	}

	public int getSensorRangeLowValue() {
		return getDto().getSensorRangeLowValue();
	}

}
