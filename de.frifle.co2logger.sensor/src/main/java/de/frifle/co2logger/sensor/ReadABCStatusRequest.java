package de.frifle.co2logger.sensor;

public class ReadABCStatusRequest extends AbstractMHZ19Request<ReadABCStatusResponse> {
 
    @Override
    public byte getCommand() {
        return (byte)0x86;
    }

    @Override
    public ReadABCStatusResponse generateResponse( byte[] data) {        
        return new ReadABCStatusResponse( data) ;
    }
}