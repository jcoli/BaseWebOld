/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.web;

import system.base.web.ContextBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import system.base.entities.holiday.Holiday;
import system.base.entities.holiday.HolidayRN;
import system.base.entities.param.Param;
import system.base.entities.param.ParamRN;
import system.base.entities.paramType.ParamType;
import system.base.entities.paramType.ParamTypeRN;
import system.base.entities.user.User;
import system.base.entities.user.UserRN;
import system.base.util.ContextUtil;
import system.base.util.MessageUtil;
import org.apache.log4j.Logger;


/**
 * @Project ProjetosSAIBR
 * @brief Classe ParamBean
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date 19/08/2014
 */
@ManagedBean(name = "paramBean")
@SessionScoped
public class ParamBean implements Serializable {

    final static Logger logger = Logger.getLogger(ParamBean.class);

    private ParamType paramType = new ParamType();
    private ParamType selParamType = new ParamType();
    private List<ParamType> listParamType = null;
    private String selParamTypeSt = "";
    private boolean bNewParamType;
    private boolean bEditParamType;
    private boolean bNewParamTypeSel;
    private boolean bParamTypeEdit = false;

    private Param param = new Param();
    private Param selParam = new Param();
    private List<Param> listParam = null;
    private ParamType selParamSt = null;
    private boolean bNewParam;
    private boolean bEditParam;
    private boolean bNewParamSel;
    private boolean bParamEdit = false;

    private Locale locale;

    private Holiday holiday = new Holiday();
    private Holiday selHoliday = new Holiday();
    private List<Holiday> listHoliday = null;
    private List<Param> listDomain = null;
    private String selStringHoliday = "";
    private boolean bNewHoliday;
    private boolean bEditaHoliday;
    private boolean bNewHolidaySel;
    private boolean bHolidayEdita = false;

    //================================================================
    //================= Parametros ===================================
    //================================================================
    public String editParam() {

        ContextBean contextBean = ContextUtil.getContextoBean();
        User user = new User();
        user = contextBean.getLoggedUser();
        UserRN userRN = new UserRN();

        user = userRN.searchByLogin(user.getLogin());

        //java.util.Set<String> permissoes = user.getPermissao();
        //if (!(permissoes.contains("ROLE_DEVELOP"))) {
        //if (!(user.getPermissao().contains("ROLE_DEVELOP"))) {
        if (!(user.getRole().contains("ROLE_SUPER"))) {
            String text = MessageUtil.getMensagem("no_user_priveleges");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, MessageUtil.getMensagem("user") + user.getName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/restrict/principal.jsf";
        }

        this.listParam = null;
        this.listParamType = null;
        this.selParam = null;
        this.param = null;
        this.bNewParam = false;
        this.bEditParam = true;
        this.bNewParamSel = true;
        this.bParamEdit = true;
        this.param = new Param();

        return "/admin/param/param_lists.jsf";
    }

    public String paramNew() {

        this.param = new Param();

        this.bNewParam = true;
        this.bEditParam = false;
        this.bNewParamSel = true;
        this.bParamEdit = false;

        this.listParamType = null;
        this.listParamType = this.getListParamType();

        return "/admin/param/param_lists.jsf";
    }

    public String paramEdit() {

        this.bNewParam = true;
        this.bEditParam = false;
        this.bNewParamSel = true;
        this.bParamEdit = false;

        return "/admin/param/param_lists.jsf";
    }

    public String filterParam() {

        if (this.selParamSt != null && this.selParamSt.getId() > 0) {
            this.listParam = null;
            this.listParam = this.getListParam(selParamSt);
        } else {
            this.listParam = null;
            this.listParam = this.getListParam();
        }

        return "/admin/param/param_lists.jsf";
    }

    public String saveParamEdit() {

        this.bNewParam = false;
        this.bEditParam = true;
        this.bNewParamSel = true;
        this.bParamEdit = true;

        ParamRN paramRN = new ParamRN();
        //this.param.setTipoCampo(selParamST);
        paramRN.save(this.param);
        this.param = new Param();
        this.listParam = null;

        return "/admin/param/param_lists.jsf";
    }

