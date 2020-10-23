package de.frifle.co2logger.sensor;

public class ReadDACRangeResponse extends AbstractMHZ19Response {

    private final int highValue;
    private final int lowValue;

    public ReadDACRangeResponse(byte[] data) {
        super( data );
        this.highValue = parseHighValue(data);
        this.lowValue = parseLowValue(data);
    }

    public int getHighValue() {
        return highValue;
    }

    public int getLowValue() {
        return lowValue;
    }

    @Override
    public String toString() {
        return String.format("Current DAC range: low=%d, high=%d", lowValue, highValue );
    }


    private int parseHighValue( byte[] data ) {
        return (0xff&data[4]) * 256 + (0xff&data[5]);
    }

    private int parseLowValue( byte[] data ) {
        return (0xff&data[2]) * 256 + (0xff&data[3]);
    }
}