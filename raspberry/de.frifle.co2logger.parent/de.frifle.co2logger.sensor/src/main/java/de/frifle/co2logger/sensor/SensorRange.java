package de.frifle.co2logger.sensor;

public enum SensorRange {
	TWO_THOUSAND(2000),
	THREE_THOUSAND(3000),
	FIVE_THOUSAND(5000),
	TEN_THOUSAND(10000);

	private final int highValue;
	private final int lowValue;

	private SensorRange( int highValue ) {
		this.highValue = highValue;
		this.lowValue = 0;
	}

	public int getHighValue() {
		return highValue;
	}

	public int getLowValue() {
		return lowValue;
	}

	static SensorRange valueOf( int lowValue, int highValue ) {
		if ( lowValue == 0 ) {
			if ( highValue == 2000 ) {
				return SensorRange.TWO_THOUSAND;
			}
			if ( highValue == 3000 ) {
				return SensorRange.THREE_THOUSAND;
			}
			if ( highValue == 5000 ) {
				return SensorRange.FIVE_THOUSAND;
			}
			if ( highValue == 10000 ) {
				return SensorRange.TEN_THOUSAND;
			}
		}
		throw new IllegalArgumentException( String.format("there is no Detectorrange with lowValue %d and highValue %d", lowValue, highValue) );
	}
}
