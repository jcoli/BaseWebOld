/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package system.base.entities.paramType;

import java.util.List;
import java.util.Locale;
import system.base.entities.paramType.ParamType;

/**
 *
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 */
public interface ParamTypeDAO {
    
     public void save(ParamType param);

    public void update(ParamType param);

    public void exclude(ParamType param);

    public ParamType load(Integer id);

    public ParamType searchById(Integer id);
    
    public ParamType searchByDesc(String desc);

    public List<ParamType> listP(String paramCampo);
    
}
