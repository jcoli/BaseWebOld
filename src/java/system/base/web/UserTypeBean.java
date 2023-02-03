package system.base.web;

import java.io.Serializable;
import system.base.entities.userType.UserTypeRN;
import system.base.entities.userType.UserType;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

import org.springframework.util.DigestUtils;

import system.base.entities.user.UserRN;

@ManagedBean(name = "userTypeBean")
@RequestScoped
public class UserTypeBean implements Serializable{
    
        final static Logger logger = Logger.getLogger(UserTypeBean.class);
	
	private UserType userType = new UserType();
	private List<UserType> listType;
	private String destinoSalvar = "/admin/user/userType";
	private UserType seluserType = new UserType();

	public String novo() {
		this.userType = new UserType();
		return "userType";
	}
	
	public String save() {
		FacesContext context = FacesContext.getCurrentInstance();
		UserTypeRN userTypeRN = new UserTypeRN();
		userTypeRN.save(this.userType);
		this.userType = null;
		return "/admin/user/userType";
	}
	
	public List<UserType> getListType() {
		if (this.listType == null) {
			UserTypeRN userTypeRN = new UserTypeRN();
			this.listType = userTypeRN.listUT();
		}
		return this.listType;
	}
	
	public void edit(){
		this.userType = this.seluserType;
	}
	
	public String exclude() {
		this.userType = this.seluserType;
		UserTypeRN userTypeRN = new UserTypeRN();
		userTypeRN.exclude(this.userType);
		this.listType = null;
		return null;
	}
	
	public String cancel() {
		listType = null;
		this.userType = null;
		return "/admin/user/userType";
	}

	public UserType getuserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setListType(List<UserType> list) {
		this.listType = list;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public UserType getSeluserType() {
		return seluserType;
	}

	public void setSeluserType(UserType seluserType) {
		this.seluserType = seluserType;
	}

	
	
}

