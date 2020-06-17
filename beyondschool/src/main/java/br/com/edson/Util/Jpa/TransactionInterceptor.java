package br.com.edson.Util.Jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


@Transactional
@Interceptor
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;
	
	@AroundInvoke
	public Object invoke (InvocationContext context) throws Exception {
		EntityTransaction et = em.getTransaction();
		boolean criador = false;
		
		try {
			if (!et.isActive()) {
				et.begin();
				et.rollback();
				et.begin();
				criador = true;
			}
			return context.proceed();
		}catch (Exception e) {
			if (et != null && criador) {
				et.rollback();
			}
			throw e;
		}finally {
			if (et != null && et.isActive() && criador) {
				et.commit();
			}
		}
	}

}
