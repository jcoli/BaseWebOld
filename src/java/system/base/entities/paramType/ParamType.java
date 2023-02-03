/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.entities.paramType;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Project ProjetosSAIBR 
 * @brief Classe Tipo
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   19/08/2014
 */
@Entity
@Table(name = "paramType")
public class ParamType implements Serializable{
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = true, length = 1000)
    private String longDescription_en_US;
    
    @Column(nullable = true, length = 50)
    private String description_en_US;
    
    @Column(nullable = true, length = 50)
    private String type_en_US;
    
    @Column(nullable = true, length = 50)
    private String type_pt_BR;
    
    @Column(nullable = true, length = 50)
    private String type_es_ES;
    
    @Column(nullable = true, length = 1000)
    private String longDescription_pt_BR;
    
    @Column(nullable = true, length = 50)
    private String description_pt_BR;
    
    @Column(nullable = true, length = 1000)
    private String longDescription_es_ES;
    
    @Column(nullable = true, length = 50)
    private String description_es_ES;
    

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

    public String getType_en_US() {
        return type_en_US;
    }

    public void setType_en_US(String type_en_US) {
        this.type_en_US = type_en_US;
    }

    public String getType_pt_BR() {
        return type_pt_BR;
    }

    public void setType_pt_BR(String type_pt_BR) {
        this.type_pt_BR = type_pt_BR;
    }

    public String getType_es_ES() {
        return type_es_ES;
    }

    public void setType_es_ES(String type_es_ES) {
        this.type_es_ES = type_es_ES;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.longDescription_en_US);
        hash = 31 * hash + Objects.hashCode(this.description_en_US);
        hash = 31 * hash + Objects.hashCode(this.type_en_US);
        hash = 31 * hash + Objects.hashCode(this.type_pt_BR);
        hash = 31 * hash + Objects.hashCode(this.type_es_ES);
        hash = 31 * hash + Objects.hashCode(this.longDescription_pt_BR);
        hash = 31 * hash + Objects.hashCode(this.description_pt_BR);
        hash = 31 * hash + Objects.hashCode(this.longDescription_es_ES);
        hash = 31 * hash + Objects.hashCode(this.description_es_ES);
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
        final ParamType other = (ParamType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.longDescription_en_US, other.longDescription_en_US)) {
            return false;
        }
        if (!Objects.equals(this.description_en_US, other.description_en_US)) {
            return false;
        }
        if (!Objects.equals(this.type_en_US, other.type_en_US)) {
            return false;
        }
        if (!Objects.equals(this.type_pt_BR, other.type_pt_BR)) {
            return false;
        }
        if (!Objects.equals(this.type_es_ES, other.type_es_ES)) {
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
        return true;
    }

    @Override
    public String toString() {
        return "ParamType{" + "id=" + id + ", longDescription_en_US=" + longDescription_en_US + ", description_en_US=" + description_en_US + ", type_en_US=" + type_en_US + ", type_pt_BR=" + type_pt_BR + ", type_es_ES=" + type_es_ES + ", longDescription_pt_BR=" + longDescription_pt_BR + ", description_pt_BR=" + description_pt_BR + ", longDescription_es_ES=" + longDescription_es_ES + ", description_es_ES=" + description_es_ES + '}';
    }

   

    

}
