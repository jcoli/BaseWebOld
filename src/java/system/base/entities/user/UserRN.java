package system.base.entities.user;

import java.util.List;
import system.base.entities.userType.UserType;
import system.base.util.DAOFactory;

public class UserRN {
	
	private UserDAO userDAO;
	
	public UserRN(){
		this.userDAO = DAOFactory.createUsuarioDAO();
	}
	
	public User load(Integer id){
		return this.userDAO.load(id);
	}
	
	public User searchByLogin(String login){
		return this.userDAO.searchByLogin(login);
	}
	
	public void save(User user){
		Integer id = user.getId();
		if (id == null || id==0){
			user.setActive(true);
			user.getRole().add("ROLE_USUARIO");
			this.userDAO.save(user);
		}else{
			this.userDAO.update(user);
	}
		
	}
	
	public void exclude(User user){
		
		this.userDAO.exclude(user);
	}
	
	public List<User> listU(){
		return this.userDAO.listU();
	}
	
	public List<User> listU(String nome){
		return this.userDAO.listU(nome);
	}
	
	public List<User> listU(User user){
		return this.userDAO.listU(user);
	}
	
	public List<User> listU(String nome, String area){
		return this.userDAO.listU(nome, area);
	}
	
	public List<User> listU(String nome, String area, UserType tipo){
		return this.userDAO.listU(nome, area, tipo);
	}
	
	
}
