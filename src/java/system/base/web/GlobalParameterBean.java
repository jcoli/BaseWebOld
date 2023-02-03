/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/

package system.base.web;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import system.base.entities.globalParameter.GlobalParameter;
import system.base.entities.globalParameter.GlobalParameterRN;
import system.base.entities.param.Param;
import system.base.entities.paramType.ParamType;
import system.base.entities.paramType.ParamTypeRN;
import system.base.entities.user.User;
import system.base.entities.user.UserRN;
import system.base.util.ContextUtil;
import system.base.util.MessageUtil;



/**
 * @Project BaseWeb 
 * @brief Class GlobalParameterBean
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date:  20/02/2015
 */
@ManagedBean(name = "globalBean")
@SessionScoped
public class GlobalParameterBean implements Serializable{
    
    final static Logger logger = Logger.getLogger(GlobalParameterBean.class);
    
    private GlobalParameter globalParameter = new GlobalParameter();
    private GlobalParameter selGlobalParameter = new GlobalParameter();
    private GlobalParameter seltypeGB = new GlobalParameter();
    private List<GlobalParameter> listGlobalParameters = null;
    private boolean bNewParam;
    private boolean bEditParam;
    private boolean bNewParamSel;
    private boolean bSaveEditParam;
    private boolean bParamEdit = false;
    private String typeParam;
    

    private Locale locale;
    private String path = "/restrict/globalParameter/admin_globalParameter.jsf";
   
    
    public String editParam() {

        ContextBean contextBean = ContextUtil.getContextoBean();
        User user = new User();
        user = contextBean.getLoggedUser();
        UserRN userRN = new UserRN();
        locale = contextBean.getLocale();

        user = userRN.searchByLogin(user.getLogin());

        if (!(user.getRole().contains("ROLE_SUPER"))) {
            String text = MessageUtil.getMensagem("no_user_priveleges");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, MessageUtil.getMensagem("user") + user.getName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/restrict/principal.jsf";
        }

        this.listGlobalParameters = null;
        this.selGlobalParameter = null;
        this.globalParameter = null;
        this.bNewParam = false;
        this.bEditParam = true;
        this.bNewParamSel = true;
        this.bParamEdit = true;
        bSaveEditParam = true;
        

        return path;
    }
    
    public String paramNew() {

        this.globalParameter = new GlobalParameter();
        this.globalParameter.setType("boolean");

        this.bNewParam = true;
        this.bEditParam = false;
        this.bNewParamSel = true;
        this.bParamEdit = false;
        this.bSaveEditParam = true;

        this.listGlobalParameters = null;
        this.listGlobalParameters = this.getListGlobalParameter();

        return path;
    }
    
    public String paramEdit() {

        this.bNewParam = false;
        this.bEditParam = false;
        this.bNewParamSel = true;
        this.bParamEdit = false;
        this.bSaveEditParam = false;

        return path;
    }
    
    public String filterParam() {

        if (this.typeParam != null ) {
            this.listGlobalParameters = null;
            this.listGlobalParameters = this.getListGlobalParameter(typeParam);
        } else {
            this.listGlobalParameters = null;
            this.listGlobalParameters = this.getListGlobalParameter();
        }

        return path;
    }
    
    public String saveParamEdit() {

        this.bNewParam = false;
        this.bEditParam = true;
        this.bNewParamSel = true;
        this.bParamEdit = true;
        this.bSaveEditParam = true;

        GlobalParameterRN  globalParameterRN = new GlobalParameterRN();
        globalParameterRN.save(this.globalParameter);
        this.globalParameter = new GlobalParameter();
        this.listGlobalParameters = null;

        return path;
    }
    
    public String saveParamNew() {

        this.bNewParam = false;
        this.bEditParam = true;
        this.bNewParamSel = true;
        this.bParamEdit = true;
        this.bSaveEditParam = true;

        GlobalParameterRN  globalParameterRN = new GlobalParameterRN();
        globalParameterRN.save(this.globalParameter);
        this.globalParameter = new GlobalParameter();
        this.listGlobalParameters = null;

        return path;

        
    }
    
    public String cancel() {

        return "/restrict/principal";
    }
    
    
    
    public List<GlobalParameter> getListGlobalParameter() {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        GlobalParameterRN  globalParameterRN = new GlobalParameterRN();
        this.listGlobalParameters = globalParameterRN.list();

        return this.listGlobalParameters;
    }
    
     public List<GlobalParameter> getListGlobalParameter(String type) {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        GlobalParameterRN  globalParameterRN = new GlobalParameterRN();
        this.listGlobalParameters = globalParameterRN.list(type);

        return this.listGlobalParameters;
    }
    
    public void onRowSelectParam(SelectEvent event) {
        this.globalParameter = this.selGlobalParameter;
        this.bNewParamSel = false;

    }
    
     public void onSelectParam(AjaxBehaviorEvent event) throws Exception {
         logger.info("onRow");
        if (event.getComponent().getAttributes().get("value").toString().trim().equalsIgnoreCase("string")) {
            seltypeGB.setType("string");
            logger.info("onRow string");
        }
        if (event.getComponent().getAttributes().get("value").toString().trim().equalsIgnoreCase("boolean")) {
            seltypeGB.setType("boolean");
            logger.info("onRow boolean");
        }
         if (event.getComponent().getAttributes().get("value").toString().trim().equalsIgnoreCase("integer")) {
            seltypeGB.setType("boolean");
            logger.info("onRow boolean");
        }

    }

    public static Logger getLogger() {
        return logger;
    }

    public GlobalParameter getGlobalParameter() {
        return globalParameter;
    }

    public GlobalParameter getSelGlobalParameter() {
        return selGlobalParameter;
    }

    public boolean isbNewParam() {
        return bNewParam;
    }

    public boolean isbEditParam() {
        return bEditParam;
    }

    public boolean isbNewParamSel() {
        return bNewParamSel;
    }

    public boolean isbParamEdit() {
        return bParamEdit;
    }

    public String getTypeParam() {
        return typeParam;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getPath() {
        return path;
    }

    public void setGlobalParameter(GlobalParameter globalParameter) {
        this.globalParameter = globalParameter;
    }

    public void setSelGlobalParameter(GlobalParameter selGlobalParameter) {
        this.selGlobalParameter = selGlobalParameter;
    }

    public void setListGlobalParameters(List<GlobalParameter> listGlobalParameters) {
        this.listGlobalParameters = listGlobalParameters;
    }

    public void setbNewParam(boolean bNewParam) {
        this.bNewParam = bNewParam;
    }

    public void setbEditParam(boolean bEditParam) {
        this.bEditParam = bEditParam;
    }

    public void setbNewParamSel(boolean bNewParamSel) {
        this.bNewParamSel = bNewParamSel;
    }

    public void setbParamEdit(boolean bParamEdit) {
        this.bParamEdit = bParamEdit;
    }

    public void setTypeParam(String typeParam) {
        this.typeParam = typeParam;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public GlobalParameter getSeltypeGB() {
        return seltypeGB;
    }

    public void setSeltypeGB(GlobalParameter seltypeGB) {
        this.seltypeGB = seltypeGB;
    }

    public boolean isbSaveEditParam() {
        return bSaveEditParam;
    }

    public void setbSaveEditParam(boolean bSaveEditParam) {
        this.bSaveEditParam = bSaveEditParam;
    }
    
    
    
    
    
    
    
    

}
