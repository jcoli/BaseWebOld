/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.entities.param;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import system.base.entities.paramType.ParamType;

/**
 * @Project BaseWeb 
 * @brief Classe Param
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   20/12/2014
 */
@Entity
@Table(name = "param")
public class Param implements Serializable{
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = true, length = 1000)
    private String longDescription_en_US;
    
    @Column(nullable = true, length = 50)
    private String description_en_US;
    
    @Column(nullable = true, length = 1000)
    private String longDescription_pt_BR;
    
    @Column(nullable = true, length = 50)
    private String description_pt_BR;
    
    @Column(nullable = true, length = 1000)
    private String longDescription_es_ES;
    
    @Column(nullable = true, length = 50)
    private String description_es_ES;
    
    @ManyToOne
    private ParamType paramType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongDescription_en_US() {
        return longDescription_en_US;
    }

    public void setLongDescription_en_US(String longDescription_en_US) {
        this.longDescription_en_US = longDescription_en_US;
    }

    public String getDescription_en_US() {
        return description_en_US;
    }

    public void setDescription_en_US(String description_en_US) {
        this.description_en_US = description_en_US;
    }

    public String getLongDescription_pt_BR() {
        return longDescription_pt_BR;
    }

    public void setLongDescription_pt_BR(String longDescription_pt_BR) {
        this.longDescription_pt_BR = longDescription_pt_BR;
    }

    public String getDescription_pt_BR() {
        return description_pt_BR;
    }

    public void setDescription_pt_BR(String description_pt_BR) {
        this.description_pt_BR = description_pt_BR;
    }

    public String getLongDescription_es_ES() {
        return longDescription_es_ES;
    }

    public void setLongDescription_es_ES(String longDescription_es_ES) {
        this.longDescription_es_ES = longDescription_es_ES;
    }

    public String getDescription_es_ES() {
        return description_es_ES;
    }

    public void setDescription_es_ES(String description_es_ES) {
        this.description_es_ES = description_es_ES;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.longDescription_en_US);
        hash = 97 * hash + Objects.hashCode(this.description_en_US);
        hash = 97 * hash + Objects.hashCode(this.longDescription_pt_BR);
        hash = 97 * hash + Objects.hashCode(this.description_pt_BR);
        hash = 97 * hash + Objects.hashCode(this.longDescription_es_ES);
        hash = 97 * hash + Objects.hashCode(this.description_es_ES);
        hash = 97 * hash + Objects.hashCode(this.paramType);
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
        final Param other = (Param) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.longDescription_en_US, other.longDescription_en_US)) {
            return false;
        }
        if (!Objects.equals(this.description_en_US, other.description_en_US)) {
            return false;
        }
        if (!Objects.equals(this.longDescription_pt_BR, other.longDescription_pt_BR)) {
            return false;
        }
        if (!Objects.equals(this.description_pt_BR, other.description_pt_BR)) {
            return false;
        }
        if (!Objects.equals(this.longDescription_es_ES, other.longDescription_es_ES)) {
            return false;
        }
        if (!Objects.equals(this.description_es_ES, other.description_es_ES)) {
            return false;
        }
        if (!Objects.equals(this.paramType, other.paramType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Param{" + "description_en_US=" + description_en_US + ", description_pt_BR=" + description_pt_BR +  ", description_es_ES=" + description_es_ES ;
    }

    
    
    

}
