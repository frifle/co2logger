package de.frifle.co2logger.sensor;

public class ReadRawCO2Request extends AbstractMHZ19Request<ReadRawCO2Response> {

    @Override
    public byte getCommand() {
        return (byte)0x84;
    }

    @Override
    public ReadRawCO2Response generateResponse( byte[] data) {
        return new ReadRawCO2Response( data) ;
    }
}