    public String saveParamNew() {

        this.bNewParam = false;
        this.bEditParam = true;
        this.bNewParamSel = true;
        this.bParamEdit = true;

        ParamRN paramRN = new ParamRN();
        paramRN.save(this.param);
        this.listParam = null;
        this.param = new Param();

        return "/admin/param/param_lists.jsf";
    }

    public String cancel() {

        return "/restrict/principal";
    }

    //================================================================
    //================= Tipos de Parametros ==========================
    //================================================================
    public String editParamType() {

        ContextBean contextBean = ContextUtil.getContextoBean();
        User user = new User();
        user = contextBean.getLoggedUser();
        UserRN userRN = new UserRN();

        user = userRN.searchByLogin(user.getLogin());

        //java.util.Set<String> permissoes = user.getPermissao();
        //if (!(permissoes.contains("ROLE_DEVELOP"))) {
        //if (!(user.getPermissao().contains("ROLE_DEVELOP"))) {
        if (!(user.getRole().contains("ROLE_SUPER"))) {
            String text = MessageUtil.getMensagem("no_user_priveleges");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, MessageUtil.getMensagem("user") + user.getName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/restrict/principal.jsf";
        }

        this.listParamType = null;
        this.selParamType = null;
        this.paramType = null;
        this.bNewParamType = false;
        this.bEditParamType = true;
        this.bNewParamTypeSel = true;
        this.bParamTypeEdit = true;

        logger.info("logger.aqui");

        return "/admin/paramType/param_type_lists.jsf";
    }

    public String paramTypeNew() {

        this.paramType = new ParamType();

        this.bNewParamType = true;
        this.bEditParamType = false;
        this.bNewParamTypeSel = true;
        this.bParamTypeEdit = false;

        return "/admin/paramType/param_type_lists.jsf";
    }

    public String paramTypeEdit() {

        this.bNewParamType = true;
        this.bEditParamType = false;
        this.bNewParamTypeSel = true;
        this.bParamTypeEdit = false;

        return "/admin/paramType/param_type_lists.jsf";
    }

    public String filterParamType() {

        this.listParamType = null;
        this.getListParamType();

        return "/admin/paramType/param_type_lists.jsf";
    }

    public String saveParamTypeEdit() {

        this.bNewParamType = false;
        this.bEditParamType = true;
        this.bNewParamTypeSel = true;
        this.bParamTypeEdit = true;

        ParamTypeRN paramTypeRN = new ParamTypeRN();
        //this.paramType.setTipoCampo(selParamTypeSt);
        paramTypeRN.save(this.paramType);
        this.paramType = null;
        this.listParamType = null;
        
        logger.info("saveParamTypeEdit");

        return "/admin/paramType/param_type_lists.jsf";
    }

    public String saveParamTypeNew() {

        this.bNewParamType = false;
        this.bEditParamType = true;
        this.bNewParamTypeSel = true;
        this.bParamTypeEdit = true;

        ParamTypeRN paramTypeRN = new ParamTypeRN();
        paramTypeRN.save(this.paramType);
        this.listParamType = null;
        this.listParamType = this.getListParamType();
        this.paramType = null;

        return "/admin/paramType/param_type_lists.jsf";
    }

    //========================================================================
    //======================= Relatorios =====================================
    //========================================================================
    //========================================================================
    //======================= Holidays =====================================
    //========================================================================
    public String editHoliday() {

        ContextBean contextBean = ContextUtil.getContextoBean();
        User user = new User();
        user = contextBean.getLoggedUser();
        UserRN userRN = new UserRN();

        user = userRN.searchByLogin(user.getLogin());

        //java.util.Set<String> permissoes = user.getPermissao();
        //if (!(permissoes.contains("ROLE_DEVELOP"))) {
        //if (!(user.getPermissao().contains("ROLE_DEVELOP"))) {
        if (!(user.getRole().contains("ROLE_SUPER"))) {
            String text = MessageUtil.getMensagem("no_user_priveleges");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, MessageUtil.getMensagem("user") + user.getName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/restrict/principal.jsf";
        }

        this.listHoliday = null;
        this.selHoliday = null;
        this.holiday = new Holiday();
        this.bHolidayEdita = true;

        this.bEditaHoliday = false;
        this.bNewHoliday = false;
        this.bNewHolidaySel = true;

        return "/admin/holiday/holiday.jsf";
    }

