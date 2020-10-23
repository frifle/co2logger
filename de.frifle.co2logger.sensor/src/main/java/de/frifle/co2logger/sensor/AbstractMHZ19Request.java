package de.frifle.co2logger.sensor;

public abstract class AbstractMHZ19Request<M extends AbstractMHZ19Response>{
    
    public abstract byte getCommand();

    public byte[] getData(){
        return new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
    }

    public abstract M generateResponse( byte[] bytes);
}