/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.entities.globalParameter;

import java.util.List;

/**
 * @Project BaseWeb
 * @brief Class GlobalParametersDAO
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date: 14/02/2015
 */
public interface GlobalParameterDAO {

    public void save(GlobalParameter globalParameter);

    public void update(GlobalParameter globalParameter);

    public void exclude(GlobalParameter globalParameter);

    public GlobalParameter load(Integer codigo);

    public GlobalParameter searchById(Integer id);

    public GlobalParameter searchByName(String name);

    public List<GlobalParameter> list();

    public List<GlobalParameter> list(String type);

}
