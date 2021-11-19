package de.frifle.co2logger.webui.frontend;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.frifle.co2logger.webui.boundary.MHZ19Dto;
import de.frifle.co2logger.webui.boundary.MHZ19SensorBoundary;

@Named("mhz19Controller")
@RequestScoped
public class MHZ19Controller implements Serializable {

	private static final long serialVersionUID = -2828718691227943515L;

	private MHZ19Dto mhz19dto;
	private String errorMessage;

	@Inject
	private MHZ19SensorBoundary boundary;

	@PostConstruct
	private void init() {
		try {
			mhz19dto = boundary.readCurrentData();
		} catch(IOException e) {
			errorMessage = e.getMessage();
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public MHZ19Dto getMhz19dto() {
		return mhz19dto;
	}

	public String toggleABCStatusAction() {
		try {
			boundary.toggleABCStatus();
			return "index?faces-redirect=true";
		} catch (IOException e) {
			errorMessage = e.getMessage();
			return null;
		}
	}

	public String toggleSensorRangeAction() {
		try {
			boundary.toggleSensorRange();
			return "index?faces-redirect=true";
		} catch (IOException e) {
			errorMessage = e.getMessage();
			return null;
		}
	}

	public String zeroCalibrationAction() {
		try {
			boundary.zeroCalibrate();
			return "index?faces-redirect=true";
		} catch (IOException e) {
			errorMessage = e.getMessage();
			return null;
		}
	}

	public String resetAction() {
		try {
			boundary.reset();
			return "index?faces-redirect=true";
		} catch (IOException e) {
			errorMessage = e.getMessage();
			return null;
		}
	}
}
