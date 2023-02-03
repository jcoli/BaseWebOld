/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.entities.globalParameter;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 * @Project BaseWeb 
 * @brief Class GlobalParametersDAOHibernate
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date:  14/02/2015
 */

public class GlobalParameterDAOHibernate implements GlobalParameterDAO{
    
     private Session session;    
    final static Logger logger = Logger.getLogger(GlobalParameterDAOHibernate.class);
    
     public void setSession(Session session) {
        this.session = session;
    }

    public void save(GlobalParameter globalParameter) {
        this.session.save(globalParameter);

    }

    public void update(GlobalParameter globalParameter) {

        this.session.merge(globalParameter);

    }

    public void exclude(GlobalParameter globalParameter) {
        this.session.delete(globalParameter);

    }

    public GlobalParameter load(Integer codigo) {
        return (GlobalParameter) this.session.get(GlobalParameter.class, codigo);
    }

    public GlobalParameter searchById(Integer id) {
        String hql = "select u from globalParameter u where u.id = :id";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("id", id);
        return (GlobalParameter) consulta.uniqueResult();
    }
    
    public GlobalParameter searchByName(String name){
        
         Criteria criteria = this.session.createCriteria(GlobalParameter.class);
        
        criteria.add(Restrictions.eq("name", name));
        
        return (GlobalParameter) criteria.uniqueResult();
        
    }

   
    public List<GlobalParameter> list() {

        Criteria criteria = this.session.createCriteria(GlobalParameter.class);
       
        return criteria.list();
        
    }
    
    public List<GlobalParameter> list(String type){
        
        Criteria criteria = this.session.createCriteria(GlobalParameter.class);
        
        criteria.add(Restrictions.eq("type", type));
       
        return criteria.list();
        
    }
}