    public String editHolidaySel() {

        ContextBean contextBean = ContextUtil.getContextoBean();
        User user = new User();
        user = contextBean.getLoggedUser();
        UserRN userRN = new UserRN();

        user = userRN.searchByLogin(user.getLogin());

        //java.util.Set<String> permissoes = user.getPermissao();
        //if (!(permissoes.contains("ROLE_DEVELOP"))) {
        //if (!(user.getPermissao().contains("ROLE_DEVELOP"))) {
        if (!(user.getRole().contains("ROLE_SUPER"))) {
            String text = MessageUtil.getMensagem("no_user_priveleges");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, MessageUtil.getMensagem("user") + user.getName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/restrict/principal.jsf";
        }

        this.bEditaHoliday = true;
        this.bHolidayEdita = false;
        this.bNewHoliday = false;
        this.bNewHolidaySel = false;

        return "/admin/holiday/holiday.jsf";
    }

    public String filterHoliday() {

        this.listHoliday = null;
        this.getListHoliday();

        return "/admin/holiday/holiday.jsf";

    }

    public String holidayNew() {

        this.holiday = new Holiday();
        this.bNewHoliday = true;
        this.bEditaHoliday = false;
        this.bNewHolidaySel = true;
        this.bHolidayEdita = false;

        return "/admin/holiday/holiday.jsf";
    }

    public String saveHolidayEditado() {

        this.bNewHoliday = false;
        this.bEditaHoliday = false;
        this.bNewHolidaySel = false;
        this.bHolidayEdita = true;

        //this.paramType.setTipoCampo(selParamTypeSt);F
        HolidayRN holidayRN = new HolidayRN();
        holidayRN.save(this.holiday);
        this.holiday = null;
        this.listHoliday = null;

        return "/admin/holiday/holiday.jsf";
    }

    public String saveHolidayNew() {

        this.bNewHoliday = false;
        this.bEditaHoliday = false;
        this.bNewHolidaySel = false;
        this.bHolidayEdita = true;

        Date d = new Date();
        
        logger.info("saveHolidayNew");

        HolidayRN holidayRN = new HolidayRN();
        holidayRN.save(this.holiday);

        this.listHoliday = null;
        this.holiday = new Holiday();
        
        logger.info("saveHolidayNew1");

        return "/admin/holiday/holiday.jsf";
        
        //return "/restrict/principal.jsf";
    }

    //========================================================================
    //======================= Envio =====================================
    //========================================================================
    //==========================================================================
    public void onRowSelectParam(SelectEvent event) {
        this.param = this.selParam;
        this.bNewParamSel = false;

    }

    public void onRowSelectParamType(SelectEvent event) {
        this.paramType = this.selParamType;
        this.bNewParamTypeSel = false;
    }

