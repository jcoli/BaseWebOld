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
import org.apache.log4j.Logger;
import system.base.entities.holiday.Holiday;
import system.base.entities.holiday.HolidayRN;


/**
 * @Project BaseWeb 
 * @brief Class HolidayConverter
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date:  08/01/2015
 */
@FacesConverter(forClass = Holiday.class)
public class HolidayConverter implements Converter {
    
    final static Logger logger = Logger.getLogger(HolidayConverter.class);
    
    @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if (value != null && value.trim().length() > 0){
			Integer id = Integer.valueOf(value);
			try{
				HolidayRN holidayRN = new HolidayRN();
				return holidayRN.load(id);
			}catch (Exception e){
				throw new ConversionException("Não foi possivel encontrar a holidayType de id "+value+". "+e.getMessage());
                                
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if (value != null){
			Holiday holiday = (Holiday) value;
			return holiday.getId().toString();
		}
		return "";
	}


}
