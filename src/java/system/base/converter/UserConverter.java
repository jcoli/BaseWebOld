package system.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.beanutils.ConversionException;
import org.apache.log4j.Logger;

import system.base.entities.user.User;
import system.base.entities.user.UserRN;


@FacesConverter(forClass = User.class)
//@FacesConverter(value="userConverter", forClass = User.class)
public class UserConverter implements Converter {
    
        final static Logger logger = Logger.getLogger(UserConverter.class);
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if (value != null && value.trim().length() > 0){
			Integer id = Integer.valueOf(value);
			
			try{
				UserRN userRN = new UserRN();
				return userRN.load(id);
			}catch (Exception e){
				throw new ConversionException("Não foi possivel encontrar a user de id "+value+". "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		
		
		if (value != null){
		
			User user = (User) value;
			
			return user.getId().toString();
			
		}
		return "";
	}

}
