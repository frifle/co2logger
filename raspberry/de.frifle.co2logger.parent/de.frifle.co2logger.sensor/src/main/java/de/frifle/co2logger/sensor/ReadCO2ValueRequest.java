package de.frifle.co2logger.sensor;

public class ReadCO2ValueRequest extends AbstractMHZ19Request<ReadCO2ValueResponse>{
 
    @Override
    public byte getCommand() {
        return (byte)0x86;
    }

    @Override
    public ReadCO2ValueResponse generateResponse( byte[] data) {        
        return new ReadCO2ValueResponse( data) ;
    }
}