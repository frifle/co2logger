package de.frifle.co2logger.sensor;

public class SetSensorRangeRequest extends AbstractMHZ19Request<ACKResponse> {

	private final SensorRange range;

	public SetSensorRangeRequest( SensorRange range ) {
		this.range = range;
	}

    @Override
    public byte getCommand() {
        return (byte)0x99;
    }

    @Override
    public byte[] getData() {
    	byte[] data = super.getData();
    	data[1] = (byte)( ( this.range.getLowValue() / 256 ) & 0xff );
    	data[2] = (byte)( this.range.getLowValue() & 0xff );
    	data[3] = (byte)( ( this.range.getHighValue() / 256 ) & 0xff );
    	data[4] = (byte)( this.range.getHighValue() & 0xff );
    	return data;
    }

    @Override
    public ACKResponse generateResponse( byte[] data) {
        return new ACKResponse( data) ;
    }
}