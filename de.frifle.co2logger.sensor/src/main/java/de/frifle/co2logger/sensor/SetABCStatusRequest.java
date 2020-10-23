package de.frifle.co2logger.sensor;

public class SetABCStatusRequest extends AbstractMHZ19Request<ACKResponse> {

	private final ABCStatus status;

	public SetABCStatusRequest(ABCStatus status) {
		this.status = status;
	}

    @Override
    public byte getCommand() {
        return (byte)0x79;
    }

    @Override
    public byte[] getData() {
    	byte[] data = super.getData();
    	data[0] = this.status == ABCStatus.ON ? (byte)0xa0 : (byte)0x00;
    	return data;
    }

    @Override
    public ACKResponse generateResponse( byte[] data) {
        return new ACKResponse( data) ;
    }
}