package de.frifle.co2logger.webui;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@SimpleCache
@Priority( Interceptor.Priority.APPLICATION )
public class SimpleCacheInterceptor implements Serializable {

	private static final long CACHE_TIMEOUT_MILLIS = 5000; // 5 Sekunden

	private CacheEntry cacheEntry;

	@AroundInvoke
	public Object logMethodCall( InvocationContext context ) throws Exception {

		CacheEntry entry = getFromCache();
		if ( entry!=null ) {
			if ( entry.getException()!=null ) {
				throw entry.getException();
			} else {
				return entry.getResult();
			}
		}
		try {
			Object result = context.proceed();
			cacheResult( result );
			return result;
		} catch (Exception e) {
			cacheException( e );
			throw e;
		}
	}

	private synchronized CacheEntry getFromCache() {
		if ( cacheEntry!=null ) {
			long jetzt = System.currentTimeMillis();
			if ( jetzt - cacheEntry.getTimestamp() < CACHE_TIMEOUT_MILLIS ) {
				return cacheEntry;
			} else {
				cacheEntry = null;
			}
		}
		return null;
	}

	private synchronized void cacheResult( Object result ) {
		this.cacheEntry = new CacheEntry(result);
	}

	private synchronized void cacheException( Exception exception ) {
		this.cacheEntry = new CacheEntry(exception);
	}

	private static class CacheEntry {
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
