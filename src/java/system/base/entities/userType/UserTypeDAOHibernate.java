package system.base.entities.userType;

import java.util.List;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;



public class UserTypeDAOHibernate implements UserTypeDAO{
    
        final static Logger logger = Logger.getLogger(UserTypeDAOHibernate.class);
	
	private Session session;
	public void setSession(Session session){
		this.session = session;
	}
	
	public void save(UserType userType) {
		this.session.save(userType);
	}

	public void update(UserType userType) {
		this.session.update(userType);
	}

	public void exclude(UserType userType) {
		this.session.delete(userType);
	}

	public UserType load(Integer codigo) {
		return (UserType) this.session.get(UserType.class, codigo);
	}

	public UserType searchById(Integer id) {
			String hql = "select u from userType u where u.id = :id";
			Query consulta = this.session.createQuery(hql);
			consulta.setInteger("id", id);
			return (UserType) consulta.uniqueResult();
	}

	public List<UserType> listUT() {
		Criteria criteria = this.session.createCriteria(UserType.class);
		criteria.addOrder(Order.asc("description"));
		return criteria.list();
	}


}
