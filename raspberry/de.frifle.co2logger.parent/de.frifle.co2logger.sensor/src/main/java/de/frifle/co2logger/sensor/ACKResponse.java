package de.frifle.co2logger.sensor;

public class ACKResponse extends AbstractMHZ19Response {


    public ACKResponse(byte[] data) {
        super( data );
    }

    @Override
    public String toString() {
        return "ACK!";
    }
}