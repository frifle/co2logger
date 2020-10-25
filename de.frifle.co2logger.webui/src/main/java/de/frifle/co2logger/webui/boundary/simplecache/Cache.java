package de.frifle.co2logger.webui.boundary.simplecache;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Cache {
	private static final long CACHE_TIMEOUT_MILLIS = 5000; // 5 Sekunden

	private CacheEntry cacheEntry;

	public synchronized CacheEntry getFromCache() {
		if ( cacheEntry!=null ) {
			if ( !isExpired( cacheEntry ) ) {
				return cacheEntry;
			} else {
				clearCache();
			}
		}
		return null;
	}

	public synchronized void cacheResult( Object result ) {
		this.cacheEntry = new CacheEntry(result);
	}

	public synchronized void cacheException( Exception exception ) {
		this.cacheEntry = new CacheEntry(exception);
	}

	public synchronized void clearCache() {
		this.cacheEntry = null;
	}

	private boolean isExpired(CacheEntry entry) {
		long now = System.currentTimeMillis();
		return now - cacheEntry.getTimestamp() > CACHE_TIMEOUT_MILLIS;
	}

	public static class CacheEntry {
		private final Object result;
		private final Exception exception;
		private final long timestamp;

		public CacheEntry(Object result) {
			this( result, null );
		}
		public CacheEntry(Exception exception) {
			this(null, exception);
		}
		private CacheEntry(Object result, Exception exception ) {
			this.timestamp = System.currentTimeMillis();
			this.result = result;
			this.exception = exception;
		}

		public Exception getException() {
			return exception;
		}

		public Object getResult() {
			return result;
		}

		public long getTimestamp() {
			return timestamp;
		}

	}
}
