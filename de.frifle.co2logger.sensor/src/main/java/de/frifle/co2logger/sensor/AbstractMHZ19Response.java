package de.frifle.co2logger.sensor;

public abstract class AbstractMHZ19Response {
    private final byte command;
    private final byte[] data;

    public AbstractMHZ19Response( byte[] data ){
        this.command = data[0];
        this.data = data;
    }

    public byte getCommand() {
        return command;
    }

    public byte[] getData() {
        return data;
    }

}