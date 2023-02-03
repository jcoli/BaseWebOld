package system.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;

import org.apache.commons.beanutils.ConversionException;
import org.apache.log4j.Logger;

import system.base.entities.userType.UserType;
import system.base.entities.userType.UserTypeRN;

@FacesConverter(forClass = UserType.class)

public class UserTypeConverter implements Converter{
    
        final static Logger logger = Logger.getLogger(UserTypeConverter.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if (value != null && value.trim().length() > 0){
			Integer id = Integer.valueOf(value);
			try{
				UserTypeRN userTypeRN = new UserTypeRN();
				return userTypeRN.load(id);
			}catch (Exception e){
				throw new ConversionException("Nãoo foi possivel encontrar o tipo de user de id "+value+". "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if (value != null){
			UserType userType = (UserType) value;
			return userType.getId().toString();
		}
		return "";
	}
	
}
