package de.frifle.co2logger.sensor;

public class ReadRawCO2Response extends AbstractMHZ19Response {

    private final int rawCo2Value;

    public ReadRawCO2Response(byte[] data) {
        super( data );
        this.rawCo2Value = parseRawCo2Value(data);
    }

    public int getRawCo2Value() {
		return rawCo2Value;
	}

    @Override
    public String toString() {
        return String.format("RAW CO2 value=%d", rawCo2Value);
    }

    private int parseRawCo2Value( byte[] data ) {
        return (0xff&data[2]) * 256 + (0xff&data[3]);
    }


}