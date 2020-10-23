package de.frifle.co2logger.sensor;

public class ReadDetectorRangeRequest extends AbstractMHZ19Request<ReadDetectorRangeResponse>{
 
    @Override
    public byte getCommand() {
        return (byte)0x9b;
    }

    @Override
    public ReadDetectorRangeResponse generateResponse( byte[] data) {        
        return new ReadDetectorRangeResponse( data) ;
    }
}