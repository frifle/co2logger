package de.frifle.co2logger.sensor;

public class ReadCO2ValueResponse extends AbstractMHZ19Response {
    
    private final int co2Value;
    private final int temperature;

    public ReadCO2ValueResponse(byte[] data) {
        super( data );
        this.co2Value = parseCo2Value(data);
        this.temperature = parseTemperature(data);
    }

    public int getCo2Value() {
        return co2Value;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return String.format("Current values: co2=%d, temp=%d", co2Value, temperature );
    }


    private int parseCo2Value( byte[] data ) {
        return (0xff&data[2]) * 256 + (0xff&data[3]);
    }

    private int parseTemperature( byte[] data ) {
        return ( (0xff&data[4]) - 32 ) * 5 / 9;
    }
}