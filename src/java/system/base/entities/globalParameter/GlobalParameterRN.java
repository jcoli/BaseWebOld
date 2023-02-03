/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.entities.globalParameter;

import java.util.List;
import system.base.util.DAOFactory;


/**
 * @Project BaseWeb 
 * @brief Class GlobalParametersRN
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date:  14/02/2015
 */

public class GlobalParameterRN {
    
    private GlobalParameterDAO globalParameterDAO;
    
    public GlobalParameterRN() {
        this.globalParameterDAO = DAOFactory.createGlobalParameterDAO();

    }
    
    public void save(GlobalParameter globalParameter){
        Integer id = globalParameter.getId();
        if (id == null || id == 0) {
            this.globalParameterDAO.save(globalParameter);
        } else {
            this.globalParameterDAO.update(globalParameter);
        }
    }

    public void update(GlobalParameter globalParameter){
        Integer id = globalParameter.getId();
        this.globalParameterDAO.update(globalParameter);
        
    }

    public void exclude(GlobalParameter globalParameter){
        this.globalParameterDAO.exclude(globalParameter);
        
    }

    public GlobalParameter load(Integer id){
        return this.globalParameterDAO.load(id);
    }

    public GlobalParameter searchById(Integer id){
        return this.globalParameterDAO.searchById(id);
        
    }

    public GlobalParameter searchByName(String name){
        return this.globalParameterDAO.searchByName(name);

    }    
    public List<GlobalParameter> list(){
        return this.globalParameterDAO.list();
        
    }

    public List<GlobalParameter> list(String type){
        return this.globalParameterDAO.list(type);
    }

}
