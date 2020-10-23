package de.frifle.co2logger.sensor;

public class ResetSensorRequest extends AbstractMHZ19Request<ACKResponse> {

    @Override
    public byte getCommand() {
        return (byte)0x78;
    }

    @Override
    public ACKResponse generateResponse( byte[] data) {
        return new ACKResponse( data) ;
    }
}