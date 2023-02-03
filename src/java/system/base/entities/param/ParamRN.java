/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.entities.param;

import java.util.List;
import java.util.Locale;
import system.base.entities.param.Param;
import system.base.entities.param.ParamDAO;
import system.base.entities.paramType.ParamType;
import system.base.util.DAOFactory;

/**
 * @Project BaseWeb 
 * @brief Classe ParamRN
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   20/12/2014
 */
public class ParamRN {
    
    private ParamDAO paramDAO;
    
    public ParamRN() {
        this.paramDAO = DAOFactory.createParamDAO();

    }

    public Param load(Integer id) {
        return this.paramDAO.load(id);
    }

    public Param searchById(Integer id) {
        return this.paramDAO.searchById(id);
    }

    public void save(Param param) {
        System.out.println("aqui");
        Integer id = param.getId();
        if (id == null || id == 0) {
            this.paramDAO.save(param);
        } else {
            this.paramDAO.update(param);
        }
    }

    public void update(Param param) {
        Integer id = param.getId();
        this.paramDAO.update(param);
    }

    public void exclude(Param param) {
        this.paramDAO.exclude(param);
    }
    
    public List<Param> listP(ParamType paramType) {
        return this.paramDAO.listP(paramType);
    }
    

}
