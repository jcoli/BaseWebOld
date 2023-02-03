package system.base.util;

import org.apache.log4j.Logger;
import system.base.entities.globalParameter.GlobalParameterDAO;
import system.base.entities.globalParameter.GlobalParameterDAOHibernate;
import system.base.entities.userType.UserTypeDAO;
import system.base.entities.userType.UserTypeDAOHibernate;
import system.base.entities.user.UserDAOHibernate;
import system.base.entities.user.UserDAO;
import system.base.entities.holiday.HolidayDAO;
import system.base.entities.holiday.HolidayDAOHibernate;
import system.base.entities.log.LogDAO;
import system.base.entities.log.LogDAOHibernate;
import system.base.entities.param.ParamDAO;
import system.base.entities.param.ParamDAOHibernate;
import system.base.entities.paramType.ParamTypeDAO;
import system.base.entities.paramType.ParamTypeDAOHibernate;

public class DAOFactory {
    
    final static Logger logger = Logger.getLogger(DAOFactory.class);

    public static UserDAO createUsuarioDAO() {
        UserDAOHibernate userDAO = new UserDAOHibernate();
        userDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return userDAO;
    }

    public static UserTypeDAO createuserTypeDAO() {
        UserTypeDAOHibernate userTypeDAO = new UserTypeDAOHibernate();
        userTypeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return userTypeDAO;
    }

    public static ParamTypeDAO createParamTypeDAO() {
        ParamTypeDAOHibernate paramTypeDAO = new ParamTypeDAOHibernate();
        paramTypeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return paramTypeDAO;
    }
    
     public static HolidayDAO createHolidayDAO() {
        HolidayDAOHibernate holidayDAO = new HolidayDAOHibernate();
        holidayDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return holidayDAO;
    }
     
      public static ParamDAO createParamDAO() {
        ParamDAOHibernate paramDAO = new ParamDAOHibernate();
        paramDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return paramDAO;
    }
      
      public static LogDAO createLogDAO() {
        LogDAOHibernate logDAO = new LogDAOHibernate();
        logDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return logDAO;
    }
      
      
      public static GlobalParameterDAO createGlobalParameterDAO() {
        GlobalParameterDAOHibernate globalParameterDAO = new GlobalParameterDAOHibernate();
        globalParameterDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return globalParameterDAO;
    }
}
