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
    public ACKResponse generateResponse( byte[] data) {
        return new ACKResponse( data) ;
    }
}