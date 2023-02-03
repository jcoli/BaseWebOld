/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.base.entities.holiday;

import java.util.Date;
import java.util.List;
import system.base.entities.holiday.Holiday;

/**
 *
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 */
public interface HolidayDAO {
    
    public void save(Holiday holidays);

    public void update(Holiday holidays);

    public void exclude(Holiday holidays);

    public Holiday load(Integer codigo);

    public Holiday searchById(Integer id);

    public List<Holiday> listH();
    
     public List<Holiday> listH(Date dataDeriado);
    
    
}
