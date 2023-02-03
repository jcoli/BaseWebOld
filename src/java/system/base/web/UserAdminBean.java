/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.web;

import system.base.web.ContextBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.util.DigestUtils;
import system.base.entities.paramType.ParamType;
import system.base.entities.paramType.ParamTypeRN;
import system.base.entities.userType.UserType;
import system.base.entities.user.User;
import system.base.entities.user.UserRN;
import system.base.util.CommandExec;
import system.base.util.ContextUtil;
import system.base.util.MessageUtil;


/**
 * @Project ProjetosSAIBR
 * @brief Classe UserPerfilBean
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date 27/09/2014
 */
@ManagedBean(name = "userPerfilBean")
@SessionScoped
public class UserAdminBean implements Serializable{
    
    final static Logger logger = Logger.getLogger(UserAdminBean.class);

    private User user = new User();
    private UserType tipoUser;

    private User filterUser = null;
    private String confirmarPassword;
    private List<User> listUser = null;
    private String destinoSalvar = "/restrict/user/perfil_user";
    private String passwordCriptografada;
    private User selUser = new User();
    private User filterUserTime = null;
    private List<Double> saldoHoras = new ArrayList<Double>();
    private Locale locale;

    
    private List<ParamType> listparamType = null;
    
    
    
    
       

    public String editUser(){
        ContextBean contextBean = ContextUtil.getContextoBean();
        this.user = contextBean.getLoggedUser();
        // UserRN userRN = new UserRN();
        
        //this.user = userRN.buscarPorLogin(this.user.getLogin());
        this.passwordCriptografada = this.user.getPassword();
        
        return "/restrict/user/perfil_user";
    }
    //==================================================================
    //=================  EXCELL ========================================  
    //==================================================================
    
    
    
    //==================================================================
    //=================  User     ===================================  
    //==================================================================
    
    public String savePerfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        String password = this.user.getPassword();
        if (password != null && password.trim().length() > 0) {
            String passwordCripto = DigestUtils.md5DigestAsHex(password.getBytes());
            this.user.setPassword(passwordCripto);

        } else {
            this.user.setPassword(this.passwordCriptografada);
        }
        user.setLanguage("pt_BR");
        UserRN userRN = new UserRN();
        userRN.save(this.user);

        return "/restrict/principal";
    }
    
    public String cancelar() {
    
        return "/restrict/principal";
    }
    
    public String atribuiPermissao(User user, String permissao) {
        this.user = user;
        if (user.isActive()) {
            java.util.Set<String> permissoes = this.user.getRole();
            if (permissoes.contains(permissao)) {
                permissoes.remove(permissao);
            } else {
                permissoes.add(permissao);

            }
        }
        return null;
    }
    
    //==================================================================
    //=================  TimeTracker ===================================  
    //==================================================================
    
    //==================================================================
    //=================  Relatorios  ====================================  
    //==================================================================
    
    
    
    public List<ParamType> getListaParam(String param) {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        ParamTypeRN paramTypeRN = new ParamTypeRN();
        this.listparamType = paramTypeRN.listP(param);

        return this.listparamType;
    }

    public List<User> getListaUser() {
        if (this.listUser == null) {
            UserRN userRN = new UserRN();
              this.listUser = userRN.listU();
        }

        return this.listUser;
    }

    
    //==================================================================
    //=================  Get And Setters ===============================  
    //==================================================================
    public void setUser(User user) {
        this.user = user;
    }

    public void setTipoUser(UserType tipoUser) {
        this.tipoUser = tipoUser;
    }

    public void setFiltroUser(User filterUser) {
        this.filterUser = filterUser;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    public void setListaUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public void setDestinoSalvar(String destinoSalvar) {
        this.destinoSalvar = destinoSalvar;
    }

    public void setPasswordCriptografada(String passwordCriptografada) {
        this.passwordCriptografada = passwordCriptografada;
    }

    public void setSelUser(User selUser) {
        this.selUser = selUser;
    }

    
    public void setListaparamType(List<ParamType> listparamType) {
        this.listparamType = listparamType;
    }

    public User getUser() {
        return user;
    }

    public UserType getTipoUser() {
        return tipoUser;
    }

    public User getFiltroUser() {
        return filterUser;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public String getDestinoSalvar() {
        return destinoSalvar;
    }

    public String getPasswordCriptografada() {
        return passwordCriptografada;
    }

    public User getSelUser() {
        return selUser;
    }

    public User getFiltroUserTime() {
        return filterUserTime;
    }

    public List<Double> getSaldoHoras() {
        return saldoHoras;
    }

    
    public List<ParamType> getListaparamType() {
        return listparamType;
    }

    
}

