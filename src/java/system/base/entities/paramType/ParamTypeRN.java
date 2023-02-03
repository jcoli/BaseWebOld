/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.entities.paramType;

import java.util.List;
import java.util.Locale;
import system.base.entities.paramType.ParamType;
import system.base.entities.paramType.ParamTypeDAO;
import system.base.util.DAOFactory;

/**
 * @Project System
 * @brief Classe TipoRN
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   19/08/2014
 */
public class ParamTypeRN {
    
     private ParamTypeDAO paramType;
    
    public ParamTypeRN() {
        this.paramType = DAOFactory.createParamTypeDAO();

    }

    public ParamType load(Integer codigo) {
        return this.paramType.load(codigo);
    }

    public ParamType searchById(Integer id) {
        return this.paramType.searchById(id);
    }
    
    public ParamType searchByDesc(String desc){
        return this.paramType.searchByDesc(desc);
    }

    public void save(ParamType tipo) {
        System.out.println("aqui");
        Integer id = tipo.getId();
        if (id == null || id == 0) {
            this.paramType.save(tipo);
        } else {
            this.paramType.update(tipo);
        }
    }

    public void update(ParamType tipo) {
        Integer id = tipo.getId();
        this.paramType.update(tipo);
    }

    public void exclude(ParamType tipo) {
        this.paramType.exclude(tipo);
    }
    
    public List<ParamType> listP(String tipoCampo) {
        return this.paramType.listP(tipoCampo);
    }
    
}
