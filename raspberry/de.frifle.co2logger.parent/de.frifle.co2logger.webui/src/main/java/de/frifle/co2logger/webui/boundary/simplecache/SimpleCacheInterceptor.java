package de.frifle.co2logger.webui.boundary.simplecache;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import de.frifle.co2logger.webui.boundary.simplecache.Cache.CacheEntry;

@Interceptor
@SimpleCache
@Priority( Interceptor.Priority.APPLICATION )
public class SimpleCacheInterceptor implements Serializable {

	private static final long serialVersionUID = -472458889950067838L;

	@Inject
	private Cache cache;

	@AroundInvoke
	public Object cachedMethodCall( InvocationContext context ) throws Exception {

		CacheEntry entry = cache.getFromCache();
		if ( entry!=null ) {
			if ( entry.getException()!=null ) {
				throw entry.getException();
			} else {
				return entry.getResult();
			}
		}
		try {
			Object result = context.proceed();
			cache.cacheResult( result );
			return result;
		} catch (Exception e) {
			cache.cacheException( e );
			throw e;
		}
	}


}
