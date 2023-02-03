/*
 * Copyright  2015 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.web.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;
import system.base.util.MessageUtil;
import system.base.web.UserBean;

/**
 * @Project BaseWeb
 * @brief Class AuthenticationFilter
 * @author Jeferson Coli - jcoli@tecnocoli.com.br -
 * @Date: 31/01/2015
 */
@ManagedBean(name = "authenticationFilter")
@RequestScoped
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//     private EntityManagerFactory factory = Persistence.createEntityManagerFactory("LoginCustomizadoPU");
//    private EntityManager em;
    private String message = "";

    final static Logger logger = Logger.getLogger(AuthenticationFilter.class);
    private Connection conPost;
    private String loadPassword;
    private boolean active = true;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private Integer attempts = 0;
    private Integer id;
    private Date dateAccountExpired = new Date();
    private Date dateCredentialsExpired = new Date();
    private Date dateLastFailAttempts = new Date();
    List<String> roles = new ArrayList<>();

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, BadCredentialsException {
        String login = request.getParameter("j_login");
        String password = request.getParameter("j_password");
        String passwordCripto = DigestUtils.md5DigestAsHex(password.getBytes());
        //Integer codigoEmpresa = Integer.parseInt(request.getParameter("j_empresa"));
       

        try {
            this.getUserBylogin(login);
          
    

            if (this.loadPassword.equals(passwordCripto) && this.active && this.accountNonLocked && this.accountNonExpired && this.credentialsNonExpired) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                this.getRolesBylogin(login);
                for (String r : roles) {
                    logger.info("ROLES: " + r.toString());
                    grantedAuths.add(new SimpleGrantedAuthority(r));
                }
                logger.info("authenticated");
                Authentication auth = new UsernamePasswordAuthenticationToken(login, password, grantedAuths);
                this.saveLog(this.id);
                Date d = new Date();
                Date d5 = new Date(System.currentTimeMillis() - 5L * 24 * 3600 * 1000);
                Date d6 = new Date(System.currentTimeMillis() - 6L * 24 * 3600 * 1000);
                Date d5c = new Date(this.dateCredentialsExpired.getTime() - (5L * 24 * 3600 * 1000));

                message = "";

                if ((d5c.getTime() <= d.getTime()) && (d5c.getTime() >= d6.getTime())) {
                    this.message = ("credentialsExpiredTime");
                    logger.info("cred exp");
                } else if (d5c.getTime() < d6.getTime()) {
                    this.credentialsNonExpired = false;
                    this.updateUser(this.id);
                    logger.info("cred exp block");
                    this.message = ("userBlockedByExpPass");
                }

                return auth;

            } else {

                logger.info("fail auth ");

                Locale locale = new Locale("en", "US");

                logger.info("fail auth " + locale.toString());

                if (!(this.loadPassword.equals(passwordCripto))) {
                    logger.info("fail auth password ");
                    Date d3 = new Date(System.currentTimeMillis() - 3L * 24 * 3600 * 1000);
                    logger.info("fail auth password " + d3.toString() + "  -----  " + this.dateLastFailAttempts);
                    if (this.dateLastFailAttempts == null) {
                        this.dateLastFailAttempts = new Date();
                    }
                    if (this.dateLastFailAttempts.getTime() < (d3.getTime())) {
                        this.attempts = 1;
                        this.dateLastFailAttempts = new Date();
                    } else {
                        this.attempts++;
                        if (this.attempts >= 5) {
                            this.accountNonLocked = false;
                        }
                    }
                    this.updateUser(this.id);
                }
                logger.info("fail auth 5 ");
                if (!this.accountNonExpired) {
                    this.message = ("accountExpired");
                } else if (!this.accountNonLocked) {
                    this.message = ("accountLocked");
                } else if (!this.credentialsNonExpired) {
                    this.message = ("credentialsExpired");
                } else {
                    this.message = ("badCredentials");
                }

                logger.info("fail auth " + this.message);
                throw new BadCredentialsException(this.message);
            }

        } catch (Exception e) {
            logger.error("fail auth catch " + e);
            throw new BadCredentialsException(e.getMessage());
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        request.getSession().setAttribute("msg", this.message);
        logger.info("authenticated successfull");
        if (!(this.credentialsNonExpired)) {
            logger.info("Cred. Expired");

            response.sendRedirect("restrict/principal.jsf");
        } else {
            response.sendRedirect("restrict/principal.jsf");
        }
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        request.getSession().setAttribute("msg", message);
        logger.info("authenticated unsuccessfull");

        response.sendRedirect("public/login.jsf");

    }

    /**
     * Metodos de acesso ao BD
     *
     */
    
    public Integer getUserBylogin(String login) {

        Integer ret = 0;

        try {
            this.connection_Postgres();

            logger.info("getUserByLogin " + login);
            String consUser = "SELECT password, active, id, accountnonexpired, accountnonlocked, credentialsnonexpired, \n"
                    + "dateaccountexpired, datecredentialsexpired, datelastfailattempts, language  FROM sysuser where login = ?;";

            PreparedStatement consUserStmtPost = conPost.prepareStatement(consUser);
            consUserStmtPost.setString(1, login);

            logger.info("getUserByLogin 2");

            //  System.out.println("searchUser " + consUserStmtPost.toString());
            ResultSet rsUser = consUserStmtPost.executeQuery();

            if (rsUser.next()) {
                this.loadPassword = rsUser.getString(1);
                this.active = rsUser.getBoolean(2);
                this.id = rsUser.getInt(3);
                this.accountNonExpired = rsUser.getBoolean(4);
                this.accountNonLocked = rsUser.getBoolean(5);
                this.credentialsNonExpired = rsUser.getBoolean(6);
                this.dateAccountExpired = rsUser.getDate(7);
                this.dateCredentialsExpired = rsUser.getDate(8);
                this.dateLastFailAttempts.setTime(rsUser.getTimestamp(9).getTime());

            } else {
                ret = 0;
            }

            Date d = new Date();
            if (this.dateAccountExpired != null) {
                if ((this.dateAccountExpired.getTime() < d.getTime())) {
                    this.accountNonExpired = false;
                }
            }
            

            this.conPost.close();

        } catch (Exception e) {
            logger.error("searchUser " + e.toString());

        }

        return ret;

    }

    public Integer getRolesBylogin(String login) {

        Integer ret = 0;

        try {
            this.connection_Postgres();

            // System.out.println("searchUser " + login);
            String consUser = "SELECT u.login, p.role FROM sysuser u, user_role p WHERE u.id = p.sysuser AND u.login = ?";

            PreparedStatement consUserStmtPost = conPost.prepareStatement(consUser);
            consUserStmtPost.setString(1, login);

            //  System.out.println("searchUser " + consUserStmtPost.toString());
            ResultSet rsUser = consUserStmtPost.executeQuery();

            while (rsUser.next()) {
                this.roles.add(rsUser.getString(2));
            }

            this.conPost.close();

        } catch (Exception e) {
            logger.error("searchUser " + e.toString());

        }

        return ret;

    }

    public void saveLog(Integer id) {

        try {
            this.connection_Postgres();
            String consSQlPost = "INSERT INTO log(\n"
                    + " datalog, description, loggeduser_id)\n"
                    + " VALUES (?, ?, ?);";
            Date d = new Date();
            PreparedStatement stmtPost;
            stmtPost = conPost.prepareStatement(consSQlPost);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(d.getTime());
            stmtPost.setTimestamp(1, sqlDate);
            stmtPost.setString(2, "logged");
            stmtPost.setInt(3, id);
            stmtPost.execute();

            this.conPost.close();

        } catch (Exception e) {
            logger.error("erro insert log logged " + e.toString());
        }

    }

    public void updateUser(Integer id) {

        try {
            this.connection_Postgres();
            String consSQlPost = "UPDATE sysuser SET accountnonexpired=?, accountnonlocked=?, credentialsnonexpired=?, "
                    + "attempts=?, datelastfailattempts=?  WHERE id = ?;";
            Date d = new Date();
            PreparedStatement stmtPost;
            stmtPost = conPost.prepareStatement(consSQlPost);
            stmtPost.setBoolean(1, this.accountNonExpired);
            stmtPost.setBoolean(2, this.accountNonLocked);
            stmtPost.setBoolean(3, this.credentialsNonExpired);
            stmtPost.setInt(4, this.attempts);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(this.dateLastFailAttempts.getTime());
            stmtPost.setTimestamp(5, sqlDate);

            stmtPost.setInt(6, id);

            stmtPost.execute();

            this.conPost.close();

        } catch (Exception e) {
            logger.error("erro update AccountExpired log logged " + e.toString());
        }

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
