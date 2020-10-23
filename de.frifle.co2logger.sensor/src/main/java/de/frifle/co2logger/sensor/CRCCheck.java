package de.frifle.co2logger.sensor;

public class CRCCheck {

    public static boolean check( byte[] bytes, byte sum ) {

        if ( bytes == null ) {
            return false;
        }

        byte crc = calculateCRC( bytes );
        return crc == sum;
    }

    static byte calculateCRC( byte[] bytes ) {
        if ( bytes == null ) {
            throw new IllegalArgumentException( "bytes must not be null" );
        }
        int crc = 0;
        for ( int i=0; i<bytes.length; i++ ) {
            crc += bytes[i];
        }
        return (byte)( 0xff & ( ~crc ) );
    }

}