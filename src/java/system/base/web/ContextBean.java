package system.base.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import system.base.entities.user.User;
import system.base.entities.user.UserRN;

@ManagedBean(name = "contextBean")
@SessionScoped
public class ContextBean implements Serializable{

    private final static Logger logger = Logger.getLogger(ContextBean.class);

    public User loggedUser = null;
    private String path = null;
    private Locale locale = null;
    private List<Locale> languages;

    public User getLoggedUser() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        String login = external.getRemoteUser();
        
       //  logger.info ("locale" );

        if (this.loggedUser == null || !login.equals(this.loggedUser.getLogin())) {

            if (login != null) {
                UserRN userRN = new UserRN();
                this.loggedUser = userRN.searchByLogin(login);
                
         //       logger.info ("locale1" + this.loggedUser.getName());

                String[] info = this.loggedUser.getLanguage().split("_");
                this.locale = new Locale(info[0], info[1]);

               context.getELContext().setLocale(this.locale);
           //     logger.info("getLoggedUsuario " + this.locale.toString());

            }
        }

        return loggedUser;
    }

    public Locale getLocaleUsuario() {

        User user = this.getLoggedUser();
        String idioma = user.getLanguage();
        String[] info = idioma.split("_");
        this.locale = new Locale(info[0], info[1]);

        //logger.info("getlocaleUser " + this.locale);

//        try {
//            FacesContext context = FacesContext.getCurrentInstance();
//            logger.info("getlocaleUser a " + locale);
//            context.getViewRoot().setLocale(this.locale);
//            logger.info("getlocaleUser1 " + this.locale);
//        } catch (Exception e) {
//            logger.info("getlocaleUser1 " + e);
//        }
        return this.locale;
    }

    public List<Locale> getLanguages() {
        FacesContext context = FacesContext.getCurrentInstance();
        Iterator<Locale> locales = context.getApplication().getSupportedLocales();
        this.languages = new ArrayList<Locale>();
        while (locales.hasNext()) {
            this.languages.add(locales.next());
        }
        return languages;
    }

    public void setUserLanguage(String language) {
        UserRN userRN = new UserRN();
        this.loggedUser = userRN.load(this.loggedUser.getId());
        this.loggedUser.setLanguage(language);
        userRN.save(this.loggedUser);

        String[] info = language.split("_");
        Locale locale = new Locale(info[0], info[1]);

        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(locale);
    }

    public Locale setLocale(String language) {

        String[] info = language.split("_");
        this.locale = new Locale(info[0], info[1]);

        FacesContext context = FacesContext.getCurrentInstance();
        context.getELContext().setLocale(this.locale);

        return this.locale;

    }

    public Locale getLocale() {
        //logger.info("getlocale" + locale);

        if (this.locale == null) {
            this.locale = this.getLocaleUsuario();
          //  logger.info("getlocale null" + this.locale);
        }

       // logger.info("getlocale " + this.locale);

        FacesContext context = FacesContext.getCurrentInstance();

        context.getELContext().setLocale(this.locale);

       // logger.info("getlocale2 " + this.locale);
        //context.getResponseComplete();
       // logger.info("getlocale2 " + this.locale);
        //context.getViewRoot().setLocale(this.locale);

        //logger.info("---> " + this.locale);

        return this.locale;
    }

    public void setLoggedUser(User user) {
        this.loggedUser = user;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        String path = "";
        try {
            InitialContext ic = new InitialContext();
            path = (String) ic.lookup("java:comp/env/filespath");
        } catch (NamingException exc) {
            logger.error("Erro Path " + exc);
        }

        return path;
    }

}
