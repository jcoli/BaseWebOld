/*
 * Copyright 2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.entities.log;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import system.base.entities.user.User;


/**
 * @Project BaseWeb
 * @brief Class Log
 * @author Jeferson Coli - www.tecnocoli.com.br - jcoli@tecnocoli.com.br
 * @Date: 03/01/2015
 */
@Entity
@Table(name = "log")
public class Log implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = true, length = 100)
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dataLog;
    
    @ManyToOne
    private User loggedUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDataLog() {
        return dataLog;
    }

    public void setDataLog(Date dataLog) {
        this.dataLog = dataLog;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.dataLog);
        hash = 97 * hash + Objects.hashCode(this.loggedUser);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.dataLog, other.dataLog)) {
            return false;
        }
        if (!Objects.equals(this.loggedUser, other.loggedUser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Log{" + "id=" + id + ", description=" + description + ", dataLog=" + dataLog + ", loggedUser=" + loggedUser + '}';
    }
    
    
    
    
}
