package system.base.entities.userType;

import java.util.List;

import system.base.util.DAOFactory;

public class UserTypeRN {
	
private UserTypeDAO userTypeDAO;
	
	public UserTypeRN(){
		this.userTypeDAO = DAOFactory.createuserTypeDAO();
	}
	
	public UserType load(Integer codigo){
		return this.userTypeDAO.load(codigo);
	}
	
	public UserType searchById(Integer id){
		return this.userTypeDAO.searchById(id);
	}
	
	public void save(UserType userType){
		Integer id = userType.getId();
		if (id == null || id==0){
			this.userTypeDAO.save(userType);
		}else{
			this.userTypeDAO.update(userType);
		}
	}
	
	public void exclude(UserType userType){
		this.userTypeDAO.exclude(userType);
	}
	
	public List<UserType> listUT(){
		return this.userTypeDAO.listUT();
	}
	
	
}
