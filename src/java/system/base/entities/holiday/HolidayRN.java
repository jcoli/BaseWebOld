/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.entities.holiday;

import java.util.Date;
import java.util.List;

import system.base.util.DAOFactory;

/**
 * @Project ProjetosSAIBR 
 * @brief Classe HolidaysRN
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   31/10/2014
 */
public class HolidayRN {
    
    private HolidayDAO holidayDAO;
    

    public HolidayRN() {
        this.holidayDAO = DAOFactory.createHolidayDAO();
    }
    
    
     public void save(Holiday holiday) {
        Integer id = holiday.getId();
        if (id == null || id == 0) {
            this.holidayDAO.save(holiday);
        } else {
            this.holidayDAO.update(holiday);
        }
    }

    public void update(Holiday holiday) {
        this.holidayDAO.update(holiday);
    }
    
     public void exclude(Holiday holiday) {
        this.holidayDAO.exclude(holiday);
    }

    public Holiday load(Integer id) {
        return this.holidayDAO.load(id);
    }

    public Holiday searchById(Integer id) {
        return this.holidayDAO.searchById(id);
    }
    
    public List<Holiday> listH() {
        return this.holidayDAO.listH();
    }
    
    public List<Holiday> listH(Date dataDeriado) {
        return this.holidayDAO.listH(dataDeriado);
    }

}
