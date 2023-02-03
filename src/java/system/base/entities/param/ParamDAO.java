/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.base.entities.param;

import java.util.List;
import java.util.Locale;
import system.base.entities.param.Param;
import system.base.entities.paramType.ParamType;

/**
 *
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 */
public interface ParamDAO {
    
    public void save(Param tipo);

    public void update(Param tipo);

    public void exclude(Param tipo);

    public Param load(Integer codigo);

    public Param searchById(Integer id);

    public List<Param> listP(ParamType tipoCampo);
    
    
}
