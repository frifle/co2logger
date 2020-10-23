package de.frifle.co2logger.webui;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/mhz19")
public class MHZ19Resource {

	private MHZ19SensorBoundary sensor;

	@GET
	@Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
	public MHZ19Data getCurrentData() throws IOException {
		return sensor.readCurrentData();
	}

}
