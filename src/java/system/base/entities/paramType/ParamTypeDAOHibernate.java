/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.entities.paramType;

import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import system.base.entities.paramType.ParamType;

/**
 * @Project ProjetosSAIBR
 * @brief Classe TipoDAOHibernate
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date 19/08/2014
 */
public class ParamTypeDAOHibernate implements ParamTypeDAO {

    private Session session;
    final static Logger logger = Logger.getLogger(ParamTypeDAOHibernate.class);

    public void setSession(Session session) {
        this.session = session;
    }

    public void save(ParamType tipo) {
        this.session.save(tipo);

    }

    public void update(ParamType tipo) {

        this.session.merge(tipo);

    }

    public void exclude(ParamType tipo) {
        this.session.delete(tipo);

    }

    public ParamType load(Integer codigo) {
        return (ParamType) this.session.get(ParamType.class, codigo);
    }

    public ParamType searchById(Integer id) {
        String hql = "select u from tipo u where u.id = :id";
        Query consulta = this.session.createQuery(hql);
        consulta.setInteger("id", id);
        return (ParamType) consulta.uniqueResult();
    }
    
    public ParamType searchByDesc(String desc) {
        Criteria criteria = this.session.createCriteria(ParamType.class);
        
        criteria.add(Restrictions.eq("description_en_US", desc));
        
        return (ParamType) criteria.uniqueResult();
    }

    public List<ParamType> listP(String tipoCampo) {

        Criteria criteria = this.session.createCriteria(ParamType.class);
       
        
        return criteria.list();
    }
}
