/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 * @Project BaseWeb 
 * @brief Classe MessageUtil
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   18/12/2014
 */

public class MessageUtil {
    
    private static final String PACKAGE_LANGUAGE_MESSAGES = "system.base.languages";
    final static Logger logger = Logger.getLogger(MessageUtil.class);

	public static String getMensagem(String propriedade) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
                
		return bundle.getString(propriedade);
	}
	
	public static String getMensagem(String propriedade, Object...parametros) {
		String mensagem = getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
                
		return formatter.format(parametros);
	}
	
	public static String getMensagem(Locale locale, String propriedade) {
		ResourceBundle bundle = ResourceBundle.getBundle(MessageUtil.PACKAGE_LANGUAGE_MESSAGES, locale);
 		return bundle.getString(propriedade);
	}

	public static String getMensagem(Locale locale, String propriedade, Object... parametros) {
		String mensagem = getMensagem(locale, propriedade);
 		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}


}
