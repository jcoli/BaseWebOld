package system.base.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import system.base.web.ContextBean;


public class ContextUtil {
    
        final static Logger logger = Logger.getLogger(ContextUtil.class);

	public static ContextBean getContextoBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);

		ContextBean contextBean = (ContextBean) session.getAttribute("contextBean");
		
		return contextBean;
	}
}