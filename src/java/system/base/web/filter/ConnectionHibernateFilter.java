package system.base.web.filter;

import javax.servlet.*;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import system.base.util.HibernateUtil;

public class ConnectionHibernateFilter implements Filter {
    
        final static Logger logger = Logger.getLogger(ConnectionHibernateFilter.class);
	
	private SessionFactory sf;
	
	public void init(FilterConfig config) throws ServletException{
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException {
		try{
			this.sf.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			this.sf.getCurrentSession().getTransaction().commit();
			this.sf.getCurrentSession().close();			
		}catch (Throwable ex) {
			try{
				if (this.sf.getCurrentSession().getTransaction().isActive()){
					this.sf.getCurrentSession().getTransaction().rollback();
				}
			}catch (Throwable t){
				t.printStackTrace();
                                logger.error(ex);
			}
			throw new ServletException(ex);
		}
	}
	
	public void destroy(){}
	

}
