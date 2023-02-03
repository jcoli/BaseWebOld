/*
 * Copyright 2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.entities.log;

import java.util.List;
import system.base.entities.log.Log;

import system.base.util.DAOFactory;


/**
 * @Project BaseWeb
 * @brief Class LogRN
 * @author Jeferson Coli - www.tecnocoli.com.br - jcoli@tecnocoli.com.br
 * @Date: 03/01/2015
 */

public class LogRN {
    
    private LogDAO logDAO;
    
    public LogRN() {
        this.logDAO = DAOFactory.createLogDAO();

    }

    public Log load(Integer id) {
        return this.logDAO.load(id);
    }

    public Log searchById(Integer id) {
        return this.logDAO.searchById(id);
    }

    public void save(Log log) {
          Integer id = log.getId();
        if (id == null || id == 0) {
            this.logDAO.save(log);
        } else {
            this.logDAO.update(log);
        }
    }

    public void update(Log log) {
        Integer id = log.getId();
        this.logDAO.update(log);
    }

    public void exclude(Log log) {
        this.logDAO.exclude(log);
    }
    
    public List<Log> list() {
        return this.logDAO.list();
    }
    

}
