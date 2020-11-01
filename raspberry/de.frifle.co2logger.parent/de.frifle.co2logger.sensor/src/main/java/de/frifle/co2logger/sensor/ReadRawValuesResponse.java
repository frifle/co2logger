package de.frifle.co2logger.sensor;

public class ReadRawValuesResponse extends AbstractMHZ19Response {

    private final int co2Value;
    private final int temperature;
    private final int lightValue;

    public ReadRawValuesResponse(byte[] data) {
        super( data );
        this.co2Value = parseCo2Value(data);
        this.temperature = parseTemperature(data);
        this.lightValue = parseLightValue(data);
    }

    public int getCo2Value() {
        return co2Value;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getLightValue() {
        return lightValue;
    }

    @Override
    public String toString() {
        return String.format("RAW values: co2=%d, temp=%d, light=%d", co2Value, temperature, lightValue );
    }

    private int parseCo2Value( byte[] data ) {
        return (0xff&data[4]) * 256 + (0xff&data[5]);
    }

    private int parseTemperature( byte[] data ) {
        return (0xff&data[2]) * 256 + (0xff&data[3]);
    }

    private int parseLightValue( byte[] data ) {
        return (0xff&data[6]) * 256 + (0xff&data[7]);
    }
}