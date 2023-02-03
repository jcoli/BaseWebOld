package system.base.entities.user;

import java.util.List;
import system.base.entities.userType.UserType;


public interface UserDAO {
	
	public void save(User user);
	public void update (User user);
	public void exclude (User user);
	public User load(Integer codigo);
	public User searchByLogin(String login);
	public List<User> listU();
	public List<User> listU(String name);
	public List<User> listU(String name, String area);
	public List<User> listU(String name, String area, UserType tipo);
	public List<User> listU(User user);
	
	
}