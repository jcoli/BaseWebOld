/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.entities.param;

import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import system.base.entities.param.Param;
import system.base.entities.param.Param;
import system.base.entities.paramType.ParamType;
import system.base.util.DAOFactory;

/**
 * @Project BaseWeb 
 * @brief Classe ParamDAOHibernate
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   20/12/2014
 */
public class ParamDAOHibernate implements ParamDAO{
       
     private Session session;
     final static Logger logger = Logger.getLogger(ParamDAOHibernate.class);

    public void setSession(Session session) {
        this.session = session;
    }

    public void save(Param param) {
        this.session.save(param);

    }

    public void update(Param param) {

        this.session.merge(param);

    }

    public void exclude(Param param) {
        this.session.delete(param);

    }

    public Param load(Integer codigo) {
        return (Param) this.session.get(Param.class, codigo);
    }

    public Param searchById(Integer id) {
        String hql = "select u from param u where u.id = :id";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("id", id);
        return (Param) consulta.uniqueResult();
    }

    public List<Param> listP(ParamType paramType) {

        Criteria criteria = this.session.createCriteria(Param.class);
       
         
       
        if (paramType != null){
            if(paramType.getId()>0){
            criteria.add(Restrictions.eq("paramType", paramType));
        }}
        
        
        return criteria.list();
        
    }

}
