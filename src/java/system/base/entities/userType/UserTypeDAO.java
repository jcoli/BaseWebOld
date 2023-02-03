package system.base.entities.userType;

import java.util.List;



public interface UserTypeDAO {
	
	public void save(UserType userType);
	public void update (UserType userType);
	public void exclude (UserType userType);
	public UserType load(Integer id);
	public UserType searchById(Integer id);
	public List<UserType> listUT();
	

}
