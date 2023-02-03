/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.entities.holiday;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * @Project ProjetosSAIBR 
 * @brief Classe FeriadosDAOHibernate
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   31/10/2014
 */
public class HolidayDAOHibernate implements HolidayDAO{

    private Session session;
    final static Logger logger = Logger.getLogger(HolidayDAOHibernate.class);

    public void setSession(Session session) {
        this.session = session;
    }
    
     public void save(Holiday holidays) {
        
         logger.info("saveHolidayNew hiber");
        this.session.save(holidays);
    }

    public void update(Holiday holidays) {
        this.session.update(holidays);
    }
    
     public void exclude(Holiday holidays) {
        session.clear(); 
        this.session.delete(holidays);
    }

    public Holiday load(Integer id) {
        return (Holiday) this.session.get(Holiday.class, id);
    }

    public Holiday searchById(Integer id) {
        String hql = "select a from holiday a where a.id = :id";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("id", id);
        return (Holiday) consulta.uniqueResult();
    }
    
    public List<Holiday> listH() {
        Criteria criteria = this.session.createCriteria(Holiday.class);
        criteria.addOrder(Order.asc("holidayDate"));
        return criteria.list();
    }
    
     public List<Holiday> listH(Date holidayDate) {
        Criteria criteria = this.session.createCriteria(Holiday.class);
        criteria.add(Restrictions.eq("holidayDate", holidayDate));
        return criteria.list();
        
     }
    
}
