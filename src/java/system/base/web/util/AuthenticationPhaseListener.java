/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import system.base.entities.user.User;
import system.base.entities.user.UserRN;
import system.base.util.MessageUtil;
import static system.base.web.util.AuthenticationFilter.logger;

/**
 * @Project BaseWeb
 * @brief Class AutenticacaoPhaseListener
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date: 31/01/2015
 */
@ManagedBean(name = "authenticationPhaseFilter")
@RequestScoped
public class AuthenticationPhaseListener implements PhaseListener {

    final static Logger logger = Logger.getLogger(AuthenticationPhaseListener.class);
    public User loggedUser = null;
    private Locale locale = null;
    private Connection conPost;
    private String language = "en_US";

    @Override
    public void afterPhase(PhaseEvent event) {
        //não implementado
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        logger.info("beforePhase");
        if (session != null) {
            String message = (String) session.getAttribute("msg");
            String infoMessage = "";
            logger.info("beforePhase not null " + message);

            if (message != null) {

                ExternalContext external = context.getExternalContext();
                String login = external.getRemoteUser();
                this.getUserBylogin(login);
                if (message.equals("badCredentials")) {
                    if (language.equals("pt_BR")) {
                        infoMessage = "Credenciais Incorretas";
                    }
                    if (language.equals("en_US")) {
                        infoMessage = "Bad Credentials";
                    }
                }
                if (message.equals("accountExpired")) {
                    if (language.equals("pt_BR")) {
                        infoMessage = "Conta Expirada";
                    }
                    if (language.equals("en_US")) {
                        infoMessage = "Account Expired";
                    }
                }

                if (message.equals("accountLocked")) {
                    if (language.equals("pt_BR")) {
                        infoMessage = "Consta Bloqueada";
                    }
                    if (language.equals("en_US")) {
                        infoMessage = "Account Locked";
                    }
                }
                
                 if (message.equals("credentialsExpired")) {
                    if (language.equals("pt_BR")) {
                        infoMessage = "Senha Expirada - Usuário Bloqueado";
                    }
                    if (language.equals("en_US")) {
                        infoMessage = "Credentials Expired - User Locked";
                    }
                }

                if (message.equals("credentialsExpiredTime")) {
                    if (language.equals("pt_BR")) {
                        infoMessage = "Senha Expirada - Troque sua senha - Seu usuário poderá ser bloqueado";
                    }
                    if (language.equals("en_US")) {
                        infoMessage = "Credentials Expired - change your password - Your User may be locked";
                    }
                }
                
                 if (message.equals("userBlockedByExpPass")) {
                    if (language.equals("pt_BR")) {
                        infoMessage = "Senha Expirada - Troque sua senha - Usuario bloqueado para o proximo login";
                    }
                    if (language.equals("en_US")) {
                        infoMessage = "Credentials Expired - change your password - User locked for next login";
                    }
                }

                if (!(infoMessage.isEmpty())) {

                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, infoMessage, null));
                    session.setAttribute("msg", null);
                }
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public Integer getUserBylogin(String login) {

        Integer ret = 0;

        try {
            this.connection_Postgres();

            logger.info("getUserByLogin " + login);
            String consUser = "SELECT language  FROM sysuser where login = ?;";

            PreparedStatement consUserStmtPost = conPost.prepareStatement(consUser);
            consUserStmtPost.setString(1, login);

            logger.info("getUserByLogin 2");

            //  System.out.println("searchUser " + consUserStmtPost.toString());
            ResultSet rsUser = consUserStmtPost.executeQuery();

            if (rsUser.next()) {
                this.language = rsUser.getString(1);

            } else {
                ret = 0;
            }

        } catch (Exception e) {
            logger.error("searchUser " + e.toString());

        }

        return ret;

    }

    private void connection_Postgres() {
        String driver;
        String user;
        String endereco;
        String pass;

        try {
            driver = "org.postgresql.Driver";
            user = "jcoli";
            pass = "shady55";
            endereco = "jdbc:postgresql://localhost/baseweb";
            Class.forName(driver);
            conPost = DriverManager.getConnection(endereco, user, pass);

        } catch (Exception e) {
            logger.error("erro Postgres " + e.toString());
        }
    }

}
