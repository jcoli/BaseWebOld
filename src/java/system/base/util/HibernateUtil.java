package system.base.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory	sessionFactory	= buildSessionFactory();
        final static Logger logger = Logger.getLogger(HibernateUtil.class);

	private static SessionFactory buildSessionFactory() {
		try {
			//AnnotationConfiguration cfg = new AnnotationConfiguration();
			//cfg.configure("hibernate.cfg.xml");
			//return cfg.buildSessionFactory();
			Configuration config = new AnnotationConfiguration().configure();
			config.configure("hibernate.cfg.xml");
			
			return config.configure().buildSessionFactory();
		} catch (Throwable e) {
			logger.error("Criação inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
