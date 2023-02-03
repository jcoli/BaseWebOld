package system.base.entities.user;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import system.base.entities.userType.UserType;

@Entity
@Table(name = "sysuser")
public class User implements Serializable {

    private static final long serialVersionUID = -2276215792742363279L;

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, unique = true, length = 15)
    private String login;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = true, length = 15)
    private String cellphone;
    @Column(nullable = false, length = 10)
    private String language;
    
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = true)
    private boolean accountNonExpired;
    @Column(nullable = true)
    private boolean accountNonLocked;
    @Column(nullable = true)
    private boolean credentialsNonExpired;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateAccountExpired;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateCredentialsExpired;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateLastFailAttempts;
    
    
    @Column(nullable = true, length = 20)
    private String function;
    @Column(nullable = true, length = 20)
    private String area;
    
    private Integer attempts;
    private Float rateHour;
    private Float rateHourEst;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtype", nullable = true)
    private UserType userType;

    @ElementCollection(targetClass = String.class)
    @JoinTable(
            name = "user_role",
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"sysuser", "role"})},
            joinColumns = @JoinColumn(name = "sysuser"))
    @Column(name = "role", length = 50)
    private Set<String> role = new HashSet<String>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Float getRateHour() {
        return rateHour;
    }

    public void setRateHour(Float rateHour) {
        this.rateHour = rateHour;
    }

    public Float getRateHourEst() {
        return rateHourEst;
    }

    public void setRateHourEst(Float rateHourEst) {
        this.rateHourEst = rateHourEst;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Date getDateAccountExpired() {
        return dateAccountExpired;
    }

    public void setDateAccountExpired(Date dateAccountExpired) {
        this.dateAccountExpired = dateAccountExpired;
    }

    public Date getDateCredentialsExpired() {
        return dateCredentialsExpired;
    }

    public void setDateCredentialsExpired(Date dateCredentialsExpired) {
        this.dateCredentialsExpired = dateCredentialsExpired;
    }

    public Date getDateLastFailAttempts() {
        return dateLastFailAttempts;
    }

    public void setDateLastFailAttempts(Date dateLastFailAttempts) {
        this.dateLastFailAttempts = dateLastFailAttempts;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.cellphone);
        hash = 97 * hash + Objects.hashCode(this.language);
        hash = 97 * hash + (this.active ? 1 : 0);
        hash = 97 * hash + (this.accountNonExpired ? 1 : 0);
        hash = 97 * hash + (this.accountNonLocked ? 1 : 0);
        hash = 97 * hash + (this.credentialsNonExpired ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.dateAccountExpired);
        hash = 97 * hash + Objects.hashCode(this.dateCredentialsExpired);
        hash = 97 * hash + Objects.hashCode(this.dateLastFailAttempts);
        hash = 97 * hash + Objects.hashCode(this.function);
        hash = 97 * hash + Objects.hashCode(this.area);
        hash = 97 * hash + Objects.hashCode(this.attempts);
        hash = 97 * hash + Objects.hashCode(this.rateHour);
        hash = 97 * hash + Objects.hashCode(this.rateHourEst);
        hash = 97 * hash + Objects.hashCode(this.userType);
        hash = 97 * hash + Objects.hashCode(this.role);
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
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.cellphone, other.cellphone)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (this.accountNonExpired != other.accountNonExpired) {
            return false;
        }
        if (this.accountNonLocked != other.accountNonLocked) {
            return false;
        }
        if (this.credentialsNonExpired != other.credentialsNonExpired) {
            return false;
        }
        if (!Objects.equals(this.dateAccountExpired, other.dateAccountExpired)) {
            return false;
        }
        if (!Objects.equals(this.dateCredentialsExpired, other.dateCredentialsExpired)) {
            return false;
        }
        if (!Objects.equals(this.dateLastFailAttempts, other.dateLastFailAttempts)) {
            return false;
        }
        if (!Objects.equals(this.function, other.function)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.attempts, other.attempts)) {
            return false;
        }
        if (!Objects.equals(this.rateHour, other.rateHour)) {
            return false;
        }
        if (!Objects.equals(this.rateHourEst, other.rateHourEst)) {
            return false;
        }
        if (!Objects.equals(this.userType, other.userType)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", login=" + login + ", password=" + password + ", cellphone=" + cellphone + ", language=" + language + ", active=" + active + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", dateAccountExpired=" + dateAccountExpired + ", dateCredentialsExpired=" + dateCredentialsExpired + ", dateLastFailAttempts=" + dateLastFailAttempts + ", function=" + function + ", area=" + area + ", attempts=" + attempts + ", rateHour=" + rateHour + ", rateHourEst=" + rateHourEst + ", userType=" + userType + ", role=" + role + '}';
    }
    
    

}
