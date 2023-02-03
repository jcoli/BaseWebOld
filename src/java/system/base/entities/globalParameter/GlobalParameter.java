/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.entities.globalParameter;

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
 * @brief Class GlobalParameters
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date: 14/02/2015
 */
@Entity
@Table(name = "globalparameter")
public class GlobalParameter implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateModi;

    @ManyToOne
    private User loggedUser;
    
    @Column(nullable = true, length = 30)
    private String name;

    @Column(nullable = true, length = 100)
    private String description;

    @Column(nullable = true, length = 20)
    private String valueString;

    @Column(nullable = true)
    private Integer valueInteger;

    @Column(nullable = true)
    private Float valueFloat;

    @Column(nullable = true)
    private Boolean valueBolean;
    
    @Column(nullable = true)
    private Double valueDouble;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date valueTimeStamp;
    

    @Column(nullable = true, length = 20)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateModi() {
        return dateModi;
    }

    public void setDateModi(Date dateModi) {
        this.dateModi = dateModi;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public Integer getValueInteger() {
        return valueInteger;
    }

    public void setValueInteger(Integer valueInteger) {
        this.valueInteger = valueInteger;
    }

    public Float getValueFloat() {
        return valueFloat;
    }

    public void setValueFloat(Float valueFloat) {
        this.valueFloat = valueFloat;
    }

    public Boolean getValueBolean() {
        return valueBolean;
    }

    public void setValueBolean(Boolean valueBolean) {
        this.valueBolean = valueBolean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValueDouble() {
        return valueDouble;
    }

    public void setValueDouble(Double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public Date getValueTimeStamp() {
        return valueTimeStamp;
    }

    public void setValueTimeStamp(Date valueTimeStamp) {
        this.valueTimeStamp = valueTimeStamp;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.dateModi);
        hash = 53 * hash + Objects.hashCode(this.loggedUser);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.valueString);
        hash = 53 * hash + Objects.hashCode(this.valueInteger);
        hash = 53 * hash + Objects.hashCode(this.valueFloat);
        hash = 53 * hash + Objects.hashCode(this.valueBolean);
        hash = 53 * hash + Objects.hashCode(this.type);
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
        final GlobalParameter other = (GlobalParameter) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateModi, other.dateModi)) {
            return false;
        }
        if (!Objects.equals(this.loggedUser, other.loggedUser)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.valueString, other.valueString)) {
            return false;
        }
        if (!Objects.equals(this.valueInteger, other.valueInteger)) {
            return false;
        }
        if (!Objects.equals(this.valueFloat, other.valueFloat)) {
            return false;
        }
        if (!Objects.equals(this.valueBolean, other.valueBolean)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GlobalParameters{" + "id=" + id + ", dateModi=" + dateModi + ", loggedUser=" + loggedUser + ", name=" + name + ", description=" + description + ", valueString=" + valueString + ", valueInteger=" + valueInteger + ", valueFloat=" + valueFloat + ", valueBolean=" + valueBolean + ", type=" + type + '}';
    }

   

}
