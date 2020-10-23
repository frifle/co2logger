package de.frifle.co2logger.sensor;

public class ReadSensorRangeRequest extends AbstractMHZ19Request<ReadSensorRangeResponse>{
 
    @Override
    public byte getCommand() {
        return (byte)0x9b;
    }

    @Override
    public ReadSensorRangeResponse generateResponse( byte[] data) {        
        return new ReadSensorRangeResponse( data) ;
    }
}