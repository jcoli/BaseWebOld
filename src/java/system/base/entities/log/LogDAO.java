/*
 * Copyright 2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.entities.log;

import java.util.List;
import system.base.entities.log.Log;



/**
 * @Project BaseWeb
 * @brief Interface LogDAO
 * @author Jeferson Coli - www.tecnocoli.com.br - jcoli@tecnocoli.com.br
 * @Date: 03/01/2015
 */

public interface LogDAO {
    
     public void save(Log log);

    public void update(Log log);

    public void exclude(Log log);

    public Log load(Integer id);

    public Log searchById(Integer id);

    public List<Log> list();

}