    public void onRowSelectHoliday(SelectEvent event) {
        this.holiday = this.selHoliday;
        bNewHolidaySel = false;
    }
    
    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "No activity.", "What are you doing over there?"));
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Welcome Back", "Well, that's a long coffee break!"));
    }

    public List<ParamType> getListParamType() {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        if (this.selParamTypeSt.isEmpty() || this.selParamTypeSt == null) {
            this.selParamTypeSt = "%";
        }
        if (this.listParamType == null) {
            ParamTypeRN paramTypeRN = new ParamTypeRN();
            this.listParamType = paramTypeRN.listP(this.selParamTypeSt);
        }
        return this.listParamType;
    }

    public List<ParamType> getListParamType(String paramType) {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        ParamTypeRN paramTypeRN = new ParamTypeRN();
        this.listParamType = paramTypeRN.listP(paramType);

        return this.listParamType;
    }

    public List<Param> getListParam() {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        if (this.listParam == null) {
            ParamRN paramRN = new ParamRN();
            this.listParam = paramRN.listP(null);
        }
        return this.listParam;
    }
    
    public List<Param> getListDomain() {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        if (this.listDomain == null) {
            ParamRN paramRN = new ParamRN();
            ParamTypeRN paramTypeRN = new ParamTypeRN();
            ParamType paramType = paramTypeRN.searchByDesc("holiday domain");
            this.listDomain = paramRN.listP(paramType);
        }
        return this.listDomain;
    }

    public List<Param> getListParam(ParamType paramType) {
        ContextBean contextoBean = ContextUtil.getContextoBean();
        locale = contextoBean.getLocale();

        ParamRN paramRN = new ParamRN();
        this.listParam = paramRN.listP(paramType);

        return this.listParam;
    }

    public List<Holiday> getListHoliday() {

        if (this.listHoliday == null) {
            HolidayRN holidayRN = new HolidayRN();
            this.listHoliday = holidayRN.listH();
        }
        return this.listHoliday;
    }

    public String idleListener() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        logger.info("idleListener");
        
        //killHttpSession(ctx);
        return "/public/login.jsf";
    }
    
  

    public void activeListener() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        logger.info("activeListener");
        //killHttpSession(ctx);
        //doRedirectToLoggedOutPage(ctx);
    }

    public ParamType getParamType() {
        return paramType;
    }

    public ParamType getSelParamType() {
        return selParamType;
    }

    public String getSelParamTypeSt() {
        return selParamTypeSt;
    }

    public boolean isbNewParamType() {
        return bNewParamType;
    }

    public boolean isbEditParamType() {
        return bEditParamType;
    }

    public boolean isbNewParamTypeSel() {
        return bNewParamTypeSel;
    }

    public boolean isbParamTypeEdit() {
        return bParamTypeEdit;
    }

    public Param getParam() {
        return param;
    }

    public Param getSelParam() {
        return selParam;
    }

    public ParamType getSelParamSt() {
        return selParamSt;
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

    public Locale getLocale() {
        return locale;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public Holiday getSelHoliday() {
        return selHoliday;
    }

    public String getSelStringHoliday() {
        return selStringHoliday;
    }

    public boolean isbNewHoliday() {
        return bNewHoliday;
    }

    public boolean isbEditaHoliday() {
        return bEditaHoliday;
    }

    public boolean isbNewHolidaySel() {
        return bNewHolidaySel;
    }

    public boolean isbHolidayEdita() {
        return bHolidayEdita;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public void setSelParamType(ParamType selParamType) {
        this.selParamType = selParamType;
    }

    public void setListparamType(List<ParamType> listparamType) {
        this.listParamType = listparamType;
    }

    public void setSelParamTypeSt(String selParamTypeSt) {
        this.selParamTypeSt = selParamTypeSt;
    }

    public void setbNewParamType(boolean bNewParamType) {
        this.bNewParamType = bNewParamType;
    }

    public void setbEditParamType(boolean bEditParamType) {
        this.bEditParamType = bEditParamType;
    }

    public void setbNewParamTypeSel(boolean bNewParamTypeSel) {
        this.bNewParamTypeSel = bNewParamTypeSel;
    }

    public void setbParamTypeEdit(boolean bParamTypeEdit) {
        this.bParamTypeEdit = bParamTypeEdit;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public void setSelParam(Param selParam) {
        this.selParam = selParam;
    }

    public void setListParam(List<Param> listParam) {
        this.listParam = listParam;
    }

    public void setSelParamSt(ParamType selParamSt) {
        this.selParamSt = selParamSt;
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

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }

    public void setSelHoliday(Holiday selHoliday) {
        this.selHoliday = selHoliday;
    }

    public void setListHoliday(List<Holiday> listHoliday) {
        this.listHoliday = listHoliday;
    }

    public void setSelStringHoliday(String selStringHoliday) {
        this.selStringHoliday = selStringHoliday;
    }

    public void setbNewHoliday(boolean bNewHoliday) {
        this.bNewHoliday = bNewHoliday;
    }

    public void setbEditaHoliday(boolean bEditaHoliday) {
        this.bEditaHoliday = bEditaHoliday;
    }

    public void setbNewHolidaySel(boolean bNewHolidaySel) {
        this.bNewHolidaySel = bNewHolidaySel;
    }

    public void setbHolidayEdita(boolean bHolidayEdita) {
        this.bHolidayEdita = bHolidayEdita;
    }

    public void setListParamType(List<ParamType> listParamType) {
        this.listParamType = listParamType;
    }

    public void setListDomain(List<Param> listDomain) {
        this.listDomain = listDomain;
    }

    
}
