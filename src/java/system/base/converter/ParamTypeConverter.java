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
import system.base.entities.paramType.ParamType;
import system.base.entities.paramType.ParamTypeRN;
import system.base.entities.paramType.ParamType;

/**
 * @Project ProjetosSAIBR 
 * @brief Classe paramTypeConverter
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   19/08/2014
 */
@FacesConverter(forClass = ParamType.class)
public class ParamTypeConverter implements Converter{
    
    final static Logger logger = Logger.getLogger(ParamTypeConverter.class);
    
    @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if (value != null && value.trim().length() > 0){
			Integer id = Integer.valueOf(value);
			try{
				ParamTypeRN paramTypeRN = new ParamTypeRN();
				return paramTypeRN.load(id);
			}catch (Exception e){
				throw new ConversionException("Não foi possivel encontrar a paramType de id "+value+". "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if (value != null){
			ParamType paramType = (ParamType) value;
			return paramType.getId().toString();
		}
		return "";
	}


}
