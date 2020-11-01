package de.frifle.co2logger.sensor;

public class NoResponse extends AbstractMHZ19Response {

    public NoResponse(byte[] data) {
        super( data );
    }

    @Override
    public String toString() {
        return "No Response!";
    }
}