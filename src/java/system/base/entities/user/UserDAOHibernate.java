package system.base.entities.user;

import java.util.List;

import javax.persistence.criteria.JoinType;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import system.base.entities.userType.UserType;

public class UserDAOHibernate implements UserDAO {

    private Session session;
     final static Logger logger = Logger.getLogger(UserDAOHibernate.class);

    public void setSession(Session session) {
        this.session = session;
    }

    public void save(User user) {
        this.session.save(user);
    }

    public void update(User user) {
        if (user.getRole()== null || user.getRole().size() == 0) {
            User userRole = this.load(user.getId());
            user.setRole(userRole.getRole());
            this.session.evict(userRole);
        }
        this.session.merge(user);
    }

    public void exclude(User user) {
        this.session.delete(user);
    }

    public User load(Integer id) {
        return (User) this.session.get(User.class, id);
    }

    public User searchByLogin(String login) {

        Criteria criteria = this.session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login.trim()).ignoreCase());
        criteria.uniqueResult();
        List<User> list = criteria.list();
        User user = (User) list.get(0);
        return user;
    }

    public List<User> listU() {
        Criteria criteria = this.session.createCriteria(User.class);
        criteria.addOrder(Order.asc("name"));
        List<User> list = criteria.list();
        return criteria.list();
    }

    public List<User> listU(String name) {
        Criteria criteria = this.session.createCriteria(User.class);
        criteria.addOrder(Order.asc("name"));
        criteria.add(Restrictions.like("name", name).ignoreCase());
        return criteria.list();
    }

    public List<User> listU(String name, String area) {
        Criteria criteria = this.session.createCriteria(User.class);
        criteria.addOrder(Order.asc("name"));
        criteria.add(Restrictions.like("name", name).ignoreCase());
        criteria.add(Restrictions.like("area", area));
        return criteria.list();
    }

    public List<User> listU(String name, String area, UserType tipo) {
        Criteria criteria = this.session.createCriteria(User.class);
        criteria.addOrder(Order.asc("name"));
        criteria.add(Restrictions.like("name", name).ignoreCase());
        criteria.add(Restrictions.like("area", area));
        criteria.add(Restrictions.eq("userType", tipo));
        return criteria.list();
    }

    public List<User> listU(User user) {
        Criteria criteria = this.session.createCriteria(User.class);
        criteria.addOrder(Order.asc("name"));
        if (user != null) {
            if ((user.getName()!= null)) {
                criteria.add(Restrictions.like("name", user.getName() + "%").ignoreCase());
            }
            if (user.getArea() != null) {
                if (!(user.getArea().equals("*"))) {
                    criteria.add(Restrictions.like("area", user.getArea() + "%"));
                }
            }
            if (user.getUserType() != null) {
                criteria.add(Restrictions.eq("userType", user.getUserType()));
            }
        }
        //criteria.add(Restrictions.eq("permissao", "ROLE_ADMINISTRADOR"));

        return criteria.list();
    }

    
}
