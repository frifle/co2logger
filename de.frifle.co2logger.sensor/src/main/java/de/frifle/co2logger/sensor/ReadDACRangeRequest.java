package de.frifle.co2logger.sensor;

public class ReadDACRangeRequest extends AbstractMHZ19Request<ReadDACRangeResponse>{

    @Override
    public byte getCommand() {
        return (byte)0xa5;
    }

    @Override
    public ReadDACRangeResponse generateResponse( byte[] data) {
        return new ReadDACRangeResponse( data) ;
    }
}