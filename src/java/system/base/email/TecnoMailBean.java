/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.email;

import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.log4j.Logger;
import system.base.email.AuthEmail;

/**
 *
 * @brief Classe TecnoMailBean
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date 02/05/2014
 *
 */
@ManagedBean(name = "tecnoMailBean")
@RequestScoped
public class TecnoMailBean {
    
    final static Logger logger = Logger.getLogger(TecnoMailBean.class);

//    public static final String SERVIDOR_SMTP = "smtp.gmail.com";
//    public static final String PORTA_SERVIDOR_SMTP = "465";
//    //private static final String	CONTA_EMAIL		= "sistema@tecnocoli.com.br";
//    private static final String CONTA_EMAIL = "jefersoncoli@gmail.com";
//    private static final String SENHA_EMAIL = "cacauzion";
//
//    private String from = "jefersoncoli@gmail.com";
    
    public static final String SERVER_SMTP = "cl-t058-333cl.privatedns.com";
    public static final String PORT_SERVER_SMTP = "465";
    private static final String STANDART_ACCOUNT = "jcoli@tecnocoli.com.br";
    private static final String PASSWORD_STANDART_ACCOUNT = "shady55";

    private String from = "jcoli@tecnocoli.com.br";
    private String to;
    private String topic;
    private String message;

    public void sendEmail() {
        FacesContext context = FacesContext.getCurrentInstance();
        AuthEmail autenticaEmail = new AuthEmail(TecnoMailBean.STANDART_ACCOUNT, TecnoMailBean.PASSWORD_STANDART_ACCOUNT);
        Session session = Session.getDefaultInstance(this.emailConfiguration(), autenticaEmail);
        //Habilita o LOG das ações executadas durante o envio do email
        session.setDebug(true);

        try {
            Transport envio = null;
            MimeMessage email = new MimeMessage(session);
            email.setRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            email.setFrom(new InternetAddress(this.from));
            email.setSubject(this.topic);
            email.setContent(this.message, "text/plain");
            email.setSentDate(new Date());
            envio = session.getTransport("smtp");
            envio.connect(TecnoMailBean.SERVER_SMTP, TecnoMailBean.STANDART_ACCOUNT, TecnoMailBean.PASSWORD_STANDART_ACCOUNT);
            email.saveChanges();
            envio.sendMessage(email, email.getAllRecipients());
            envio.close();

            context.addMessage(null, new FacesMessage("E-mail enviado com sucesso"));

        } catch (AddressException e) {
            FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: " + e.getMessage());
            context.addMessage(null, msg);
            return;
        } catch (MessagingException e) {
            FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
            context.addMessage(null, msg);
            return;
        }
    }

    public Properties emailConfiguration() {
        Properties config = new Properties();

		//Configuração adicional to servidor proxy.
        //Descomentar somente se utliza servidor com proxy.
		/*
         props.setProperty("proxySet", "true");
         props.setProperty("socksProxyHost","127.0.0.1"); //IP do Servidor Proxy
         props.setProperty("socksProxyPort","8080");  //Porta do servidor Proxy
         */
        
        config.put("mail.transport.protocol", "smtp"); //define protocolo from envio como SMTP
        //config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.ssl.enable", "true");
        
        config.put("mail.smtp", "true");
        config.put("mail.smtp.host", SERVER_SMTP); //servidor SMTP do GMAIL
        config.put("mail.smtp.auth", "true"); //ativa autenticacao
        config.put("mail.smtp.user", TecnoMailBean.STANDART_ACCOUNT); // conta que esta enviando o email
        config.put("mail.debug", "true");
        config.put("mail.smtp.port", PORT_SERVER_SMTP); //porta
        config.put("mail.smtp.socketFactory.port", PORT_SERVER_SMTP); //mesma porta to o socket
        config.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        config.put("mail.smtp.socketFactory.fallback", "false");
        return config;
    }

    public String getDe() {
        return from;
    }

    public void setDe(String de) {
        this.from = de;
    }

    public String getPara() {
        return to;
    }

    public void setPara(String para) {
        this.to = para;
    }

    public String getAssunto() {
        return topic;
    }

    public void setAssunto(String assunto) {
        this.topic = assunto;
    }

    public String getMensagem() {
        return message;
    }

    public void setMensagem(String mensagem) {
        this.message = mensagem;
    }

}
