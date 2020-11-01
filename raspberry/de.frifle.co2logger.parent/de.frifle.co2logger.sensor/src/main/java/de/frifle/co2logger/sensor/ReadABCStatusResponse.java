package de.frifle.co2logger.sensor;

public class ReadABCStatusResponse extends AbstractMHZ19Response {

    private final boolean abc;

    public ReadABCStatusResponse(byte[] data) {
        super( data );
        this.abc = parseABCValue(data);
    }

    public boolean isAbc() {
		return abc;
	}

    public ABCStatus getABCStatus() {
    	return abc ? ABCStatus.ON : ABCStatus.OFF;
    }

    @Override
    public String toString() {
        return String.format("Current ABC status: %s", getABCStatus() );
    }


    private boolean parseABCValue( byte[] data ) {
        return (0xff&data[7]) == 1;
    }


}