package system.base.web;

import java.io.Serializable;
import system.base.web.ContextBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import org.springframework.util.DigestUtils;
import system.base.entities.log.Log;
import system.base.entities.log.LogRN;

import system.base.entities.userType.UserType;
import system.base.entities.userType.UserTypeRN;
import system.base.entities.user.User;
import system.base.entities.user.UserRN;
import system.base.util.ContextUtil;
import system.base.util.MessageUtil;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable{

    final static Logger logger = Logger.getLogger(UserBean.class);

    private User user = new User();
    private UserType userType;

    public String source;

    private User filterUser = new User();
    private String confirmarPassword;
    private List<User> list;
    private String destinoSalvar = "/admin/user/admin_user";
    private String passwordCriptografada;
    private String newPassword;
    private String oldPassword;
    private User selUser = new User();
    private User filterUserTime = null;
    private List<Double> saldoHoras = new ArrayList<Double>();

    public String newUser() {
        this.destinoSalvar = "userSucesso";
        this.user = new User();
        this.user.setActive(true);
        return "user";
    }

    public String cancel() {
        list = null;
        return "/admin/user/admin_user";
    }

    public String edit() {
        this.passwordCriptografada = this.user.getPassword();
        return "/admin/user/user";
    }

    public String edit1() {
        this.user = this.selUser;
        this.passwordCriptografada = this.user.getPassword();
        return "/admin/user/user";
    }

    public String userEdit() {
        ContextBean contextBean = ContextUtil.getContextoBean();
        String login = contextBean.getLoggedUser().getLogin();
        UserRN userRN = new UserRN();

        this.user = userRN.searchByLogin(login);
        this.passwordCriptografada = this.user.getPassword();

        this.source = "user";

        return "/restrict/user/user_perfil";
    }

    public String userEditAdm() {
        ContextBean contextBean = ContextUtil.getContextoBean();
        String login = contextBean.getLoggedUser().getLogin();
        UserRN userRN = new UserRN();

        this.user = this.selUser;

        this.passwordCriptografada = this.user.getPassword();

        this.source = "adm";

        return "/restrict/user/user_perfil";
    }

    public String userPassword() {
        ContextBean contextBean = ContextUtil.getContextoBean();
        String login = contextBean.getLoggedUser().getLogin();
        UserRN userRN = new UserRN();

        logger.info("userPassword");

        this.user = userRN.searchByLogin(login);

        return "/restrict/user/user_changePassword";
    }

    public String userPasswordAdm() {
        ContextBean contextBean = ContextUtil.getContextoBean();
        String login = contextBean.getLoggedUser().getLogin();
        UserRN userRN = new UserRN();

        this.user = this.selUser;

        this.passwordCriptografada = this.user.getPassword();

        return "/restrict/user/admin_changePassword";
    }

    public String savePerfil(String source) {
        FacesContext context = FacesContext.getCurrentInstance();
//        String password = this.user.getPassword();
//        if (password != null && password.trim().length() > 0) {
//            String passwordCripto = DigestUtils.md5DigestAsHex(password.getBytes());
//            this.user.setPassword(passwordCripto);
//
//        } else {
//            this.user.setPassword(this.passwordCriptografada);
//        }
//        user.setLanguage("pt_BR");
        UserRN userRN = new UserRN();
        userRN.save(this.user);

        if (source.equals("adm")) {
            return "/admin/user/admin_user.jsf";
        } else {
            return "/restrict/principal";
        }
    }

    public String saveAdminPassword() {

        UserRN userRN = new UserRN();
        passwordCriptografada = DigestUtils.md5DigestAsHex(this.newPassword.getBytes());
        this.user.setPassword(this.passwordCriptografada);
        Date d = new Date();
        this.user.setDateCredentialsExpired(d);
        this.user.setCredentialsNonExpired(true);
        userRN.save(this.user);

        ContextBean contextBean = ContextUtil.getContextoBean();
        String login = contextBean.getLoggedUser().getLogin();
        User userLogged = userRN.searchByLogin(login);

        LogRN logRn = new LogRN();
        Log log = new Log();
        log.setDataLog(new Date());
        log.setLoggedUser(userLogged);
        log.setDescription("O Administrador "+userLogged.getName()+ "trocou a senha do usuário "+user.getName());
        logRn.save(log);

        return "/restrict/principal";

    }

    public String saveNewPassword() {
        ContextBean contextBean = ContextUtil.getContextoBean();
        String login = contextBean.getLoggedUser().getLogin();
        UserRN userRN = new UserRN();
        this.user = userRN.searchByLogin(login);

        String password = this.user.getPassword();
        String passwordCripto = DigestUtils.md5DigestAsHex(this.oldPassword.getBytes());
        passwordCriptografada = DigestUtils.md5DigestAsHex(this.newPassword.getBytes());

        if (passwordCripto.equals(password)) {
            logger.info("password equals");
            if (passwordCriptografada.equals(password)) {
                logger.info("same password " + password + " -- " + passwordCripto);
                String text = MessageUtil.getMensagem("samePassword");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, MessageUtil.getMensagem("user") + user.getName());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "/restrict/user/user_changePassword";

            }

            this.user.setPassword(this.passwordCriptografada);
            Date d = new Date(System.currentTimeMillis() + 30L * 24 * 3600 * 1000);
            this.user.setDateCredentialsExpired(d);
            this.user.setCredentialsNonExpired(true);

            LogRN logRn = new LogRN();
            Log log = new Log();
            log.setDataLog(new Date());
            log.setLoggedUser(user);
            log.setDescription("Usuário trocou a senha");
            logRn.save(log);

            userRN.save(this.user);
            return "/restrict/principal";
        } else {
            logger.info("password not equals " + password + " -- " + passwordCripto);
            String text = MessageUtil.getMensagem("oldPasswordNotMatch");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, MessageUtil.getMensagem("user") + user.getName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/restrict/user/user_changePassword";

        }

    }

    public String save() {
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

        return this.destinoSalvar;
    }

    public String exclude() {
        this.user = this.selUser;
        UserRN userRN = new UserRN();
        userRN.exclude(this.user);
        this.list = null;
        return null;
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

    public void filter() {
        list = null;
    }

    public String ativar() {
        java.util.Set<String> permissoes = this.user.getRole();
        if (this.user.isActive()) {
            this.user.setActive(false);
        }

        if (permissoes.contains("ROLE_ADMINISTRADOR")) {
            permissoes.remove("ROLE_ADMINISTRADOR");
        }
        if (permissoes.contains("ROLE_AVANCADO")) {
            permissoes.remove("ROLE_AVANCADO");
        }
        if (permissoes.contains("ROLE_USUARIO")) {
            permissoes.remove("ROLE_USUARIO");
        } else {
            this.user.setActive(true);
            permissoes.add("ROLE_USUARIO");
        }
        if (permissoes.contains("ROLE_SUPER")) {
            permissoes.remove("ROLE_SUPER");
        } else {
            this.user.setActive(true);
        }
        if (permissoes.contains("ROLE_GEREN")) {
            permissoes.remove("ROLE_GEREN");
        } else {
            this.user.setActive(true);
        }

        UserRN userRN = new UserRN();
        userRN.save(this.user);

        return null;
    }

    public List<User> getList() {
        if (this.list == null) {
            UserRN userRN = new UserRN();
            this.list = userRN.listU(filterUser);
        }
        return this.list;
    }

    public List<User> getList1() {
        if (this.list == null) {
            UserRN userRN = new UserRN();
            this.list = userRN.listU();
        }
        return this.list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDestinoSalvar() {
        return destinoSalvar;
    }

    public void setDestinoSalvar(String destinoSalvar) {
        this.destinoSalvar = destinoSalvar;
    }

    public String getPasswordCriptografada() {
        return passwordCriptografada;
    }

    public void setPasswordCriptografada(String passwordCriptografada) {
        this.passwordCriptografada = passwordCriptografada;
    }

    public User getSelUser() {
        return selUser;
    }

    public void setSelUser(User selUser) {
        this.selUser = selUser;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public User getFilterUser() {
        return filterUser;
    }

    public void setFilterUser(User filterUser) {
        this.filterUser = filterUser;
    }

    public User getFilterUserTime() {
        return filterUserTime;
    }

    public void setFilterUserTime(User filterUserTime) {
        this.filterUserTime = filterUserTime;
    }

    public List<Double> getSaldoHoras() {
        return saldoHoras;
    }

    public void setSaldoHoras(List<Double> saldoHoras) {
        this.saldoHoras = saldoHoras;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
