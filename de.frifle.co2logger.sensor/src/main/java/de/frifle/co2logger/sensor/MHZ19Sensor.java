package de.frifle.co2logger.sensor;

import java.util.logging.Logger;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

public class MHZ19Sensor implements AutoCloseable {

    private static final Logger LOG = Logger.getLogger(MHZ19Sensor.class.getName());

    private static final int WAIT_FOR_PORT_AVAIL = 2000;
    private static final int WAIT_FOR_DATA_AVAIL = 2000;

    private static final byte START_BYTE = (byte)0xff;

    private final String commPortName;
    private final SerialPort port;
    private final byte sensorNumber;

    public MHZ19Sensor(String commPortName) throws MHZ19Exception {
        this.commPortName = commPortName;
        this.sensorNumber = 0x01;
        try {
            this.port = openCommPort(this.commPortName);
        } catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException e) {
            throw new MHZ19Exception(
                    String.format("oups, error opening port %s: %s", this.commPortName, e.getMessage()), e);
        }
    }

    public String getCommPortName() {
        return commPortName;
    }

    public byte getSensorNumber() {
        return sensorNumber;
    }

    public <M extends AbstractMHZ19Response> M sendRequest(AbstractMHZ19Request<M> request) throws IOException {
        LOG.log(Level.INFO,"writing bytes of request {0}", request);

        writeRequest( request );
        byte[] response = readResponse();

        return request.generateResponse( response );
    }

    public void close() {
        this.port.close();
    }

    private void writeRequest( AbstractMHZ19Request<?> request ) throws IOException {
        byte[] bytes = new byte[8];
        bytes[0] = START_BYTE;
        bytes[1] = this.sensorNumber;
        bytes[2] = request.getCommand();
        byte[] data = request.getData();
        for ( int i=3; i<data.length+3 && i<8; i++ ) {
            bytes[i] = data[i-3];
        }
        byte crc = CRCCheck.calculateCRC(bytes);
        OutputStream out = this.port.getOutputStream();
        out.write( bytes );
        out.write( crc );
        out.flush();
        log("written request ", bytes, crc);
    }

    private byte[] readResponse( ) throws IOException {
        InputStream in = this.port.getInputStream();
        byte[] bytes = new byte[8];
        for ( int i=0; i<8; i++ ){
            bytes[i] = (byte)in.read();
        }
        byte crc = (byte)in.read();
        log("read response ", bytes, crc);
        if ( bytes[0] != START_BYTE ) {
            throw new IOException("response starts not with START_BYTE 0xff");
        }
        if ( !CRCCheck.check(bytes, crc) ) {
            throw new IOException("response with wrong checksum");
        }
        return bytes;
    }

    private static SerialPort openCommPort(String portName)
            throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(portName);
        SerialPort port = portId.open(MHZ19Sensor.class.getName(), WAIT_FOR_PORT_AVAIL);
        port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        port.enableReceiveTimeout(WAIT_FOR_DATA_AVAIL);
        LOG.log(Level.INFO, "opened MH-Z19-Sensor on port {0}", portId.getName());
        return port;
    }

    private void log( String message, byte[] data, byte crc ) {
        StringBuilder sb = new StringBuilder(message);
        for ( int i=0; data!=null && i<data.length; i++) {
            sb.append( toHexString( data[i] ) );
        }
        sb.append( " crc" );
        sb.append( toHexString( crc ) );
        LOG.info( sb.toString() );
    }

    private String toHexString( byte b ) {
        String s = Integer.toHexString( 0xff & b );
        if ( s.length()==1 ){
            return " 0x0"+s;
        } else {
            return " 0x" + s;
        }
    }
}