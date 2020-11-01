package de.frifle.co2logger.sensor;

public class NoResponse extends AbstractMHZ19Response {

    public NoResponse( byte command, byte[] data) {
        super( new byte[]{ (byte)0xff, command} );
    }

    @Override
    public String toString() {
        return "No Response!";
    }
}