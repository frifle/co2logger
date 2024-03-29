package de.frifle.co2logger.quarkus.boundary.simplecache;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@RefreshCache
@Priority( Interceptor.Priority.APPLICATION )
public class RefreshCacheInterceptor implements Serializable {

	private static final long serialVersionUID = -472458889950067838L;

	@Inject
	private Cache cache;

	@AroundInvoke
	public Object refreshCacheAfterCall( InvocationContext context ) throws Exception {

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
