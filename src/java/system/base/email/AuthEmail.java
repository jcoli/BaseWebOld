/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import org.apache.log4j.Logger;

/**
 *
 * @brief Classe AutenticaEmail
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date 02/05/2014
 *
 */
public class AuthEmail extends Authenticator {
    
    final static Logger logger = Logger.getLogger(AuthEmail.class);

    private String user;
    private String password;

    public AuthEmail(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.password);
    }

}
