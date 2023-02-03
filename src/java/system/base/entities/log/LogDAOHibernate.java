/*
 * Copyright 2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.entities.log;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import system.base.entities.log.Log;



/**
 * @Project BaseWeb
 * @brief Class LogDAOHibernate
 * @author Jeferson Coli - www.tecnocoli.com.br - jcoli@tecnocoli.com.br
 * @Date: 03/01/2015
 */

public class LogDAOHibernate implements LogDAO{
    
    private Session session;    
    final static Logger logger = Logger.getLogger(LogDAOHibernate.class);
    
    public void setSession(Session session) {
        this.session = session;
    }

    public void save(Log log) {
        this.session.save(log);

    }

    public void update(Log log) {

        this.session.merge(log);

    }

    public void exclude(Log log) {
        this.session.delete(log);

    }

    public Log load(Integer id) {
        return (Log) this.session.get(Log.class, id);
    }

    public Log searchById(Integer id) {
        String hql = "select u from log u where u.id = :id";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("id", id);
        return (Log) consulta.uniqueResult();
    }

    public List<Log> list() {

        Criteria criteria = this.session.createCriteria(Log.class);
       
        return criteria.list();
        
    }

}
