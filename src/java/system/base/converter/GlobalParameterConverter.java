/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.beanutils.ConversionException;
import system.base.entities.globalParameter.GlobalParameter;
import system.base.entities.globalParameter.GlobalParameterRN;


/**
 * @Project BaseWeb 
 * @brief Class GlobalParameterConverter
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date:  18/02/2015
 */
@FacesConverter(forClass = GlobalParameter.class)
public class GlobalParameterConverter implements Converter{
    
     @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if (value != null && value.trim().length() > 0){
			Integer id = Integer.valueOf(value);
			try{
				GlobalParameterRN globalParameterRN = new GlobalParameterRN();
				return globalParameterRN.load(id);
			}catch (Exception e){
				throw new ConversionException("Não foi possivel encontrar a globalParameterType de id "+value+". "+e.getMessage());
                                
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if (value != null){
			GlobalParameter globalParameter = (GlobalParameter) value;
			return globalParameter.getId().toString();
		}
		return "";
	}

    
    
    

}
