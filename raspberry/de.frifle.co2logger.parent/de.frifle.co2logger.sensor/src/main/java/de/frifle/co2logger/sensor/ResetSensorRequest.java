package de.frifle.co2logger.sensor;

public class ResetSensorRequest extends AbstractMHZ19Request<NoResponse> {

    @Override
    public byte getCommand() {
        return (byte)0x78;
    }

    @Override
    public int getNumberOfResponseBytes() {
    	return 0;
    }

    @Override
    public NoResponse generateResponse( byte[] data) {
        return new NoResponse( data) ;
    }
}