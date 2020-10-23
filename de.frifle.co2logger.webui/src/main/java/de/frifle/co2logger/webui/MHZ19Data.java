package de.frifle.co2logger.webui;

import javax.xml.bind.annotation.XmlRootElement;

import de.frifle.co2logger.sensor.ABCStatus;

@XmlRootElement(name = "mhz-19")
public class MHZ19Data {

	private ABCStatus abcStatus;
	private int co2Value;
	private int unclampedCo2Value;
	private int rawCo2Value;
	private int temperature;
	private int dacRangehighValue;
	private int dacRangelowValue;
	private int rawTemperature;
	private int lightValue;
	private int sensorRangeHighValue;
	private int sensorRangeLowValue;

	public ABCStatus getAbcStatus() {
		return abcStatus;
	}

	public int getCo2Value() {
		return co2Value;
	}

	public int getUnclampedCo2Value() {
		return unclampedCo2Value;
	}

	public int getRawCo2Value() {
		return rawCo2Value;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getDacRangehighValue() {
		return dacRangehighValue;
	}

	public int getDacRangelowValue() {
		return dacRangelowValue;
	}

	public int getRawTemperature() {
		return rawTemperature;
	}

	public int getLightValue() {
		return lightValue;
	}

	public int getSensorRangeHighValue() {
		return sensorRangeHighValue;
	}

	public int getSensorRangeLowValue() {
		return sensorRangeLowValue;
	}

	void setAbcStatus(ABCStatus abcStatus) {
		this.abcStatus = abcStatus;
	}

	void setCo2Value(int co2Value) {
		this.co2Value = co2Value;
	}

	void setUnclampedCo2Value(int unclampedCo2Value) {
		this.unclampedCo2Value = unclampedCo2Value;
	}

	void setRawCo2Value(int rawCo2Value) {
		this.rawCo2Value = rawCo2Value;
	}

	void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	void setDacRangehighValue(int dacRangehighValue) {
		this.dacRangehighValue = dacRangehighValue;
	}

	void setDacRangelowValue(int dacRangelowValue) {
		this.dacRangelowValue = dacRangelowValue;
	}

	void setRawTemperature(int rawTemperature) {
		this.rawTemperature = rawTemperature;
	}

	void setLightValue(int lightValue) {
		this.lightValue = lightValue;
	}

	void setSensorRangeHighValue(int sensorRangeHighValue) {
		this.sensorRangeHighValue = sensorRangeHighValue;
	}

	void setSensorRangeLowValue(int sensorRangeLowValue) {
		this.sensorRangeLowValue = sensorRangeLowValue;
	}

}
