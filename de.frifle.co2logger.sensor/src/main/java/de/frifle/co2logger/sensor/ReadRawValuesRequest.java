package de.frifle.co2logger.sensor;

public class ReadRawValuesRequest extends AbstractMHZ19Request<ReadRawValuesResponse> {
 
    @Override
    public byte getCommand() {
        return (byte)0x85;
    }

    @Override
    public ReadRawValuesResponse generateResponse( byte[] data) {        
        return new ReadRawValuesResponse( data) ;
    }
}