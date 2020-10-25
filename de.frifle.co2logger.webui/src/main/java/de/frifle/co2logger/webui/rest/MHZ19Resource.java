package de.frifle.co2logger.webui.rest;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.frifle.co2logger.webui.boundary.MHZ19Dto;
import de.frifle.co2logger.webui.boundary.MHZ19SensorBoundary;

import javax.inject.Inject;

@ApplicationScoped
@Path("/mhz19")
public class MHZ19Resource {

	@Inject
	private MHZ19SensorBoundary sensor;

	@GET
	@Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
	public MHZ19Dto getCurrentData() throws IOException {
		return sensor.readCurrentData();
	}

}
