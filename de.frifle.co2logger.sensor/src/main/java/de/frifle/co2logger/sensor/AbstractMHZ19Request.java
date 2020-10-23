package de.frifle.co2logger.sensor;

public abstract class AbstractMHZ19Request<M extends AbstractMHZ19Response>{

	private static final int DEFAULT_NUMBER_OF_RESPONSE_BYTES_WO_CRC = 8;

    public abstract byte getCommand();

    public byte[] getData(){
        return new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
    }

    public int getNumberOfResponseBytes() {
    	return DEFAULT_NUMBER_OF_RESPONSE_BYTES_WO_CRC;
    }

    public abstract M generateResponse( byte[] bytes);
}