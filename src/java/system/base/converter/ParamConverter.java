/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.beanutils.ConversionException;
import org.apache.log4j.Logger;
import system.base.entities.param.Param;
import system.base.entities.param.ParamRN;

/**
 * @Project BaseWeb 
 * @brief Classe ParamConverter
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   20/12/2014
 */
@FacesConverter(forClass = Param.class)
public class ParamConverter implements Converter{
    
    final static Logger logger = Logger.getLogger(ParamConverter.class);
    
    @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if (value != null && value.trim().length() > 0){
			Integer id = Integer.valueOf(value);
			try{
				ParamRN paramRN = new ParamRN();
				return paramRN.load(id);
			}catch (Exception e){
				throw new ConversionException("Não foi possivel encontrar a paramType de id "+value+". "+e.getMessage());
                                
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if (value != null){
			Param param = (Param) value;
			return param.getId().toString();
		}
		return "";
	}


}
