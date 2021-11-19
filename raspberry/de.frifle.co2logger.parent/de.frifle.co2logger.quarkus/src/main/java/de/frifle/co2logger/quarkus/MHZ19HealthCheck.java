package de.frifle.co2logger.quarkus;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import de.frifle.co2logger.quarkus.boundary.MHZ19Dto;
import de.frifle.co2logger.quarkus.boundary.MHZ19SensorBoundary;

@ApplicationScoped
@Liveness
public class MHZ19HealthCheck implements HealthCheck {

	@Inject
	private MHZ19SensorBoundary sensor;

	@Override
	public HealthCheckResponse call() {
		MHZ19Dto dto = null;
		String errorMessage = null;
		try {
			dto = sensor.readCurrentData();
		} catch(NullPointerException e) {
			if ( sensor==null ) {
				errorMessage = "oups, sensor is null";
			} else {
				errorMessage = "Nullpointer: "+ ( e.getCause()!=null ? e.getCause().getMessage() : e.getMessage() );
			}
		} catch(IOException e) {
			errorMessage = e.getMessage();
		}
		return HealthCheckResponse.named( String.format("Sensor %d at %s", sensor.getSensorNumber(), sensor.getSensorCommPortName()))
			.state( dto != null )
			.withData("currentState", errorMessage == null
				? String.format("connected, current Value %d", dto.getCo2Value() )
				: String.format("error: %s ", errorMessage) )
			.build();
	}
}
